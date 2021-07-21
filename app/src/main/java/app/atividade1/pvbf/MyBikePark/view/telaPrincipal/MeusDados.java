package app.atividade1.pvbf.MyBikePark.view.telaPrincipal;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import app.atividade1.pvbf.MyBikePark.api.AppUtil;
import app.atividade1.pvbf.MyBikePark.controller.ClienteORMController;
import app.atividade1.pvbf.MyBikePark.model.ClienteORM;

import static android.content.Context.MODE_PRIVATE;

public class MeusDados extends Fragment {

    View view;

    TextView txtTitulo;
    Button btVoltar;
    //cli vip
    EditText editPrimeiroNomeMydb, editSobreNomeMydb;
    CheckBox ckPessoaFisicaVIPMydb;
    //cli PF
    EditText editCPFPF, editNomeCompleto, editSobreNomeVipPFMydb;

    //cli PJ
    EditText editCNPJMydb, editRazaoSocialMydb, editDataAberturaMydb;
    CheckBox checSimplesNacionalMydb, checMEIMydb;
    //credenciais
    EditText editEmailMydb, editSenhaMydb;
    CheckBox checkBoxTermoMydb;

    ClienteORMController clienteORMController;

    //metodo Cliente tem acesso a cliente PF e PJ
    ClienteORM cliente;
    // ClientePF clientePF;

    int clienteID,id;
    boolean isPessoaFisica;
    SharedPreferences preferences;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_meus_dados, container, false);

        iniciarComponentesDeLayout();
popularFormulario();
        //escutarEventosDosBotoes();


        return view;
    }

    /**
     * Inicializar os componentes da tela/layout
     * para adicionar os clientes
     */
    private void iniciarComponentesDeLayout() {
        editPrimeiroNomeMydb = view.findViewById(R.id.editPrimeiroNomeMydb);

        editSobreNomeMydb = view.findViewById(R.id.editSobreNomeMydb);

        ///////////////
        editEmailMydb =view.findViewById(R.id.editEmailMydb);

        editSenhaMydb = view.findViewById(R.id.editSenhaMydb);

        checkBoxTermoMydb = view.findViewById(R.id.checkBoxTermoMydb);

        clienteORMController = new ClienteORMController();

        cliente = new ClienteORM();

        cliente.setId(clienteID);



    }
    private void popularFormulario() {


            //TODO: PEGAR ID CLIENTE
            cliente =  buscarCliente(1);


            editPrimeiroNomeMydb.setText(cliente.getPrimeiroNome());
            editSobreNomeMydb.setText(cliente.getSobreNome());
            editEmailMydb.setText(cliente.getEmail());
            editSenhaMydb.setText(cliente.getSenha());




            checkBoxTermoMydb.setChecked(true);

    }
    private void escutarEventosDosBotoes() {

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MeusDadosActivity.this, MenuUserActivity.class);
                //startActivity(intent);
                //finish();

            }
        });




    }
    private ClienteORM buscarCliente(int id) {
        ClienteORM obj=new ClienteORM();
        clienteORMController =new ClienteORMController();
        obj.setId(id);

        obj=clienteORMController.getById(obj);



        return obj;
    }

}