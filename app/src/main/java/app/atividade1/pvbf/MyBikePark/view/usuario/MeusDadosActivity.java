package app.atividade1.pvbf.MyBikePark.view.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.api.AppUtil;
import app.atividade1.pvbf.MyBikePark.controller.ClienteController;
import app.atividade1.pvbf.MyBikePark.model.Cliente;

public class MeusDadosActivity extends AppCompatActivity {
    Button btVoltar;
    //cli vip
    EditText editPrimeiroNomeMydb, editSobreNomeMydb;
    CheckBox ckPessoaFisicaVIPMydb;
    //cli PF
    EditText editCPFPF, editNomeCompleto, editSobreNomeVipPFMydb;

    //cli PJ
    EditText editCNPJMydb, editRazaoSocialMydb, editDataAberturaMydb;
    CheckBox checSimplesNacionalMydb, checMEIMydb;
    //credenciais
    EditText editEmailMydb, editSenhaMydb;
    CheckBox checkBoxTermoMydb;

    ClienteController controller;

    //metodo Cliente tem acesso a cliente PF e PJ
    Cliente cliente;
    // ClientePF clientePF;

    int clienteID;
    boolean isPessoaFisica;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_dados);

        restaurarSharedPreferences();
        initFormulario();

        popularFormulario();


    }

    private void popularFormulario() {
        if (clienteID >= 1) {
            cliente = controller.getClienteByID(cliente);


            editPrimeiroNomeMydb.setText(cliente.getPrimeiroNome());
            editSobreNomeMydb.setText(cliente.getSobreNome());
            editEmailMydb.setText(cliente.getEmail());
            editSenhaMydb.setText(cliente.getSenha());




            checkBoxTermoMydb.setChecked(true);
        } else {

            new FancyAlertDialog.Builder(MeusDadosActivity.this)
                    .setTitle("ATENÇÃO!")
                    .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
                    .setMessage("Não foi possível, recuperar os dados do cliente ?")


                    //.setPositiveBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
                    .setPositiveBtnText("Concordo")
                    .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                    .setAnimation(Animation.POP)
                    .isCancellable(true)
                    .setIcon(R.drawable.ic_star_border_black_24dp, Icon.Visible)
                    .OnPositiveClicked(new FancyAlertDialogListener() {
                        @Override
                        public void OnClick() {
                            Intent intent = new Intent(MeusDadosActivity.this, MenuUserActivity.class);
                            startActivity(intent);
                            finish();
                            return;
                        }
                    })

                    .build();
        }
    }

    private void initFormulario() {

        editPrimeiroNomeMydb = findViewById(R.id.editPrimeiroNomeMydb);

        editSobreNomeMydb = findViewById(R.id.editSobreNomeMydb);





        ///////////////
        editEmailMydb = findViewById(R.id.editEmailMydb);

        editSenhaMydb = findViewById(R.id.editSenhaMydb);

        checkBoxTermoMydb = findViewById(R.id.checkBoxTermoMydb);
        ////////////////
        //btVoltar = findViewById(R.id.btVoltarMeusDados);

        controller = new ClienteController(this);

        cliente = new Cliente();

        cliente.setId(clienteID);



    }

    private void salvarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();


    }

    public void restaurarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);

        isPessoaFisica = preferences.getBoolean("PF", false);
        clienteID = preferences.getInt("ultimoIDVIP", -1);
        //cliente.setPrimeiroNome(preferences.getString("PrimeiroNome",""));


    }

    public void voltar(View view) {
        Intent intent = new Intent(MeusDadosActivity.this, MenuUserActivity.class);
        startActivity(intent);
        finish();
    }
}