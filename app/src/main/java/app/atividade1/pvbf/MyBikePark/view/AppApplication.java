package app.atividade1.pvbf.MyBikePark.view;

import android.app.Application;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;
public class AppApplication extends Application {

    public static final String DB_NAME = "MyBikePark.realm";
    public static final int DB_VERSION = 1;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(DB_NAME)
                .schemaVersion(DB_VERSION)
                .allowWritesOnUiThread(true)//execulta em segundo plano uando precisa
                .build();

        Realm realm = Realm.getInstance(configuration);//nesse momento ele cria um objeto que tem o padrao singleton, não corro o risco de ter dois objetos aberto durante a aplicacoa
        Log.d("db_log", "onCreate: Realm com sucesso: " + DB_VERSION + "versão" + DB_VERSION);


    }
}

