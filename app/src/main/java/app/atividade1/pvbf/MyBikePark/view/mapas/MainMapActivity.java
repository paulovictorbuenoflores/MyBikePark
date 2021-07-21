package app.atividade1.pvbf.MyBikePark.view.mapas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.controller.ParqueController;
import app.atividade1.pvbf.MyBikePark.model.ParquesORM;

public class MainMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mMap;

    String[] permissoesRequiridas = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    public static final int APP_PERMISSOES_ID = 2021;

    double latitude, longitude;
    TextView txtLatitude, txtLongitude;

    //1 passo verificar se a localizacao está ativa
    LocationManager locationManager;
    boolean gpsAtivo = false;

    //obj Parque
    ParqueController parqueController;
    ParquesORM parquesORM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);

        initformulario();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //2 conferir se os servidores de localizacao estao disponiveis, via LocationManeger
        locationManager = (LocationManager) getApplication().getSystemService(Context.LOCATION_SERVICE);
        gpsAtivo = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);//verificar se o gps ta ou n ativo
        //para obter a cordenada GPS precisamos ver se o GPS esta ativo
        if (gpsAtivo) {
            obterCoordenadas();

        } else {

            latitude = 0.00;
            longitude = 0.00;

            txtLatitude.setText(String.valueOf(latitude));
            txtLongitude.setText(String.valueOf(longitude));

            Toast.makeText(this, "Coordenadas não disponiveis", Toast.LENGTH_LONG).show();
        }

    }

    private void initformulario() {
        txtLatitude = findViewById(R.id.txtValorLatitude);
        txtLongitude = findViewById(R.id.txtValorLongitude);
        parqueController =new ParqueController(this);
        parquesORM=new ParquesORM();
    }

    private void obterCoordenadas() {
        //primeiro verificar se tem permissao para acessar o GPS
        boolean permissaoAtiva = solicitarPermissaoParaObterLocalizacao();
        if (permissaoAtiva) {
            capturarUltimaLocalizacaoValida();

        }

    }

    private boolean solicitarPermissaoParaObterLocalizacao() {


        Toast.makeText(this, "Verificando permissão...", Toast.LENGTH_LONG).show();

        List<String> permissoesNegadas = new ArrayList<>();

        int permissaoNegada;

        for (String permissao : this.permissoesRequiridas) {
            permissaoNegada = ContextCompat.checkSelfPermission(MainMapActivity.this, permissao);
            if (permissaoNegada != PackageManager.PERMISSION_GRANTED) {
                permissoesNegadas.add(permissao);

            }

        }
        if (!permissoesNegadas.isEmpty()) {

            ActivityCompat.requestPermissions(MainMapActivity.this, permissoesNegadas.toArray(new String[permissoesNegadas.size()]), APP_PERMISSOES_ID);

            return false;
        } else {

            return true;
        }


    }

    private void capturarUltimaLocalizacaoValida() {
        parquesORM.setId(1);
        parquesORM= parqueController.getById(parquesORM);

        @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {
            //devolver cordenadas GPS
            //Geopoint
            latitude = parquesORM.getLatitude();
            longitude = parquesORM.getLongitude();


        }



       // parqueController.
        txtLatitude.setText(String.valueOf(formatarGeopoint(parquesORM.getLatitude())));
        txtLongitude.setText(String.valueOf(formatarGeopoint(parquesORM.getLongitude())));

        Toast.makeText(this, "Coordenadas obtidas com sucesso", Toast.LENGTH_LONG).show();

    }

    private String formatarGeopoint(double valor) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#####");

        return decimalFormat.format(valor);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng localizacaoDoCeulular = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(localizacaoDoCeulular).title("Nore Bike Parque"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(localizacaoDoCeulular));

    }
}