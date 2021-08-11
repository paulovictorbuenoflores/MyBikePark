package app.atividade1.pvbf.MyBikePark.view.telaPrincipal;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.api.AppUtil;
import app.atividade1.pvbf.MyBikePark.controller.ClienteORMController;
import app.atividade1.pvbf.MyBikePark.model.ClienteORM;
import app.atividade1.pvbf.MyBikePark.view.usuario.AlterarMeusDados;
import app.atividade1.pvbf.MyBikePark.view.usuario.MeusDados;


// TODO - Criar o novo Layout para suporte aos CARDS

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    FragmentManager fragmentManager;

    NavigationView navigationView;

    Menu menu;
    //para controlar os itens do menu
    MenuItem nav_ListaRiders;
    MenuItem nav_ImportanciaDaBike;
    MenuItem nav_adicionar_parque;
    MenuItem nav_lista_parque;


    ClienteORM clienteORM;
    ClienteORMController clienteORMController;
    TextView txtNomeUserNoMenu;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();

        //pegando o id do usuario enviado pela intent da tela de login
        id = bundle.getInt("id");
        Log.d(null, "Nome:" + bundle.getString("id") + id);


        ////busca o cliente logado
        buscarCliente(id);
//salvar no sharepreference


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Action Button Clicado", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.content_fragment, new ListarClientesFragment()).commit();

    }

    private ClienteORM buscarCliente(int id) {
        ClienteORM obj = new ClienteORM();
        clienteORMController = new ClienteORMController();
        obj.setId(id);

        obj = clienteORMController.getById(obj);

        salvarSharedPreferences(obj.getId());

        return obj;
    }

    private void initFormulario() {

        txtNomeUserNoMenu = findViewById(R.id.txtNomeUser);

        clienteORM = new ClienteORM();
        clienteORMController = new ClienteORMController();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //txtNomeUserNoMenu=findViewById(R.id.txtNomeUse);


        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //menu 3 pontinho no canto da tela
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        clienteORM = buscarCliente(id);
        //noinspection SimplifiableIfStatement
        if (itemId == R.id.action_perfil) {


            //Fragment myFrag = new Fragment();
            //Bundle bundle = new Bundle();
            //bundle.putInt("id", id);

            // myFrag.setArguments(bundle);
            //fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_fragment, new MeusDados()).commit();


            Toast.makeText(getApplicationContext(), "Meu Perfil", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.action_alterarDados) {
            fragmentManager.beginTransaction().replace(R.id.content_fragment, new AlterarMeusDados()).commit();


            Toast.makeText(getApplicationContext(), "Meu Perfil", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.action_ExcluirConta) {
            new FancyAlertDialog.Builder(MainActivity.this)
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


                            clienteORMController.deletar(clienteORM);


                            Toast.makeText(getApplicationContext(), clienteORM.getPrimeiroNome() + ", volte sempre e obrigado...", Toast.LENGTH_LONG).show();
                            //isLoginAutomatico = false;
                            //  salvarSharedPreferences();
                            finish();
                            return;
                        }
                    })
                    .OnNegativeClicked(new FancyAlertDialogListener() {
                        @Override
                        public void OnClick() {
                            Toast.makeText(getApplicationContext(), clienteORM.getPrimeiroNome() + ", divirta-se com as opções do aplicativo...", Toast.LENGTH_LONG).show();
                        }
                    })
                    .build();

        } else if (itemId == R.id.action_settings) {
            new FancyAlertDialog.Builder(MainActivity.this)
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
                            Toast.makeText(getApplicationContext(), clienteORM.getPrimeiroNome() + ", volte sempre e obrigado...", Toast.LENGTH_LONG).show();
                            //isLoginAutomatico = false;
                            //salvarSharedPreferences();
                            finish();
                            return;
                        }
                    })
                    .OnNegativeClicked(new FancyAlertDialogListener() {
                        @Override
                        public void OnClick() {
                            Toast.makeText(getApplicationContext(), clienteORM.getPrimeiroNome() + ", divirta-se com as opções do aplicativo...", Toast.LENGTH_LONG).show();
                        }
                    })
                    .build();
        }

        return super.onOptionsItemSelected(item);
    }


    //metodo MenuItem
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        int id = item.getItemId();


        //TODO: Obter ID para a opcao selecionada no MENU DRAWER
        if (id == R.id.nav_listar_clientes_cards) {
            menu = navigationView.getMenu();
            nav_ImportanciaDaBike = menu.findItem(R.id.nav_ImportanciaDabike);


            fragmentManager.beginTransaction().replace(R.id.content_fragment, new TabLayoutFragment()).commit();


        } else if (id == R.id.nav_ImportanciaDabike) {
            menu = navigationView.getMenu();
            nav_ImportanciaDaBike = menu.findItem(R.id.nav_ImportanciaDabike);


            fragmentManager.beginTransaction().replace(R.id.content_fragment, new TabLayoutFragment()).commit();

        } else if (id == R.id.nav_adicionar_parque) {
            menu = navigationView.getMenu();
            nav_adicionar_parque = menu.findItem(R.id.nav_adicionar_parque);


            fragmentManager.beginTransaction().replace(R.id.content_fragment, new AdicionarParqueFragment()).commit();

        } else if (id == R.id.nav_lista_parque) {
            menu = navigationView.getMenu();
            nav_lista_parque = menu.findItem(R.id.nav_lista_parque);


            fragmentManager.beginTransaction().replace(R.id.content_fragment, new ListaDeParquesFragmentoRecyclerView()).commit();

        } else if (id == R.id.txtNomeUser) {
            //headerLayout=navigationView.getHeaderCount();
            txtNomeUserNoMenu = findViewById(R.id.txtNomeUser);
            txtNomeUserNoMenu.setText("t");


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //TODO: IMPLEMENTAR TODOS OS MAP
    //bt map mostrar todos os parques no mapa
    public void todosOsParques(View view) {


    }

    private void salvarSharedPreferences(int id) {
        SharedPreferences preferences;
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();


        dados.putInt("id", id);

        dados.apply();
    }


}
