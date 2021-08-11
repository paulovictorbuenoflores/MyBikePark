package app.atividade1.pvbf.MyBikePark.view.telaPrincipal;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.List;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.controller.ClienteController;
import app.atividade1.pvbf.MyBikePark.model.Cliente;


public class ListarClientesFragment extends Fragment {

    View view;

    EditText editPesquisarNome;

    ListView listView;

    // Clientes salvos no banco de dados
    List<Cliente> clienteList;

    // Clientes listados na tela (VIEW)
    List<String> clientes;

    ArrayAdapter<String> clienteAdapter;

    ClienteController clienteController;

    public ListarClientesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_listar_clientes, container, false);

        TextView txtTitulo = view.findViewById(R.id.txtTitulo);

        txtTitulo.setText(R.string.fragmento_listar_clientes);

        txtTitulo.setTextColor(ColorStateList.valueOf(Color.RED));

        clienteController = new ClienteController(getContext());

        listView = view.findViewById(R.id.listView);

        editPesquisarNome = view.findViewById(R.id.editPesquisarNome);

        // clienteList = new ArrayList<>();
        clienteList = clienteController.listar();

        //clientes = new ArrayList<>();
        clientes = clienteController.gerarListaDeClientesListView();

        clienteAdapter = new ArrayAdapter<>(getContext(),
                R.layout.item_usuario,
                R.id.txtItemLista, clientes);

        listView.setAdapter(clienteAdapter);

        editPesquisarNome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence filtro, int start, int count, int after) {

                ListarClientesFragment.this.clienteAdapter.getFilter().filter(filtro);

                Log.i("add_ListView", "beforeTextChanged: "+filtro);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return view;
    }


}
