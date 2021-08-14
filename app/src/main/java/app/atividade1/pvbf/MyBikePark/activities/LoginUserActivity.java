package app.atividade1.pvbf.MyBikePark.activities;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.Icon;

import java.util.List;

import app.atividade1.pvbf.MyBikePark.model.Usuario;
import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.controller.AppUtil;
import app.atividade1.pvbf.MyBikePark.controller.UsuarioController;

public class LoginUserActivity extends AppCompatActivity {

    SharedPreferences preferences;
    EditText editTextEmail, editTextSenha;
    TextView textViewRecuperarSenha, textViewPolitica, txtCadastrar;
    CheckBox checkBoxLembrarSenha;
    Button btAcessar;
    ImageView imGLogo;

    boolean isFormulario = false, islembrarSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        iniciarViews();

        UsuarioController controller = new UsuarioController();
       // List<Usuario> usuarios  = controller.listar();
        Usuario usuario =new Usuario();

        textViewPolitica.setOnClickListener(view -> new FancyAlertDialog.Builder(LoginUserActivity.this)
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
                .build());

        btAcessar.setOnClickListener(view -> {
            if (isFormulario = validarFormulario()) {
                salvarSharedPreferences();
                usuario.setEmail(editTextEmail.getText().toString());
                usuario.setSenha(editTextSenha.getText().toString());
                if (validarDadosDoUsuario(usuario)) {
                    Intent intent = new Intent(LoginUserActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", getIdUser(editTextEmail.getText().toString(), editTextSenha.getText().toString()));
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                    return;
                } else  Toast.makeText(getApplicationContext(), "Verifique os dados", Toast.LENGTH_LONG).show();
            }
        });

        txtCadastrar.setOnClickListener(view -> {
            Intent intent = new Intent(LoginUserActivity.this, CadastroPassoUmActivity.class);
            startActivity(intent);
            finish();
            return;
        });

        textViewRecuperarSenha.setOnClickListener(view -> {
            Intent intent = new Intent(LoginUserActivity.this, RecuperarSenhaActivity.class);
            startActivity(intent);
            finish();
            return;

        });
    }

    private boolean validarDadosDoUsuario(Usuario usuario) {
        boolean retorno = false;
        UsuarioController usuarioController=new UsuarioController();

            if (usuarioController.validarDadosDoUsuario(usuario)) {
             retorno = true;
            }

        return retorno;
    }

    private boolean validarFormulario() {
        boolean retorno = true;
        if (TextUtils.isEmpty(editTextEmail.getText().toString())) {
            editTextEmail.setError("*");
            editTextEmail.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(editTextSenha.getText().toString())) {
            editTextSenha.setError("*");
            editTextSenha.requestFocus();
            retorno = false;
        }
        return retorno;
    }


    private void iniciarViews() {
        textViewRecuperarSenha = findViewById(R.id.textRecuperarSenhaLogin);
        textViewPolitica = findViewById(R.id.textPoliticaLogin);
        editTextEmail = findViewById(R.id.editEmailLoginID);
        editTextSenha = findViewById(R.id.editSenhaLoginID);
        checkBoxLembrarSenha = findViewById(R.id.checkBoxLembrarSenhaLogin);
        btAcessar = findViewById(R.id.btAcessarLogin);
        txtCadastrar = findViewById(R.id.txt_activity_login_user_cadastro);
        imGLogo = findViewById(R.id.imgLogo);
    }

    public void lembrarSenha(View view) {
        islembrarSenha = checkBoxLembrarSenha.isChecked();
    }

    private void salvarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        dados.putBoolean("loginAutomatico", islembrarSenha);
        dados.putString("Email", editTextEmail.getText().toString());
        dados.putString("Senha", editTextSenha.getText().toString());

        dados.apply();
    }

    public int getIdUser(String email, String senha) {
        UsuarioController usuarioController = new UsuarioController();
        Usuario usuario = usuarioController.buscaEmailSenha(email, senha);
        return usuario.getId();
    }


}