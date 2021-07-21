package app.atividade1.pvbf.MyBikePark.view.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.api.AppUtil;
import app.atividade1.pvbf.MyBikePark.controller.ClienteController;
import app.atividade1.pvbf.MyBikePark.controller.ClienteORMController;
import app.atividade1.pvbf.MyBikePark.model.Cliente;
import app.atividade1.pvbf.MyBikePark.model.ClienteORM;
import app.atividade1.pvbf.MyBikePark.view.telaPrincipal.MainActivity;

public class LoginUserActivity extends AppCompatActivity {
    List<Cliente> clientes;
    Cliente cliente;
    EditText editTextEmail, editTextSenha;
    TextView textViewRecuperarSenha, textViewPolitica, txtSejaVip;
    CheckBox checkBoxLembrarSenha;
    Button btAcessar;

    boolean isFormulario, islembrarSenha;
    SharedPreferences preferences;
    ImageView imGLogo, imgBackGround;
    ClienteController controller;
ClienteORMController clienteORMController;
ClienteORM clienteORM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        initFormulario();
        loadImagens();
        clientes = new ArrayList<>();
        controller = new ClienteController(getApplicationContext());
        clientes = controller.listar();
        ////POLITICA///
        textViewPolitica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FancyAlertDialog.Builder(LoginUserActivity.this)
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
                        .OnPositiveClicked(new FancyAlertDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(getApplicationContext(), "Obrigado, seja bem vindo", Toast.LENGTH_SHORT).show();
                            }
                        })
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
        });
        ////BT ACESSAR ///
        btAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //verificar formulario, sé o formulario estiver ok, vamos verificar a senha, login ok?

                if (isFormulario = validarFormulario()) {
                    salvarSharedPreferences();
                    //  senha2=editTextSenha.getText().toString();// email2=editTextEmail.getText().toString();
                    if (validarDadosDoUsuario()) {


                        //pegar o id do usuario

                       // Intent sendIntent = new Intent();
                        restaurarSharedPreferences(editTextEmail.getText().toString(),editTextSenha.getText().toString());
                       //passar na intente o id do usuario






                       Log.i("db_log","id :"+clienteORM.getId());
                        //Intent intent = new Intent(LoginUserActivity.this, MenuUserActivity.class);
                       // Intent intent = new Intent(LoginUserActivity.this, MainActivity.class);

                        Intent intent = new Intent(LoginUserActivity.this, MainActivity.class);
                        Bundle bundle = new Bundle();

                        bundle.putInt("id", clienteORM.getId());

                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                        return;

                    } else {
                        Toast.makeText(getApplicationContext(), "Verifique os dados", Toast.LENGTH_LONG).show();

                    }


                }

            }
        });
        ///BT SEJA VIP ///
        txtSejaVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginUserActivity.this, ClienteVipActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
        ///RECUPERAR SENHA////
        textViewRecuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(getApplicationContext(),"carregando...",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginUserActivity.this, RecuperarSenhaActivity.class);
                startActivity(intent);
                finish();
                return;

            }
        });
    }


    private boolean validarDadosDoUsuario() {
        boolean retorno = false, retornov;


        for (int i = 0; i < clientes.size(); i++) {

            //cliente=clientes.get(i);
            if (ClienteController.validarDadosDoCliente(cliente = clientes.get(i), editTextEmail.getText().toString(), AppUtil.gerarMD5Hash(editTextSenha.getText().toString()))) {
                retornov = true;
                if (retornov) retorno = true;
                else retorno = false;
            }
        }


        int b = 0;
        return retorno;
        //return true;
    }

    //verificar campos de dados formulario
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

    //recebe formulario do layout
    private void initFormulario() {
        textViewRecuperarSenha = findViewById(R.id.textRecuperarSenhaLogin);
        textViewPolitica = findViewById(R.id.textPoliticaLogin);
        editTextEmail = findViewById(R.id.editEmailLoginID);
        editTextSenha = findViewById(R.id.editSenhaLoginID);
        checkBoxLembrarSenha = findViewById(R.id.checkBoxLembrarSenhaLogin);
        btAcessar = findViewById(R.id.btAcessarLogin);
        txtSejaVip = findViewById(R.id.btSejaVipLogin);
        imgBackGround = findViewById(R.id.imgBackground);
        imGLogo = findViewById(R.id.imgLogo);


        ///////////////////////////////////////////////////////
        isFormulario = false;
        cliente = new Cliente();
        restaurarSharedPreferences(editTextEmail.getText().toString(),editTextSenha.getText().toString());
        controller = new ClienteController(getApplicationContext());
        clienteORM =new ClienteORM();
        //
        //cliente.setId(20);

        // controller.alterar(cliente);
        //controller.deletar(cliente);
        //List<Cliente> listaCliente =controller.listar();

    }

    //carregar IMG
    private void loadImagens() {

       /* Picasso.with (this)
                .load (AppUtil.URL_IMG_BACKGROUD)
                .resize (50, 50)
                .centerCrop ()
                .into (imgBackGround);*/
        // Picasso.get().load(AppUtil.URL_IMG_BACKGROUD).into(imgBackGround);
        Picasso.get().load(AppUtil.URL_IMG_BACKGROUD).into(imgBackGround);
        Picasso.get().load(AppUtil.URL_IMG_LOGO).placeholder(R.drawable.carregando_animacao).into(imGLogo);
    }

    //verificar checkbox se é para lembrarsenha
    public void lembrarSenha(View view) {
        //  salvarSharedPreferencesLoginAutomatico();
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

    public int restaurarSharedPreferences(String email,String senha) {
        /*preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        cliente.setEmail(preferences.getString("Email", ""));
        cliente.setSenha(preferences.getString("Senha", ""));
        islembrarSenha = preferences.getBoolean("loginAutomatico", false);*/

        clienteORMController=new ClienteORMController();
        clienteORM=new ClienteORM();

        clienteORM=clienteORMController.buscaEmailSenha(email,senha);

return  clienteORM.getId();

    }


}