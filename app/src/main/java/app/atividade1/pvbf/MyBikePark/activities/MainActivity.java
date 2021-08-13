package app.atividade1.pvbf.MyBikePark.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.NAOSEIPRAQUESERVE.UsuarioController;
import app.atividade1.pvbf.MyBikePark.fragments.AdicionarParqueFragment;
import app.atividade1.pvbf.MyBikePark.fragments.AlterarMeusDadosFragment;
import app.atividade1.pvbf.MyBikePark.fragments.DadosCadastradosFragment;
import app.atividade1.pvbf.MyBikePark.fragments.ImportanciaBikeFragment;
import app.atividade1.pvbf.MyBikePark.fragments.MapaFragment;
import app.atividade1.pvbf.MyBikePark.fragments.MeuPerfilFragment;
import app.atividade1.pvbf.MyBikePark.fragments.ParquesFragment;
import app.atividade1.pvbf.MyBikePark.fragments.TodosOsParquesFragment;
import app.atividade1.pvbf.MyBikePark.fragments.UsuariosFragment;
import app.atividade1.pvbf.MyBikePark.model.Parque;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DadosCadastradosFragment.DadosCastradosFragmentListener {
    FragmentManager fragmentManager;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configToolbarNavigationFABFragment();

    }

    public void configToolbarNavigationFABFragment() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_fragment, new UsuariosFragment()).commit();
        fab.setOnClickListener(v ->        fragmentManager.beginTransaction().replace(R.id.content_fragment, new TodosOsParquesFragment()).commit());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_perfil:
                fragmentManager.beginTransaction().replace(R.id.content_fragment, new MeuPerfilFragment()).commit();
                break;
            case R.id.action_alterar:
                fragmentManager.beginTransaction().replace(R.id.content_fragment, new AlterarMeusDadosFragment()).commit();
                break;
            case R.id.action_excluir:
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
                        .OnPositiveClicked(() -> {
                            Toast.makeText(getApplicationContext(), "Volte sempre e obrigado...", Toast.LENGTH_LONG).show();
                            finish();
                            return;
                        })
                        .OnNegativeClicked(() -> Toast.makeText(getApplicationContext(), "Divirta-se com as opções do aplicativo...", Toast.LENGTH_LONG).show())
                        .build();
                break;
            case R.id.action_sair:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_nav_dados_cadastrados:
                fragmentManager.beginTransaction().replace(R.id.content_fragment, new DadosCadastradosFragment(MainActivity.this)).commit();
                break;
            case R.id.menu_nav_importancia_bike:
                fragmentManager.beginTransaction().replace(R.id.content_fragment, new ImportanciaBikeFragment()).commit();
                break;
            case R.id.menu_nav_adicionar_parque:
                fragmentManager.beginTransaction().replace(R.id.content_fragment, new AdicionarParqueFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void getParque(Parque parque) {
        fragmentManager.beginTransaction().replace(R.id.content_fragment, new MapaFragment(parque)).commit();
    }
}
