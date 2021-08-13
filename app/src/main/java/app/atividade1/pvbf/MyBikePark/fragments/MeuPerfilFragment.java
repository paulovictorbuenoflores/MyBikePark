package app.atividade1.pvbf.MyBikePark.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import app.atividade1.pvbf.MyBikePark.NAOSEIPRAQUESERVE.AppUtil;
import app.atividade1.pvbf.MyBikePark.NAOSEIPRAQUESERVE.UsuarioController;
import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.model.Usuario;

import static android.content.Context.MODE_PRIVATE;

public class MeuPerfilFragment extends Fragment {

    private View view;
    private TextView editPrimeiroNomeMydb, editSobreNomeMydb, editEmailMydb, editSenhaMydb;
    private CheckBox checkBoxTermoMydb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_meu_perfil, container, false);
        iniciarComponentesDeLayout();
       popularFormulario();
        return view;
    }

    private void iniciarComponentesDeLayout() {
        editPrimeiroNomeMydb = view.findViewById(R.id.editPrimeiroNomeMydb);
        editSobreNomeMydb = view.findViewById(R.id.editSobreNomeMydb);
        editEmailMydb = view.findViewById(R.id.editEmailMydb);
        editSenhaMydb = view.findViewById(R.id.editSenhaMydb);
        checkBoxTermoMydb = view.findViewById(R.id.checkBoxTermoMydb);
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