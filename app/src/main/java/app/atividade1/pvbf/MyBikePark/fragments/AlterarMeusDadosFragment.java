package app.atividade1.pvbf.MyBikePark.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import app.atividade1.pvbf.MyBikePark.model.Usuario;
import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.controller.UsuarioController;

import static android.content.Context.MODE_PRIVATE;

//TODO: MODIFICANDO TELA PARA ALTERAR MEUS DADOS
public class AlterarMeusDadosFragment extends Fragment {
    View view;
    EditText editPrimeiroNomeMydb, editSobreNomeMydb, editConfirmarSenha;
    EditText editEmailMydb, editSenhaMydb;
    CheckBox checkBoxTermoMydb;
    Button btEditar, btEditarCredenciais, btSalvar, btSalvarCredenciais;
    UsuarioController usuarioController;
    Usuario cliente;
    int clienteID;


    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_alterar_meus_dados, container, false);
        iniciarComponentesDeLayout();
        popularFormulario();

        btEditar.setOnClickListener(v ->{
            editPrimeiroNomeMydb.setEnabled(true);
            editSobreNomeMydb.setEnabled(true);
        });

        btEditarCredenciais.setOnClickListener(v ->{
            editEmailMydb.setEnabled(true);
            editSenhaMydb.setEnabled(true);
            editConfirmarSenha.setEnabled(true);
        });

        if(editPrimeiroNomeMydb.getText().toString() != "" && editSobreNomeMydb.getText().toString() != ""){
            btSalvar.setEnabled(true);
        }

        if(editEmailMydb.getText().toString() != "" && editSenhaMydb.getText().toString() != "" && editConfirmarSenha.getText().toString() != ""){
            btSalvarCredenciais.setEnabled(true);
        }
        UsuarioController usuarioController =new UsuarioController();
        Usuario usuario =new Usuario();
        btSalvar.setOnClickListener(view -> {
           usuario.setPrimeiroNome(editPrimeiroNomeMydb.getText().toString());
           usuario.setSobreNome(editSobreNomeMydb.getText().toString());
           usuarioController.update(usuario);
        });

        btSalvarCredenciais.setOnClickListener(view -> {
            usuario.setEmail(editEmailMydb.getText().toString());
            usuario.setSenha(editSenhaMydb.getText().toString());
            usuarioController.update(usuario);
        });
        return view;
    }

    private void iniciarComponentesDeLayout() {
        editPrimeiroNomeMydb = view.findViewById(R.id.editPrimeiroNomeMydb);
        editSobreNomeMydb = view.findViewById(R.id.editSobreNomeMydb);
        editEmailMydb = view.findViewById(R.id.editEmailMydb);
        editSenhaMydb = view.findViewById(R.id.editSenhaMydb);
        editConfirmarSenha = view.findViewById(R.id.editConfirmarSenha);
        btEditar = view.findViewById(R.id.btEditar);
        btEditarCredenciais = view.findViewById(R.id.btEditarCredenciais);
        btSalvar = view.findViewById(R.id.buttonSalvar);
        btSalvarCredenciais = view.findViewById(R.id.btSalvarCredenciais);

        checkBoxTermoMydb = view.findViewById(R.id.checkBoxTermoMydb);
        usuarioController = new UsuarioController();
        cliente = new Usuario();
        cliente.setId(clienteID);
    }

    private void popularFormulario() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("app_ExpLenha", MODE_PRIVATE);
        String email = sharedPreferences.getString("Email", "dfsd");
        String senha = sharedPreferences.getString("Senha", "pass");

        Toast.makeText(getContext(), ""+email, Toast.LENGTH_LONG).show();

        UsuarioController usuarioController = new UsuarioController();
        Usuario usuario = usuarioController.buscaEmailSenha(email,senha);
        editPrimeiroNomeMydb.setText(usuario.getPrimeiroNome());
        editSobreNomeMydb.setText(usuario.getSobreNome());
        editEmailMydb.setText(usuario.getEmail());
        editSenhaMydb.setText(usuario.getSenha());
        checkBoxTermoMydb.setChecked(true);
    }

}
