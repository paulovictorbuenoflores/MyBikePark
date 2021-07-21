package app.atividade1.pvbf.MyBikePark.view.usuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.api.AppUtil;
import app.atividade1.pvbf.MyBikePark.controller.ClienteController;
import app.atividade1.pvbf.MyBikePark.model.Cliente;
import app.atividade1.pvbf.MyBikePark.view.listaParqueMap.ListaParque;

public class MenuUserActivity extends AppCompatActivity {
    Button btConsultarClientes, btAtualizarDados, btMeusDados, btApagarConta, btSair;
    TextView txtTitulo;
    private SharedPreferences preferences;
    Cliente cliente;

    ClienteController controller;


    String nome;
    boolean isLoginAutomatico;

    //ImageView imGLogo, imgBackGround;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_user);

        //controller =new ClienteController(this);
        //controller.getClienteByID(cliente);
        //cliente.getClientePF();

        initFormulario();

        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        findViewById(R.id.imagemMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        txtTitulo.setText("Bem vindo, " + nome);

        /*
        btMeusDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btAtualizarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btApagarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btConsultarClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(MenuUserActivity.this, ListaEstadosActivity.class);
                startActivity(intent);
            }
        });
        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLoginAutomatico=false;
                salvarSharedPreferences();
                finish();
                return;
            }
        });*/
    }



    private void initFormulario() {

        txtTitulo = findViewById(R.id.txtTituloMenuBemVindo);
        btMeusDados = findViewById(R.id.btMeuBD);
        btAtualizarDados = findViewById(R.id.btUpdate);
        btApagarConta = findViewById(R.id.btApagar);

        btSair = findViewById(R.id.btSairApp);
        cliente = new Cliente();
        controller = new ClienteController(this);

        restaurarSharedPreferences();

    }

    private void salvarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

/*
        dados.putBoolean("loginAutomatico",islembrarSenha);
        dados.putString("Email", editTextEmail.getText().toString());
        dados.putString("Senha",editTextSenha.getText().toString());

        dados.apply();*/


        dados.putBoolean("loginAutomatico", isLoginAutomatico);
        dados.apply();

    }

    public void restaurarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);


        cliente.setPrimeiroNome(preferences.getString("PrimeiroNome", ""));
        nome = cliente.getPrimeiroNome();
//primeiroNome, sobreNome, email, senha;
        cliente.setId(preferences.getInt("ultimoIDClientePF", -1));



    }

    public void AtualizarDados(View view) {
        Intent intent = new Intent(MenuUserActivity.this, AtualizarMeusDadosActivity.class);
        startActivity(intent);
        finish();
    }

    public void sairDoAplicativo(View view) {
        new FancyAlertDialog.Builder(MenuUserActivity.this)
                .setTitle("Sair Do Aplicativo")
                .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
                .setMessage("Deseja realmente sair ?")
                .setNegativeBtnText("Retornar")
                .setPositiveBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
                .setPositiveBtnText("Sim")
                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                .setAnimation(Animation.POP)
                .isCancellable(true)
                .setIcon(R.drawable.ic_star_border_black_24dp, Icon.Visible)
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(getApplicationContext(), cliente.getPrimeiroNome() + ", volte sempre e obrigado...", Toast.LENGTH_LONG).show();
                        isLoginAutomatico = false;
                        salvarSharedPreferences();
                        finish();
                        return;
                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(getApplicationContext(), cliente.getPrimeiroNome() + ", divirta-se com as opções do aplicativo...", Toast.LENGTH_LONG).show();
                    }
                })
                .build();

    }

    public void excluirMinhaConta(View view) {
        new FancyAlertDialog.Builder(MenuUserActivity.this)
                .setTitle("Excluir Conta")
                .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
                .setMessage("Deseja realmente Excluir ?")
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


                        controller.deletar(cliente);



                        Toast.makeText(getApplicationContext(), cliente.getPrimeiroNome() + ", volte sempre e obrigado...", Toast.LENGTH_LONG).show();
                        isLoginAutomatico = false;
                        salvarSharedPreferences();
                        finish();
                        return;
                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(getApplicationContext(), cliente.getPrimeiroNome() + ", divirta-se com as opções do aplicativo...", Toast.LENGTH_LONG).show();
                    }
                })
                .build();
    }

    public void meusDados(View view) {

        Intent intent = new Intent(MenuUserActivity.this, MeusDadosActivity.class);
        startActivity(intent);
        finish();
    }

    public void listaRiders(View view) {
        Intent intent =new Intent(MenuUserActivity.this, ListaParque.class);
        startActivity(intent);
        finish();
    }
}