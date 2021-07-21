package app.atividade1.pvbf.MyBikePark.view.telaPrincipal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
import app.atividade1.pvbf.MyBikePark.controller.ClienteORMController;
import app.atividade1.pvbf.MyBikePark.model.ClienteORM;
import app.atividade1.pvbf.MyBikePark.view.usuario.AtualizarMeusDadosActivity;
import app.atividade1.pvbf.MyBikePark.view.usuario.MenuUserActivity;
import app.atividade1.pvbf.MyBikePark.view.usuario.MeusDadosActivity;


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
        ClienteORM obj=new ClienteORM();
        clienteORMController =new ClienteORMController();
        obj.setId(id);

        obj=clienteORMController.getById(obj);



        return obj;
    }

    private void initFormulario() {

        txtNomeUserNoMenu=findViewById(R.id.txtNomeUser);

        clienteORM =new ClienteORM();
        clienteORMController =new ClienteORMController();

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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_perfil) {

            Intent intent = new Intent(MainActivity.this, MeusDadosActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(getApplicationContext(), "Meu Perfil", Toast.LENGTH_SHORT).show();
            return true;
        }else if (id == R.id.action_alterarDados) {
            Intent intent = new Intent(MainActivity.this, AtualizarMeusDadosActivity.class);
            startActivity(intent);
            finish();
            return true;
        }else if(id==R.id.action_ExcluirConta){
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

        }else if (id == R.id.action_settings) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //metodo MenuItem
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {



        int id = item.getItemId();


        //TODO: Obter ID para a opcao selecionada no MENU DRAWER
        if (id == R.id.nav_listar_clientes_cards) {
            menu=navigationView.getMenu();
            nav_ListaRiders =menu.findItem(R.id.nav_listar_clientes_cards);





            fragmentManager.beginTransaction().replace(R.id.content_fragment, new ListarClientesCardsFragment()).commit();


        } else if (id == R.id.nav_ImportanciaDabike) {
            menu=navigationView.getMenu();
            nav_ImportanciaDaBike=menu.findItem(R.id.nav_ImportanciaDabike);




            fragmentManager.beginTransaction().replace(R.id.content_fragment, new ModeloFragmentListaRiders()).commit();

        } else if (id == R.id.nav_adicionar_parque) {
            menu=navigationView.getMenu();
            nav_adicionar_parque=menu.findItem(R.id.nav_adicionar_parque);


            fragmentManager.beginTransaction().replace(R.id.content_fragment, new AdicionarParqueFragment()).commit();

        } else if (id == R.id.nav_lista_parque) {
            menu=navigationView.getMenu();
            nav_lista_parque=menu.findItem(R.id.nav_lista_parque);


            fragmentManager.beginTransaction().replace(R.id.content_fragment, new ListaDeParquesFragmentoRecyclerView()).commit();

        }else if(id == R.id.txtNomeUser){
            //headerLayout=navigationView.getHeaderCount();
            //txtNomeUser


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //bt map mostrar todos os parques no mapa
    public void todosOsParques(View view) {


    }

   /* @Override
    public View onCreateView(LinearLayout inflater, ViewGroup container, Bundle savedInstanceState){
    View v = inflater.inflate(R.layout.fragment_popup_menu, container, false);

    Button buttonPopup v.findViewById(R.id.btnPopup);
    buttonPopup.setOnClickListener(new View.onClickListener(){
    @Override
    public void onClick(View view){

    }


    });
    }*/
   public void excluirMinhaConta(View view) {
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
   }
    public void meusDados(View view) {

        Intent intent = new Intent(MainActivity.this, MeusDadosActivity.class);
        startActivity(intent);
        finish();
    }
}
