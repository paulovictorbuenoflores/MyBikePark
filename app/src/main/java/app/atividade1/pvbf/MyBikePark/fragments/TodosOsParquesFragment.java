package app.atividade1.pvbf.MyBikePark.fragments;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import app.atividade1.pvbf.MyBikePark.NAOSEIPRAQUESERVE.ParqueController;
import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.model.Parque;


public class TodosOsParquesFragment extends Fragment implements OnMapReadyCallback {
    View view;
    GoogleMap mMap;
    LocationManager locationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_todos_os_parques, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getActivity().getApplication().getSystemService(Context.LOCATION_SERVICE);
        return view;
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ParqueController parqueController = new ParqueController();
        List<Parque> parqueList = parqueController.listar();
        for(Parque parque : parqueList){
            LatLng localizacao = new LatLng(parque.getLatitude(), parque.getLongitude());
            mMap.addMarker(new MarkerOptions().position(localizacao).title(parque.getNome()));
        }
    }
}
