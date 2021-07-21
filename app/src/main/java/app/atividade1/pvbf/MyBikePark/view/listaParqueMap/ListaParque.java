package app.atividade1.pvbf.MyBikePark.view.listaParqueMap;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.api.AdapterParques;
import app.atividade1.pvbf.MyBikePark.api.AppUtil;
import app.atividade1.pvbf.MyBikePark.controller.ParqueController;
import app.atividade1.pvbf.MyBikePark.model.ParquesORM;

public class ListaParque extends AppCompatActivity {
    List<ParquesORM> parqueList;
    AdapterParques adapter;
    ParquesORM ac, al, ap, am;
    RecyclerView recyclerView;

    ParqueController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_parque);

        salvarOBJ();
        recyclerView = findViewById(R.id.rvParque);

        parqueList = new ArrayList<>();


        // controller = new ClienteController(getApplicationContext());

        parqueList = controller.listar();


        adapter = new AdapterParques(parqueList, getApplicationContext());
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void salvarOBJ() {


        ImageView imageView = new ImageView(this);
        controller = new ParqueController(this);
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