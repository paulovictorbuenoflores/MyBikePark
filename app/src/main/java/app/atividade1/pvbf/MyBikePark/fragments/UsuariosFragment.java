package app.atividade1.pvbf.MyBikePark.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import app.atividade1.pvbf.MyBikePark.controller.UsuarioController;
import app.atividade1.pvbf.MyBikePark.R;


public class UsuariosFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_usuarios, container, false);
        EditText editPesquisarNome = view.findViewById(R.id.editPesquisarNome);
        ListView listView = view.findViewById(R.id.listView);


        UsuarioController usuarioController = new UsuarioController();
        String[] usuarios = usuarioController.listarPorNome();

        ArrayAdapter<String> clienteAdapter = new ArrayAdapter<>(getContext(),
                R.layout.item_usuario,
                R.id.txtItemLista, usuarios);
        listView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        listView.setAdapter(clienteAdapter);

        editPesquisarNome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence filtro, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                clienteAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return view;
    }


}
