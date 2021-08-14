package app.atividade1.pvbf.MyBikePark.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import app.atividade1.pvbf.MyBikePark.model.Usuario;
import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.controller.AppUtil;
import app.atividade1.pvbf.MyBikePark.controller.UsuarioController;

public class CadastroPassoDoisActivity extends AppCompatActivity {
    EditText editTextNome, editTextEmail, editTextSenha, editTextConfirmarSenha;
    CheckBox checkBoxTermo;
    Button btVoltar, btCadastrar;
    Boolean isFormularioOK;
    UsuarioController usuarioController = new UsuarioController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario_passo_dois);
        initFormulario();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Usuario usuario = (Usuario) bundle.getSerializable("usuario");



        editTextNome.setText(usuario.getPrimeiroNome()+usuario.getSobreNome());

        btVoltar.setOnClickListener(view -> new FancyAlertDialog.Builder(CadastroPassoDoisActivity.this)
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
                        Intent intent = new Intent(CadastroPassoDoisActivity.this, LoginUserActivity.class);
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
                .build());

        btCadastrar.setOnClickListener(view -> {
            isFormularioOK = true;
            if (TextUtils.isEmpty(editTextNome.getText().toString())) {
                isFormularioOK = false;
                editTextNome.setError("*");
                editTextNome.requestFocus();

            }
            if (TextUtils.isEmpty(editTextEmail.getText().toString())) {
                isFormularioOK = false;
                editTextEmail.setError("*");
                editTextEmail.requestFocus();

            }
            if (TextUtils.isEmpty(editTextSenha.getText().toString())) {
                isFormularioOK = false;
                editTextSenha.setError("*");
                editTextSenha.requestFocus();

            }
            if (TextUtils.isEmpty(editTextConfirmarSenha.getText().toString())) {
                isFormularioOK = false;
                editTextConfirmarSenha.setError("*");
                editTextConfirmarSenha.requestFocus();

            }
            if (!checkBoxTermo.isChecked()) {
                isFormularioOK = false;

                new FancyAlertDialog.Builder(CadastroPassoDoisActivity.this)
                        .setTitle("Política de Privacidade & Termos de Uso")
                        .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
                        .setMessage("Concordo com as normas de privacidade do aplicativo?")
                        .setNegativeBtnText("Discordo")
                        .setPositiveBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
                        .setPositiveBtnText("Concordo")
                        .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                        .setAnimation(Animation.POP)
                        .isCancellable(true)
                        .setIcon(R.drawable.ic_star_border_black_24dp, Icon.Visible)
                        .OnPositiveClicked(() -> Toast.makeText(getApplicationContext(), "Obrigado, seja bem vindo", Toast.LENGTH_SHORT).show())
                        .OnNegativeClicked(new FancyAlertDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(getApplicationContext(), "Lamentamos, mas é preciso aceitar os termos de uso", Toast.LENGTH_SHORT).show();
                                finish();
                                return;
                            }
                        })
                        .build();

            }
            //verifica o formulario esta ok
            if (isFormularioOK) {
                if (!validarSenha()) {

                    isFormularioOK = false;

                    Toast.makeText(getApplicationContext(), "As senhas não conferem", Toast.LENGTH_LONG).show();

                    editTextSenha.setError("*");
                    editTextSenha.requestFocus();
                    editTextConfirmarSenha.setError("*");


                } else {
                    usuario.setEmail(editTextEmail.getText().toString());
                    usuario.setSenha(AppUtil.gerarMD5Hash(editTextSenha.getText().toString()));
                    usuarioController.update(usuario);



                    usuario.setEmail(editTextEmail.getText().toString());
                    usuario.setSenha(AppUtil.gerarMD5Hash(editTextSenha.getText().toString()));
                   // clienteORM.setId(0);
                    usuarioController.insert(usuario);
                    //salvarSharedPreferences();

                    Intent intent2 = new Intent(CadastroPassoDoisActivity.this, LoginUserActivity.class);
                    startActivity(intent2);
                    finish();
                    return;
                }
            }


        });


    }

    private void initFormulario() {
        editTextNome = findViewById(R.id.editNome);
        editTextEmail = findViewById(R.id.editEmail);
        editTextSenha = findViewById(R.id.editSenha);
        editTextConfirmarSenha = findViewById(R.id.editConfirmarSenha);
        checkBoxTermo = findViewById(R.id.checkBoxTermo);
        btCadastrar = findViewById(R.id.bttCadContinuar);
        btVoltar = findViewById(R.id.bttCadVoltar);

    }

    public void validarTermo(View view) {
        if (!checkBoxTermo.isChecked()) {
            Toast.makeText(getApplicationContext(), "É necessario aceitar os termos de uso para continuar o cadastro...", Toast.LENGTH_LONG).show();
            new FancyAlertDialog.Builder(CadastroPassoDoisActivity.this)
                    .setTitle("Política de Privacidade & Termos de Uso")
                    .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
                    .setMessage("Concordo com as normas de privacidade do aplicativo?")
                    .setNegativeBtnText("Discordo")
                    .setPositiveBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
                    .setPositiveBtnText("Concordo")
                    .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                    .setAnimation(Animation.POP)
                    .isCancellable(true)
                    .setIcon(R.drawable.ic_star_border_black_24dp, Icon.Visible)
                    .OnPositiveClicked(() -> Toast.makeText(getApplicationContext(), "Obrigado, seja bem vindo", Toast.LENGTH_SHORT).show())
                    .OnNegativeClicked(() -> {
                        Toast.makeText(getApplicationContext(), "Lamentamos, mas é preciso aceitar os termos de uso", Toast.LENGTH_SHORT).show();
                        finish();
                        return;
                    })
                    .build();
        }
    }

    public boolean validarSenha() {
        boolean retorno = false;
        String senhaA = editTextSenha.getText().toString(), senhaB = editTextConfirmarSenha.getText().toString();
        if (senhaA.equals(senhaB)) {
            retorno = true;
        }
        return retorno;
    }




}