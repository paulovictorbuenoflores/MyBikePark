package app.atividade1.pvbf.MyBikePark.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import app.atividade1.pvbf.MyBikePark.controller.AppUtil;
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
    Boolean isFormularioOK;
    byte[] img;
    int clienteID;
    int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_alterar_meus_dados, container, false);
        UsuarioController usuarioController = new UsuarioController();
        Usuario usuario = new Usuario();
        iniciarComponentesDeLayout();
        popularFormulario();

        btEditar.setOnClickListener(v -> {
            editPrimeiroNomeMydb.setEnabled(true);
            editSobreNomeMydb.setEnabled(true);
        });
        btEditarCredenciais.setOnClickListener(v -> {
            editEmailMydb.setEnabled(true);
            editSenhaMydb.setEnabled(true);
            editConfirmarSenha.setEnabled(true);
        });

        efeitosbt();

        btSalvar.setOnClickListener(view -> {
            isFormularioOK = true;
            if (TextUtils.isEmpty(editPrimeiroNomeMydb.getText().toString())) {
                isFormularioOK = false;
                editPrimeiroNomeMydb.setError("*");
                editPrimeiroNomeMydb.requestFocus();

            }
            if (TextUtils.isEmpty(editSobreNomeMydb.getText().toString())) {
                isFormularioOK = false;
                editSobreNomeMydb.setError("*");
                editSobreNomeMydb.requestFocus();

            }
            if (isFormularioOK) {

                usuario.setPrimeiroNome(editPrimeiroNomeMydb.getText().toString());
                usuario.setSobreNome(editSobreNomeMydb.getText().toString());
                usuario.setEmail(editEmailMydb.getText().toString());
                usuario.setSenha(editSenhaMydb.getText().toString());
                usuario.setImagem(img);
                usuario.setId(id);

                usuarioController.update(usuario);
                btSalvar.setEnabled(false);
                editPrimeiroNomeMydb.setEnabled(false);
                editSobreNomeMydb.setEnabled(false);

                efeitosbt();
                btSalvar.setText("Alterados");

            }

        });

        btSalvarCredenciais.setOnClickListener(view -> {
        isFormularioOK =true;
            if (TextUtils.isEmpty(editEmailMydb.getText().toString())) {
                isFormularioOK = false;
                editEmailMydb.setError("*");
                editEmailMydb.requestFocus();
            }
            if (TextUtils.isEmpty(editSenhaMydb.getText().toString())) {
                isFormularioOK = false;
                editSobreNomeMydb.setError("*");
                editSobreNomeMydb.requestFocus();

            }
            if (TextUtils.isEmpty(editConfirmarSenha.getText().toString())) {
                isFormularioOK = false;
                editConfirmarSenha.setError("*");
                editConfirmarSenha.requestFocus();

            }
            if (isFormularioOK) {

                if (!validarSenha()) {
                    isFormularioOK = false;
                    Toast.makeText(getContext(), "As senhas não conferem", Toast.LENGTH_SHORT).show();
                    editSenhaMydb.setError("*");
                    editSenhaMydb.requestFocus();
                    editConfirmarSenha.setError("*");

                } else {
                    usuario.setPrimeiroNome(editPrimeiroNomeMydb.getText().toString());
                    usuario.setSobreNome(editSobreNomeMydb.getText().toString());
                    usuario.setEmail(editEmailMydb.getText().toString());
                    usuario.setSenha(AppUtil.gerarMD5Hash(editSenhaMydb.getText().toString()));
                    usuario.setImagem(img);
                    usuario.setId(id);
                    usuarioController.update(usuario);
                    btSalvarCredenciais.setEnabled(false);
                    editEmailMydb.setEnabled(false);
                    editSenhaMydb.setEnabled(false);
                    editConfirmarSenha.setEnabled(false);
                    efeitosbt();
                    btSalvarCredenciais.setText("Alterados");
                    savarSharedPreferences();

                }

            }


        });
        return view;
    }

    private void savarSharedPreferences() {
        SharedPreferences preferences;
        preferences = getActivity().getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        ;
        dados.putString("Email", editEmailMydb.getText().toString());
        dados.putString("Senha", editConfirmarSenha.getText().toString());

        dados.apply();
    }

    private void efeitosbt() {

        if (editPrimeiroNomeMydb.getText().toString() != "" && editSobreNomeMydb.getText().toString() != "") {
            btSalvar.setEnabled(true);
            btSalvar.setText("SALVAR");
        }

        if (editEmailMydb.getText().toString() != "" && editSenhaMydb.getText().toString() != "" && editConfirmarSenha.getText().toString() != "") {
            btSalvarCredenciais.setEnabled(true);
            btSalvarCredenciais.setText("SALVAR");
        }

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

        UsuarioController usuarioController = new UsuarioController();
        Usuario usuario = usuarioController.buscaEmailSenha(email, senha);
        editPrimeiroNomeMydb.setText(usuario.getPrimeiroNome());
        editSobreNomeMydb.setText(usuario.getSobreNome());
        editEmailMydb.setText(usuario.getEmail());
        editSenhaMydb.setText(usuario.getSenha());
        editConfirmarSenha.setText(usuario.getSenha());
        checkBoxTermoMydb.setChecked(true);
        id = usuario.getId();
        img = usuario.getImagem();
    }

    public boolean validarSenha() {
        boolean retorno = false;
        String senhaA = editSenhaMydb.getText().toString(), senhaB = editConfirmarSenha.getText().toString();
        if (senhaA.equals(senhaB)) {
            retorno = true;
        }
        return retorno;
    }
}
