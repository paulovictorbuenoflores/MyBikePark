package app.atividade1.pvbf.MyBikePark.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.api.AppUtil;

import app.atividade1.pvbf.MyBikePark.view.usuario.LoginUserActivity;
import app.atividade1.pvbf.MyBikePark.view.usuario.MenuUserActivity;

public class SplashActivity extends AppCompatActivity {
    // Use qualquer número
    public static final int APP_PERMISSOES = 2020;

    // Array String com a lista de permissões necessárias
    String[] permissoesRequeridas = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    SharedPreferences preferences;
    ImageView imgCarregar;
    boolean isLembrarSenha = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        restaurarSheredPreferences();
        // salvarSharedPreferences();

        initFormulario();
        loadImagens();


        // Verificar as permissões
        if (validarPermissoes()) {
            iniciarApp();
        } else {

            Toast.makeText(this, "Por favor, verifique as permissões.", Toast.LENGTH_SHORT).show();
            validarPermissoes();

        }


    }

    public void initFormulario() {
        imgCarregar = findViewById(R.id.imgBackground);

    }

    private void iniciarApp() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (isLembrarSenha) {

                    intent = new Intent(SplashActivity.this, MenuUserActivity.class);

                } else {
                    intent = new Intent(SplashActivity.this, LoginUserActivity.class);

                    //startActivity(intent);
                }
                startActivity(intent);
                finish();
                // return;
            }
        }, AppUtil.Time_SPLASH);

    }

    private void salvarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();
        dados.putBoolean("loginAutomatico", false);
        dados.apply();
    }

    private void restaurarSheredPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        isLembrarSenha = preferences.getBoolean("loginAutomatico", false);

    }

    private void loadImagens() {

       /* Picasso.with (this)
                .load (AppUtil.URL_IMG_BACKGROUD)
                .resize (50, 50)
                .centerCrop ()
                .into (imgBackGround);*/
        // Picasso.get().load(AppUtil.URL_IMG_BACKGROUD).into(imgBackGround);
        Picasso.get().load(AppUtil.URL_IMGSPLASH).placeholder(R.drawable.carregando_animacao).into(imgCarregar);
        // Picasso.get().load(AppUtil.URL_IMG_LOGO).placeholder(R.drawable.carregando_animacao).into(imGLogo);
    }


    private boolean validarPermissoes() {

        int result;

        //Array para verificar se as permissoes forao autorizadas
        List<String> permissoesRequeridas = new ArrayList<>();

        // Adiciona as permissoes que o app  necessita
        for (String p : this.permissoesRequeridas) {
            result = ContextCompat.checkSelfPermission(SplashActivity.this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                permissoesRequeridas.add(p);
            }

        }

        //caso o array n esteja vazio significa que permissao para serem autorizas

        if (!permissoesRequeridas.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissoesRequeridas.toArray(new String[permissoesRequeridas.size()]), APP_PERMISSOES);
            return false;

        }

        return true;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}