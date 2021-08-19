package app.atividade1.pvbf.MyBikePark.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.Icon;

import java.util.concurrent.atomic.AtomicBoolean;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.controller.AppUtil;
import app.atividade1.pvbf.MyBikePark.controller.UsuarioController;
import app.atividade1.pvbf.MyBikePark.fragments.AdicionarParqueFragment;
import app.atividade1.pvbf.MyBikePark.fragments.AlterarMeusDadosFragment;
import app.atividade1.pvbf.MyBikePark.fragments.DadosCadastradosFragment;
import app.atividade1.pvbf.MyBikePark.fragments.EventosFragment;
import app.atividade1.pvbf.MyBikePark.fragments.ImportanciaBikeFragment;
import app.atividade1.pvbf.MyBikePark.fragments.MapaFragment;
import app.atividade1.pvbf.MyBikePark.fragments.MeuPerfilFragment;
import app.atividade1.pvbf.MyBikePark.fragments.TodosOsParquesFragment;
import app.atividade1.pvbf.MyBikePark.model.Parque;
import app.atividade1.pvbf.MyBikePark.model.Usuario;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DadosCadastradosFragment.DadosCastradosFragmentListener {
    FragmentManager fragmentManager;
    DrawerLayout drawer;

    private final int GALERIA_IMAGENS = 1;
    Bitmap bitmapGlobal;
    ImageView imageView;

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
        FloatingActionButton eventoFab = findViewById(R.id.evento_fab);
        drawer = findViewById(R.id.drawer_layout);
        //configura abrir fechar do drawer layout
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);


        imageView = navigationView.getHeaderView(0).findViewById(R.id.imgViewUser);
        TextView txtNomeUserteste = navigationView.getHeaderView(0).findViewById(R.id.txtNomeUserteste);
        TextView txtSalvarIMG = navigationView.getHeaderView(0).findViewById(R.id.txtNav_header_main_salvar_img);


        Usuario usuario = new Usuario();
        UsuarioController usuarioController = new UsuarioController();
        int id=-1;
       // Bundle bundle = getIntent().getExtras();
       // id = bundle.getInt("id");
        SharedPreferences sharedPreferences = getSharedPreferences(AppUtil.PREF_APP,MODE_PRIVATE);
        id=sharedPreferences.getInt("id",-1);
        usuario.setId(id);
        usuario = usuarioController.getById(usuario);
        txtNomeUserteste.setText(usuario.getPrimeiroNome());
        Usuario finalUsuario = usuario;
        txtSalvarIMG.setOnClickListener(view -> {

            finalUsuario.setImagem(AppUtil.convertImageViewToByteArray(imageView));
            usuarioController.update(finalUsuario);
            txtSalvarIMG.setText("Imagem Salva");
        });
        if (usuario.getImagem() != null) {
            imageView.setImageBitmap(AppUtil.getImage(usuario.getImagem()));
        }
        imageView.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, GALERIA_IMAGENS);

        });

        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_fragment, new EventosFragment(MainActivity.this)).commit();
        fab.setOnClickListener(v -> fragmentManager.beginTransaction().replace(R.id.content_fragment, new TodosOsParquesFragment()).commit());
        eventoFab.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AdicionarEventoActivity.class));
            finish();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id;
        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        UsuarioController usuarioController = new UsuarioController();
        Usuario usuario = new Usuario();
        usuario.setId(id);
        switch (item.getItemId()) {
            case R.id.action_perfil:
                fragmentManager.beginTransaction().replace(R.id.content_fragment, new MeuPerfilFragment()).commit();
                break;
            case R.id.action_alterar:
                fragmentManager.beginTransaction().replace(R.id.content_fragment, new AlterarMeusDadosFragment()).commit();
                break;
            case R.id.action_excluir:
                new FancyAlertDialog.Builder(MainActivity.this)
                        .setTitle("Excluir Conta")
                        .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
                        .setMessage("Deseja realmente excluir ?")
                        .setNegativeBtnText("Retornar")
                        .setPositiveBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
                        .setPositiveBtnText("Sim")
                        .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                        .setAnimation(Animation.POP)
                        .isCancellable(true)
                        .setIcon(R.drawable.ic_star_border_black_24dp, Icon.Visible)
                        .OnPositiveClicked(() -> {
                            Toast.makeText(getApplicationContext(), "Volte sempre e obrigado...", Toast.LENGTH_LONG).show();
                            usuarioController.deletar(usuario);
                            finish();
                            return;
                        })
                        .OnNegativeClicked(() -> Toast.makeText(getApplicationContext(), "Divirta-se com as opções do aplicativo...", Toast.LENGTH_LONG).show())
                        .build();

                break;
            case R.id.action_sair:

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

    @Override
    public void onBackPressed() {
        // super.onBackPressed();

        Toast.makeText(this, "onBackPressed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALERIA_IMAGENS) {
            if (resultCode == RESULT_OK && requestCode == 1) {
                Uri imagemSelecionada = data.getData();

                String[] colunas = {MediaStore.Images.Media.DATA};

                Cursor cursor = getApplicationContext().getContentResolver().query(imagemSelecionada, colunas, null, null, null);
                cursor.moveToFirst();
                int indexColuna = cursor.getColumnIndex(colunas[0]);
                String pathImg = cursor.getString(indexColuna);
                cursor.close();

                Bitmap bitmap = BitmapFactory.decodeFile(pathImg);
                bitmapGlobal = bitmap;
                imageView.setImageBitmap(bitmap);

            }

        }
    }

}
