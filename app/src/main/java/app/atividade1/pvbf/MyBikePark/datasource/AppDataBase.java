package app.atividade1.pvbf.MyBikePark.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import app.atividade1.pvbf.MyBikePark.api.AppUtil;
import app.atividade1.pvbf.MyBikePark.datamodel.ClienteDataModel;
import app.atividade1.pvbf.MyBikePark.model.Cliente;

public class AppDataBase extends SQLiteOpenHelper {

    private static final String BD_NAME = "cliente_DB.sqlit";
    private static final int DB_VERSION = 1;
    Cursor cursor;
    SQLiteDatabase db;
    Context context;

    public AppDataBase(@Nullable Context context) {
        super(context, BD_NAME, null, DB_VERSION);
        this.context = context;
        db = getWritableDatabase();//ler é escrever no banco de dados

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Criar as tabelas


        try {

            db.execSQL(ClienteDataModel.criarTabela());
            // Log.i(AppUtil.LOG_APP, "TB Cliente: "+ClienteDataModel.gerarTabela());
            Log.d(AppUtil.TAG, "onCreate: Tabela Cliente criada... "+ClienteDataModel.criarTabela());

        } catch (SQLException e) {

            Log.e(AppUtil.LOG_APP, "TB Cliente: " + e.getMessage());

        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //atulizar o bd e tabelas

    }

    /**
     * Incluir dados no banco de dados
     *
     * @return
     */
    public boolean insert(String tabela, ContentValues dados) {
        boolean sucesso = true;
        try {
            Log.i(AppUtil.LOG_APP, tabela + "insert() executado com sucesso.");
            sucesso = db.insert(tabela, null, dados) > 0;
        } catch (SQLException e) {
            Log.e(AppUtil.LOG_APP, tabela + " falha ao executar o insert" + e.getMessage());
        }
        return sucesso;
    }

    /**
     * Apagar dados no banco de dados
     *
     * @return
     */
    public boolean delete(String tabela, int id) {
        boolean sucesso = true;
        try {
            Log.i(AppUtil.LOG_APP, tabela + "delete() executado com sucesso.");
            sucesso = db.delete(tabela, "id=?", new String[]{Integer.toString(id)}) > 0;
        } catch (SQLException e) {
            Log.e(AppUtil.LOG_APP, tabela + " falha ao executar delete()" + e.getMessage());
        }
        return sucesso;
    }

    /**
     * Atualizar dados no banco de dados CLIENTE
     *
     * @return
     */
    public boolean update(String tabela, ContentValues dados) {
        boolean sucesso = true;
        try {
            int id = dados.getAsInteger("id");
            Log.i(AppUtil.LOG_APP, tabela + "update() executado com sucesso.");
            sucesso = db.update(tabela, dados, "id=?", new String[]{Integer.toString(id)}) > 0;
        } catch (SQLException e) {
            Log.e(AppUtil.LOG_APP, tabela + " falha ao executar update()" + e.getMessage());
        }
        return sucesso;
    }

    /**
     * Listar dados do banco de dados
     *
     * @return
     */
    public List<Cliente> list(String tabela) {
        List<Cliente> list = new ArrayList<>();
        Cliente cliente;

        String sql = "SELECT * FROM " + tabela;

        try {


            cursor = db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                do {

                    cliente = new Cliente();
                    cliente.setId(cursor.getInt(cursor.getColumnIndex(ClienteDataModel.ID)));
                    cliente.setPrimeiroNome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.PRIMEIRONOME)));
                    cliente.setSobreNome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.SOBRE_NOME)));
                    cliente.setEmail(cursor.getString(cursor.getColumnIndex(ClienteDataModel.EMAIL)));
                    cliente.setSenha(cursor.getString(cursor.getColumnIndex(ClienteDataModel.SENHA)));
                    cliente.setImagem(cursor.getInt(cursor.getColumnIndex(ClienteDataModel.IMAGEM)));
                    //cliente.setPessoaFisica(cursor.getInt(cursor.getColumnIndex(ClienteDataModel.PESSOAFISICA)) == 1);




                    list.add(cliente);

                } while (cursor.moveToNext());
                Log.i(AppUtil.LOG_APP, tabela + "Lista gerada com sucesso.");
            }
        } catch (SQLException e) {
            Log.e(AppUtil.LOG_APP, "Erro ao listar os dados" + tabela);
            Log.e(AppUtil.LOG_APP, "Erro ao listar os dados" + e.getMessage());

        }

        return list;
    }

    /**
     * Listar dados do banco de dados PESSOA FISICA
     *
     * @return
     */

    public int getLastPK(String tabela) {

        String sql = "SELECT seq FROM sqlite_sequence WHERE name = '" + tabela + "'";

        try {


            cursor = db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                do {

                    return cursor.getInt(cursor.getColumnIndex("seq"));
                } while (cursor.moveToNext());

            }
        } catch (SQLException e) {
            Log.e(AppUtil.LOG_APP, "Erro recuperando ultimo PK: " + tabela);
            Log.e(AppUtil.LOG_APP, "Erro ao listar os dados" + e.getMessage());

        }

        return -1;
    }

    /////////////////////////////////////////////

    public Cliente getClienteByID(String tabela, Cliente obj) {
        //int id =obj.getId();
        Cliente cliente = new Cliente();

        String sql = "SELECT * FROM " + tabela + " WHERE id =" + obj.getId();
        // getClienteByID(ClienteDataModel.TABELA, obj);
        try {

            cursor = db.rawQuery(sql, null);
            if (cursor.moveToNext()) {
                cliente.setId(cursor.getInt(cursor.getColumnIndex(ClienteDataModel.ID)));
                cliente.setPrimeiroNome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.PRIMEIRONOME)));
                cliente.setSobreNome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.SOBRE_NOME)));
                cliente.setEmail(cursor.getString(cursor.getColumnIndex(ClienteDataModel.EMAIL)));
                cliente.setSenha(cursor.getString(cursor.getColumnIndex(ClienteDataModel.SENHA)));
                cliente.setImagem(cursor.getInt(cursor.getColumnIndex(ClienteDataModel.IMAGEM)));

            }

        } catch (SQLException e) {
            //log erro
            Log.e(AppUtil.LOG_APP, "Erro getLastPK: " + e.getMessage());
        }
        return cliente;
    }




}
