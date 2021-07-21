package app.atividade1.pvbf.MyBikePark.view.telaPrincipal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.api.AdapterParques;
import app.atividade1.pvbf.MyBikePark.api.AppUtil;
import app.atividade1.pvbf.MyBikePark.controller.ParqueController;
import app.atividade1.pvbf.MyBikePark.model.ParquesORM;

import static io.realm.Realm.getApplicationContext;


public class ListaDeParquesFragmentoRecyclerView extends Fragment {

    List<ParquesORM> parqueList;
    AdapterParques adapter;
    ParquesORM ac, al, ap, am;
    RecyclerView recyclerView;

    ParqueController controller;

    // Fragment - Classe responsável pela camada de VISÃO (Layout)
    View view;

    public ListaDeParquesFragmentoRecyclerView() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.lista_de_parques_fragmento_recycler_view, container, false);

        iniciarComponentesDeLayout();



        return view;
    }

    private void iniciarComponentesDeLayout() {
        controller = new ParqueController(getApplicationContext());
        recyclerView = view.findViewById(R.id.rvParque);


        //salvarOBJ();

        parqueList = new ArrayList<>();


        // controller = new ClienteController(getApplicationContext());

        parqueList = controller.listar();


        adapter = new AdapterParques(parqueList, getContext());
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    private void salvarOBJ() {


        ImageView imageView = new ImageView(getApplicationContext());

        imageView.setImageResource(R.drawable.acre);

        ac = new ParquesORM();
        ac.setNome("NoreBikeParque");
        ac.setLongitude(-47.30638);
        ac.setLatitude(-23.380885);
        ac.setFoto(AppUtil.convertImageViewToByteArray(imageView));


        al = new ParquesORM();
        imageView.setImageResource(R.drawable.acre);
        al.setNome("NoreBikeParque");
        al.setLongitude(-47.30638);
        al.setLatitude(-23.380885);
        al.setFoto(AppUtil.convertImageViewToByteArray(imageView));

        ap = new ParquesORM();

        imageView.setImageResource(R.drawable.acre);
        ap.setNome("NoreBikeParque");
        ap.setLongitude(-47.30638);
        ap.setLatitude(-23.380885);
        ap.setFoto(AppUtil.convertImageViewToByteArray(imageView));

        am = new ParquesORM();

        imageView.setImageResource(R.drawable.acre);
        am.setNome("NoreBikeParque");
        am.setLongitude(-47.30638);
        am.setLatitude(-23.380885);
        am.setFoto(AppUtil.convertImageViewToByteArray(imageView));

        controller.insert(ac);
        controller.insert(al);
        controller.insert(ap);
        controller.insert(am);

    }

}
