package app.atividade1.pvbf.MyBikePark.view.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.api.AppUtil;
import app.atividade1.pvbf.MyBikePark.controller.ClienteController;
import app.atividade1.pvbf.MyBikePark.model.Cliente;

public class CadastroPasso01Ativity extends AppCompatActivity {
    EditText editPrimeiroNome, editSobrenome;
    CheckBox chPessoaFisica;
    Button btSalvarContinuar, btCancelar;
    boolean isFormulario, isPessoaFisica;
    SharedPreferences preferences;
    String primeirooNome, sobreNome;
    boolean pf;
    int ultimoIDVIP;

    Cliente novoVip;
    ClienteController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_passo01);

        initFormulario();

        //cancelar
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FancyAlertDialog.Builder(CadastroPasso01Ativity.this)
                        .setTitle("Confirme o Cancelamento")
                        .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
                        .setMessage("Deseja realmente Cancelar ?")
                        .setNegativeBtnText("Não")
                        .setPositiveBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
                        .setPositiveBtnText("Sim")
                        .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                        .setAnimation(Animation.POP)
                        .isCancellable(true)
                        .setIcon(R.drawable.ic_star_border_black_24dp, Icon.Visible)
                        .OnPositiveClicked(new FancyAlertDialogListener() {
                            @Override
                            public void OnClick() {
                                Intent intent = new Intent(CadastroPasso01Ativity.this, LoginUserActivity.class);
                                startActivity(intent);
                                finish();
                                return;
                            }
                        })
                        .OnNegativeClicked(new FancyAlertDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(getApplicationContext(), "Continue o Cadastro !", Toast.LENGTH_LONG).show();
                            }
                        })
                        .build();


            }
        });

        //salvar e continuar
        btSalvarContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFormulario = validarFormulario()) {
                    novoVip.setPrimeiroNome(editPrimeiroNome.getText().toString());
                    novoVip.setSobreNome(editSobrenome.getText().toString());


                    controller.incluir(novoVip);
                    ultimoIDVIP = controller.getUltimoID();

                    salvarSharedPreferences();
                    Intent intent = new Intent(CadastroPasso01Ativity.this, CadastroUsuarioActivity.class);
                    startActivity(intent);
                    finish();


                }

            }
        });

    }

    private boolean validarFormulario() {
        boolean retorno = true;

        if (TextUtils.isEmpty(editPrimeiroNome.getText().toString())) {
            editPrimeiroNome.setError("*");
            editPrimeiroNome.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(editSobrenome.getText().toString())) {
            editSobrenome.setError("*");
            editSobrenome.requestFocus();
            retorno = false;
        }
        return retorno;
    }

    private void initFormulario() {

        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSobrenome = findViewById(R.id.editSobreNome);

        btSalvarContinuar = findViewById(R.id.btSalvar);
        btCancelar = findViewById(R.id.btCancelar);
        isFormulario = false;

        novoVip = new Cliente();
        controller = new ClienteController(this);
        // restaurarSharedPreferences();
    }

    private void salvarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();


        /*dados.putBoolean("PF",isPessoaFisica);
        dados.putString("PrimeiroNome", editPrimeiroNome.getText().toString());
        dados.putString("SobreNome",editSobrenome.getText().toString());*/

        dados.putString("PrimeiroNome", novoVip.getPrimeiroNome());
        dados.putString("SobreNome", novoVip.getSobreNome());
        dados.putInt("ultimoIDVIP", ultimoIDVIP);


        dados.apply();
    }

    public void restaurarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        primeirooNome = preferences.getString("PrimeiroNome", "");
        sobreNome = preferences.getString("SobreNome", "");
        pf = preferences.getBoolean("PF", false);
    }

    public void pessoaFisica(View view) {
        isPessoaFisica = chPessoaFisica.isChecked();
    }
}