package app.atividade1.pvbf.MyBikePark.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.api.AppUtil;
import app.atividade1.pvbf.MyBikePark.view.usuario.LoginUserActivity;

public class Main02Activity extends AppCompatActivity {
    SharedPreferences preferences;
    ImageView imGLogo, imgBackGround;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_n_usa_fragmento);

        initForm();
        loadImagens();
        iniciarApp();


    }

    private void initForm() {

        imgBackGround = findViewById(R.id.imgBackground);
        imGLogo = findViewById(R.id.imgLogo);
    }

    private void salvarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();


        //dados.putString("Email", );
    }

    private void restaurarSharedPreferences() {
    }

    private void iniciarApp() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Main02Activity.this, LoginUserActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        }, AppUtil.Time_SPLASH);

    }

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
}