package app.atividade1.pvbf.MyBikePark.view.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.api.AppUtil;
import app.atividade1.pvbf.MyBikePark.controller.ClienteController;
import app.atividade1.pvbf.MyBikePark.model.Cliente;
import app.atividade1.pvbf.MyBikePark.view.Main02Activity;

public class AtualizarMeusDadosActivity extends AppCompatActivity {
    Button btVoltar;
    //card cliente
    Button btSalvarClienteVIP, btSalvarPF, btSalvarPJ, btSalvarCredenciais;
    Button btEditar, btEditarPF, btEditarPJ, btEditarCredenciais;
    CheckBox checkBoxTermo;

    //cli vip
    EditText editPrimeiroNomeMydb, editSobreNomeMydb;
    CheckBox ckPessoaFisicaVIPMydb;
    //cli PF
    EditText editCPFPF, editNomeCompleto, editSobreNomeVipPFMydb, editTextConfirmarSenha;

    //cli PJ
    EditText editCNPJMydb, editRazaoSocialMydb, editDataAberturaMydb;
    CheckBox checSimplesNacionalMydb, checMEIMydb;
    //credenciais
    EditText editEmailMydb, editSenhaMydb;
    CheckBox checkBoxTermoMydb;

    ClienteController controller;

    //metodo Cliente tem acesso a cliente PF e PJ
    Cliente cliente;

    //imagem
    ImageView imageView;

