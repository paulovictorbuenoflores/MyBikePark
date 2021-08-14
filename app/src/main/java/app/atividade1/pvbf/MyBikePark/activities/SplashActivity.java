package app.atividade1.pvbf.MyBikePark.activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.controller.AppUtil;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    private final int TEMPO_SPLASH_SCREEN = 3;
    private String[] permissoesRequeridas = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION};
    private static final int APP_PERMISSOES = 2020;
    private boolean isLembrarSenha = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        restaurarSheredPreferences();
        verificarPermissoes();
    }
    private void verificarPermissoes(){
        if (validarPermissoes()) iniciarApp();
        else validarPermissoes();
    }
    private boolean validarPermissoes() {
        int result;
        List<String> permissoesAceitas = new ArrayList<>();
        for (String p : permissoesRequeridas) {
            result = ContextCompat.checkSelfPermission(SplashActivity.this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                permissoesAceitas.add(p);
            }
        }

        if (!permissoesAceitas.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissoesAceitas.toArray(new String[permissoesAceitas.size()]), APP_PERMISSOES);
            return false;
        }
        return true;

    }
    private void iniciarApp() {
        new Handler().postDelayed(() -> {
            Intent intent;
            if (isLembrarSenha) {
                intent = new Intent(SplashActivity.this, MainActivity.class);
            } else {
                intent = new Intent(SplashActivity.this, LoginUserActivity.class);
            }
            startActivity(intent);
            finish();
        }, TEMPO_SPLASH_SCREEN * 1000);
    }
    private void restaurarSheredPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        isLembrarSenha = preferences.getBoolean("loginAutomatico", false);
    }
}