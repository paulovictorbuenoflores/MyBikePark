package app.atividade1.pvbf.MyBikePark.view.telaPrincipal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.controller.ClienteORMController;
import app.atividade1.pvbf.MyBikePark.model.ClienteORM;


// TODO - Criar o novo Layout para suporte aos CARDS

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    FragmentManager fragmentManager;

    NavigationView navigationView;

    Menu menu;

    MenuItem nav_CollapsingToolbarLayout;
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

       // initFormulario();
       // clienteORM.setId(id);
       // clienteORM=clienteORMController.getById(clienteORM);

      //  txtNomeUserNoMenu.setText("ok");

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.nav_listar_clientes_cards) {

            fragmentManager.beginTransaction().replace(R.id.content_fragment,
                    new ListarClientesCardsFragment()).commit();


        } else if (id == R.id.nav_ImportanciaDabike) {


            fragmentManager.beginTransaction().replace(R.id.content_fragment,
                    new ModeloFragmentListaRiders()).commit();

        } else if (id == R.id.nav_adicionar_parque) {


            fragmentManager.beginTransaction().replace(R.id.content_fragment, new AdicionarParqueFragment()).commit();

        } else if (id == R.id.nav_lista_parque) {


            fragmentManager.beginTransaction().replace(R.id.content_fragment, new ListaDeParquesFragmentoRecyclerView()).commit();

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //bt map mostrar todos os parques no mapa
    public void todosOsParques(View view) {


    }
}
