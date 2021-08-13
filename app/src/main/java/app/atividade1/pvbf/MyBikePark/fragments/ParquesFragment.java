package app.atividade1.pvbf.MyBikePark.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.adapters.AdapterParques;
import app.atividade1.pvbf.MyBikePark.NAOSEIPRAQUESERVE.ParqueController;
import app.atividade1.pvbf.MyBikePark.adapters.ViewPagerAdapter;
import app.atividade1.pvbf.MyBikePark.model.Parque;

public class ParquesFragment extends Fragment implements AdapterParques.AdapterParquesListener {

    public ParquesFragment(ViewPagerAdapter viewPagerAdapter){
        this.listener = (ParquesFramentListener) viewPagerAdapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_parques, container, false);
        AdapterParques adapter = new AdapterParques(new ParqueController().listar(), getContext(), this);
        RecyclerView recyclerView = view.findViewById(R.id.rvParque);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);


        return view;
    }
    @Override
    public Parque getParque(Parque parque) {
        Toast.makeText(getContext(), ""+parque.getNome(), Toast.LENGTH_SHORT).show();
        listener.getParque(parque);
        return null;
    }
    private ParquesFramentListener listener;
    public interface ParquesFramentListener{
        public void getParque(Parque parque);
    }
}
