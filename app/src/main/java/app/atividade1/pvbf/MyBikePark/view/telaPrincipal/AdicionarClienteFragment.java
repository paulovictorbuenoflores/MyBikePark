package app.atividade1.pvbf.MyBikePark.view.telaPrincipal;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.controller.ClienteController;
import app.atividade1.pvbf.MyBikePark.model.Cliente;


public class AdicionarClienteFragment extends Fragment {

    View view;

    TextView txtTitulo;

    EditText editNomeCompleto;
    EditText editTelefone;
    EditText editEmail;
    EditText editCep;
    EditText editLogradouro;
    EditText editNumero, editBairro, editCidade, editEstado;

    CheckBox chkTermosDeUso;
    Button btnCancelar, btnSalvar;

    Cliente novoCliente;
    ClienteController clienteController;

    public AdicionarClienteFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_adicionar_cliente, container, false);

        iniciarComponentesDeLayout();

        escutarEventosDosBotoes();


        return view;
    }

    /**
     * Inicializar os componentes da tela/layout
     * para adicionar os clientes
     */
    private void iniciarComponentesDeLayout() {

        txtTitulo = view.findViewById(R.id.txtTitulo);
        txtTitulo.setText(R.string.novoCliente);

        editNomeCompleto = view.findViewById(R.id.editNomeCompleto);
        editTelefone = view.findViewById(R.id.editTelefone);
        editEmail = view.findViewById(R.id.editEmail);
        editCep = view.findViewById(R.id.editCep);
        editLogradouro = view.findViewById(R.id.editLogradouro);
        editNumero = view.findViewById(R.id.editNumero);
        editBairro = view.findViewById(R.id.editBairro);
        editCidade = view.findViewById(R.id.editCidade);
        editEstado = view.findViewById(R.id.editEstado);

        chkTermosDeUso = view.findViewById(R.id.chkTermosDeUso);

        btnCancelar = view.findViewById(R.id.btnCancelar);
        btnSalvar = view.findViewById(R.id.btnSalvar);

        novoCliente = new Cliente();

        clienteController = new ClienteController(getContext());


    }

    private void escutarEventosDosBotoes() {

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isDadosOK = true;

                if(TextUtils.isEmpty(editNomeCompleto.getText())){
                    isDadosOK = false;
                    editNomeCompleto.setError("Digite o nome completo...");
                    editNomeCompleto.requestFocus();
                }

                if(TextUtils.isEmpty(editTelefone.getText())){
                    isDadosOK = false;
                    editTelefone.setError("Digite o telefone...");
                    editTelefone.requestFocus();
                }

                if(TextUtils.isEmpty(editEmail.getText())){
                    isDadosOK = false;
                    editEmail.setError("Digite o email...");
                    editEmail.requestFocus();
                }

                if(TextUtils.isEmpty(editCep.getText())){
                    isDadosOK = false;
                    editCep.setError("Digite o cep...");
                    editCep.requestFocus();
                }

                if(TextUtils.isEmpty(editLogradouro.getText())){
                    isDadosOK = false;
                    editLogradouro.setError("Digite o logradouro (av, rua...)");
                    editLogradouro.requestFocus();
                }

                if(TextUtils.isEmpty(editNumero.getText())){
                    isDadosOK = false;
                    editNumero.setError("Digite o número...");
                    editNumero.requestFocus();
                }

                if(TextUtils.isEmpty(editBairro.getText())){
                    isDadosOK = false;
                    editBairro.setError("Digite o bairro...");
                    editBairro.requestFocus();
                }

                if(TextUtils.isEmpty(editCidade.getText())){
                    isDadosOK = false;
                    editCidade.setError("Digite a cidade...");
                    editCidade.requestFocus();
                }

                if(TextUtils.isEmpty(editEstado.getText())){
                    isDadosOK = false;
                    editEstado.setError("Digite o estado (UF)");
                    editEstado.requestFocus();
                }


                if(isDadosOK) {

                    // popular os dados no objeto cliente.

                    novoCliente.setPrimeiroNome(editNomeCompleto.getText().toString());

                    novoCliente.setEmail(editEmail.getText().toString());


                    clienteController.incluir(novoCliente);
                    Log.i("log_add_cliente", "onClick: Dados Corretos....");
                }else{
                    // Notificar o usuário
                    // TOAST
                    // Push Notification
                    // AlertDialog

                    Log.e("log_add_cliente", "onClick: Dados incorreto....");
                }



            }
        });
    }


}
