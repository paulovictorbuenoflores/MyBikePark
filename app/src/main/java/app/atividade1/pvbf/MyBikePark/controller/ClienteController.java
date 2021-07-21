package app.atividade1.pvbf.MyBikePark.controller;


import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import app.atividade1.pvbf.MyBikePark.datasource.AppDataBase;
import app.atividade1.pvbf.MyBikePark.datamodel.ClienteDataModel;
import app.atividade1.pvbf.MyBikePark.model.Cliente;

/**
 * na controller vamos adicionar as regras de negocio
 * <p>
 * CRUD
 */
public class ClienteController extends AppDataBase {

    private static final String TABELA = ClienteDataModel.TABELA;
    private ContentValues dados;

    public ClienteController(@Nullable Context context) {
        super(context);
    }

    public static boolean validarDadosDoCliente(Cliente cliente, String email, String senha) {
        return cliente.getEmail().equals(email) && cliente.getSenha().equals(senha);
    }

    public boolean incluir(Cliente obj) {
        dados = new ContentValues();
        dados.put(ClienteDataModel.PRIMEIRONOME, obj.getPrimeiroNome());
        dados.put(ClienteDataModel.SOBRE_NOME, obj.getSobreNome());
        dados.put(ClienteDataModel.EMAIL, obj.getEmail());
        dados.put(ClienteDataModel.SENHA, obj.getSenha());



        return insert(TABELA, dados);
    }

    public boolean alterar(Cliente obj) {
        dados = new ContentValues();
        dados.put(ClienteDataModel.ID, obj.getId());
        dados.put(ClienteDataModel.PRIMEIRONOME, obj.getPrimeiroNome());
        dados.put(ClienteDataModel.SOBRE_NOME, obj.getSobreNome());
        dados.put(ClienteDataModel.EMAIL, obj.getEmail());
        dados.put(ClienteDataModel.SENHA, obj.getSenha());



        return update(TABELA, dados);
    }

    public boolean deletar(Cliente obj) {
        return delete(TABELA, obj.getId());
    }

    public List<Cliente> listar() {


        return list(TABELA);
    }

    public int getUltimoID() {

        return getLastPK(TABELA);
    }

    public Cliente getClienteByID(Cliente obj) {

        return getClienteByID(ClienteDataModel.TABELA, obj);
    }
    public List<String> gerarListaDeClientesListView(){

        List<String> clientes = new ArrayList<>();

        for (Cliente obj: listar()) {

            clientes.add(obj.getId()+", "+obj.getPrimeiroNome());

        }

        return clientes;
    }

}
