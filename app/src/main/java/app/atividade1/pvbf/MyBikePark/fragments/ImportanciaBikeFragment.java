package app.atividade1.pvbf.MyBikePark.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import app.atividade1.pvbf.MyBikePark.R;


public class ImportanciaBikeFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_importancia_bike, container, false);
    }
}
