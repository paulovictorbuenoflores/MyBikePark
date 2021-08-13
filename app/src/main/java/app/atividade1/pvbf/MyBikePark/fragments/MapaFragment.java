package app.atividade1.pvbf.MyBikePark.fragments;


import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.adapters.ViewPagerAdapter;
import app.atividade1.pvbf.MyBikePark.model.Parque;

public class MapaFragment extends Fragment implements OnMapReadyCallback {
    View view;
    GoogleMap mMap;
    LocationManager locationManager;
    private Parque parque;
    public MapaFragment(Parque parque){
        this.parque = parque;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mapa, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getActivity().getApplication().getSystemService(Context.LOCATION_SERVICE);
        return view;
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng localizacao = new LatLng(parque.getLatitude(), parque.getLongitude());
        mMap.addMarker(new MarkerOptions().position(localizacao).title(parque.getNome()));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(localizacao, 8));
    }
}
