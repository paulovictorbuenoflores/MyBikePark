package app.atividade1.pvbf.MyBikePark.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.Icon;

import java.util.List;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.activities.AdicionarEventoActivity;
import app.atividade1.pvbf.MyBikePark.activities.MainActivity;
import app.atividade1.pvbf.MyBikePark.adapters.EventoAdapter;
import app.atividade1.pvbf.MyBikePark.controller.AppUtil;
import app.atividade1.pvbf.MyBikePark.model.Evento;
import app.atividade1.pvbf.MyBikePark.controller.EventoController;

import static android.content.Context.MODE_PRIVATE;


public class EventosFragment extends Fragment {

    private EventoAdapter adapter;
    private List<Evento> eventos;
    private EventoController eventoController;
    Context context;
    public EventosFragment(Context context){
        this.context = context;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eventos, container, false);


        eventoController = new EventoController();


        TextView imageView = view.findViewById(R.id.imEvento);
        eventos = eventoController.listar();
        if(eventos.size()==0){
            imageView.setVisibility(TextView.VISIBLE);
        }
        adapter = new EventoAdapter(getContext(), eventos);
        RecyclerView recyclerView = view.findViewById(R.id.rvEventos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.evento_fab);
        floatingActionButton.setOnClickListener(view1 -> {

            startActivity(new Intent(getContext(), AdicionarEventoActivity.class));
            getActivity().finish();
        });

        return view;
    }
}