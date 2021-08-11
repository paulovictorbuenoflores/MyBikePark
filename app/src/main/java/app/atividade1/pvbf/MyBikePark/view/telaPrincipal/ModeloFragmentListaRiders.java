package app.atividade1.pvbf.MyBikePark.view.telaPrincipal;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.fragment.app.Fragment;

import app.atividade1.pvbf.MyBikePark.R;


public class ModeloFragmentListaRiders extends Fragment {

    View view;

    public ModeloFragmentListaRiders() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_preto, container, false);

        //TextView txtTitulo = view.findViewById(R.id.txtTitulo);

       // txtTitulo.setText(R.string.modelo_fragment);


        //txtTitulo.setTextColor(ColorStateList.valueOf(Color.CYAN));

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));

        return view;
    }


}