    int clienteID;
    boolean isPessoaFisica;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_meus_dados);

        restaurarSharedPreferences();
        initFormulario();

        popularFormulario();

    }

    private void restaurarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);

        isPessoaFisica = preferences.getBoolean("PF", false);
        clienteID = preferences.getInt("ultimoIDVIP", -1);
    }

    private void initFormulario() {

        //IMG user
        imageView=findViewById(R.id.imagemUser);
        //////////////////////////////
        //////////////////////card cliente update /////////////////////////
        btSalvarClienteVIP = findViewById(R.id.btCancelarClienteVip);
        btEditar = findViewById(R.id.btEditar);

        btSalvarCredenciais = findViewById(R.id.btSalvarCredenciais);

        btEditarCredenciais = findViewById(R.id.btEditarCredenciais);
        //////////////////////////////////
        editPrimeiroNomeMydb = findViewById(R.id.editPrimeiroNomeMydb);
        editSobreNomeMydb = findViewById(R.id.editSobreNomeMydb);








        /////////CREDENCIAIS//////
        editEmailMydb = findViewById(R.id.editEmailMydb);
        editSenhaMydb = findViewById(R.id.editSenhaMydb);
        editTextConfirmarSenha = findViewById(R.id.editConfirmarSenha);
        checkBoxTermoMydb = findViewById(R.id.checkBoxTermoMydb);
        checkBoxTermo = findViewById(R.id.checkBoxTermoMydb);
        ////////////////
        btVoltar = findViewById(R.id.btVoltarMeusDados);

        controller = new ClienteController(this);

        cliente = new Cliente();

        cliente.setId(clienteID);
        // clientePF =new ClientePF();

    }


    private void popularFormulario() {
        if (clienteID >= 1) {
            cliente = controller.getClienteByID(cliente);



            // Dados Obj Cliente
            editPrimeiroNomeMydb.setText(cliente.getPrimeiroNome());
            editSobreNomeMydb.setText(cliente.getSobreNome());
            editEmailMydb.setText(cliente.getEmail());
            editSenhaMydb.setText(cliente.getSenha());






            checkBoxTermoMydb.setChecked(true);
        } else {

            new FancyAlertDialog.Builder(AtualizarMeusDadosActivity.this)
                    .setTitle("ATENÇÃO!")
                    .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
                    .setMessage("Não foi possível, recuperar os dados do cliente ?")


                    //.setPositiveBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
                    .setPositiveBtnText("Concordo")
                    .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                    .setAnimation(Animation.POP)
                    .isCancellable(true)
                    .setIcon(R.drawable.ic_star_border_black_24dp, Icon.Visible)
                    .OnPositiveClicked(new FancyAlertDialogListener() {
                        @Override
                        public void OnClick() {
                            Intent intent = new Intent(AtualizarMeusDadosActivity.this, MenuUserActivity.class);
                            startActivity(intent);
                            finish();
                            return;
                        }
                    })

                    .build();
        }

    }

    public void voltar(View view) {
        Intent intent = new Intent(AtualizarMeusDadosActivity.this, MenuUserActivity.class);
        startActivity(intent);
        finish();
        return;
    }

    public void editarCardCliente(View view) {

        btEditar.setEnabled(false);
        btSalvarClienteVIP.setEnabled(true);
        editPrimeiroNomeMydb.setEnabled(true);
        editSobreNomeMydb.setEnabled(true);
        ckPessoaFisicaVIPMydb.setEnabled(false);

    }

    public void editarCardPF(View view) {
        btEditarPF.setEnabled(false);
        btSalvarPF.setEnabled(true);
        editNomeCompleto.setEnabled(true);
        editCPFPF.setEnabled(true);
        //ckPessoaFisicaVIPMydb.setEnabled(true);

    }

    public void editarCardPJ(View view) {

        btEditarPJ.setEnabled(false);
        btSalvarPJ.setEnabled(true);
        editCNPJMydb.setEnabled(true);
        editRazaoSocialMydb.setEnabled(true);
        editDataAberturaMydb.setEnabled(true);
        checSimplesNacionalMydb.setEnabled(true);
        checMEIMydb.setEnabled(true);
    }

    public void editarCardCredenciais(View view) {
        btEditarCredenciais.setEnabled(false);
        btSalvarCredenciais.setEnabled(true);
        editEmailMydb.setEnabled(true);
        editSenhaMydb.setEnabled(true);
        checkBoxTermoMydb.setEnabled(true);
    }

    public void salvarCardCliente(View view) {
        if (validarFormularioCardClienteVIP()) {


        }

    }

    public void salvarCardPJ(View view) {
        if (validarFormularioCardPJ()) {
        }
    }

    public void salvarCardCredenciais(View view) {
        if (validarFormularioCardCredenciais()) {

            if (!validarSenha()) {


                Toast.makeText(getApplicationContext(), "As senhas não conferem", Toast.LENGTH_LONG).show();

                editSenhaMydb.setError("*");
                editSenhaMydb.requestFocus();
                editTextConfirmarSenha.setError("*");


            } else {

                cliente.setEmail(editEmailMydb.getText().toString());
                cliente.setSenha(editSenhaMydb.getText().toString());
                controller.alterar(cliente);
                //salvarSharedPreferences();

                Intent intent = new Intent(AtualizarMeusDadosActivity.this, Main02Activity.class);
                startActivity(intent);
                finish();
                return;
            }

        }
    }

    public void salvarCardPF(View view) {
        if (validarFormularioCardPF()) {


            Intent intent = new Intent(AtualizarMeusDadosActivity.this, Main02Activity.class);
            startActivity(intent);
            finish();
            return;

        }
    }

    public boolean validarFormularioCardClienteVIP() {
        boolean retorno = true;

        if (TextUtils.isEmpty(editPrimeiroNomeMydb.getText().toString())) {
            editPrimeiroNomeMydb.setError("*");
            editPrimeiroNomeMydb.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(editSobreNomeMydb.getText().toString())) {
            editSobreNomeMydb.setError("*");
            editSobreNomeMydb.requestFocus();
            retorno = false;
        }
        return retorno;
    }

    public boolean validarCPF() {
        boolean retorno = true;
        if (!AppUtil.isCPF(editCPFPF.getText().toString())) {
            editCPFPF.setError("*");
            editCPFPF.requestFocus();
            retorno = false;
            Toast.makeText(this, "CPF Inválido, tente novemente ... ", Toast.LENGTH_LONG).show();

        } else {

            editCPFPF.setText(AppUtil.mascaraCPF(editCPFPF.getText().toString()));
        }
        return retorno;
    }

    public boolean validarFormularioCardPF() {
        boolean retorno = true;

        if (TextUtils.isEmpty(editNomeCompleto.getText().toString())) {
            editNomeCompleto.setError("*");
            editNomeCompleto.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(editCPFPF.getText().toString())) {
            editCPFPF.setError("*");
            editCPFPF.requestFocus();
            retorno = false;
        }
        retorno = validarCPF();
        return retorno;
    }

    public boolean validarFormularioCardPJ() {
        boolean retorno = true;


        if (TextUtils.isEmpty(checSimplesNacionalMydb.getText().toString())) {
            checSimplesNacionalMydb.setError("*");
            checSimplesNacionalMydb.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(checMEIMydb.getText().toString())) {
            checMEIMydb.setError("*");
            checMEIMydb.requestFocus();
            retorno = false;
        }

        if (TextUtils.isEmpty(editCNPJMydb.getText().toString())) {
            editCNPJMydb.setError("*");
            editCNPJMydb.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(editRazaoSocialMydb.getText().toString())) {
            editRazaoSocialMydb.setError("*");
            editRazaoSocialMydb.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(editDataAberturaMydb.getText().toString())) {
            editDataAberturaMydb.setError("*");
            editDataAberturaMydb.requestFocus();
            retorno = false;
        }
        if (!AppUtil.isCNPJ(editCNPJMydb.getText().toString())) {
            editCNPJMydb.setError("*");
            editCNPJMydb.requestFocus();
            retorno = false;
            Toast.makeText(this, "CNPJ Inválido, tente novemente ... ", Toast.LENGTH_LONG).show();
            //12.245.529/0001-54
        } else {
            //Toast.makeText(this,"OK ... ",Toast.LENGTH_LONG).show();
            editCNPJMydb.setText(AppUtil.mascaraCNPJ(editCNPJMydb.getText().toString()));
        }
        return retorno;
    }

    public boolean validarFormularioCardCredenciais() {
        boolean retorno = true;

        if (TextUtils.isEmpty(editEmailMydb.getText().toString())) {
            editEmailMydb.setError("*");
            editEmailMydb.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(editSenhaMydb.getText().toString())) {
            editSenhaMydb.setError("*");
            editSenhaMydb.requestFocus();
            retorno = false;
        }
        return retorno;
    }

    public void validarTermo(View view) {
        if (!checkBoxTermo.isChecked()) {
            Toast.makeText(getApplicationContext(), "É necessario aceitar os termos de uso para continuar o cadastro...", Toast.LENGTH_LONG).show();
            new FancyAlertDialog.Builder(AtualizarMeusDadosActivity.this)
                    .setTitle("Política de Privacidade & Termos de Uso")
                    .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
                    .setMessage("Concordo com as normas de privacidade do aplicativo?")
                    .setNegativeBtnText("Discordo")
                    .setPositiveBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
                    .setPositiveBtnText("Concordo")
                    .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                    .setAnimation(Animation.POP)
                    .isCancellable(true)
                    .setIcon(R.drawable.ic_star_border_black_24dp, Icon.Visible)
                    .OnPositiveClicked(new FancyAlertDialogListener() {
                        @Override
                        public void OnClick() {
                            Toast.makeText(getApplicationContext(), "Obrigado, seja bem vindo", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .OnNegativeClicked(new FancyAlertDialogListener() {
                        @Override
                        public void OnClick() {
                            Toast.makeText(getApplicationContext(), "Lamentamos, mas é preciso aceitar os termos de uso", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    })
                    .build();

        }
    }

    public boolean validarSenha() {
        boolean retorno = false;
        String senhaA = editSenhaMydb.getText().toString(), senhaB = editTextConfirmarSenha.getText().toString();
        if (senhaA.equals(senhaB)) {
            retorno = true;
        }
        return retorno;
    }
}