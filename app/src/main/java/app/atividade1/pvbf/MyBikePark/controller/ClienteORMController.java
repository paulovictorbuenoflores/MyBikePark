package app.atividade1.pvbf.MyBikePark.controller;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import app.atividade1.pvbf.MyBikePark.model.Cliente;
import app.atividade1.pvbf.MyBikePark.model.ClienteORM;
import app.atividade1.pvbf.MyBikePark.model.ICrud;
import app.atividade1.pvbf.MyBikePark.model.ParquesORM;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

public class ClienteORMController implements ICrud<ClienteORM> {

    @Override
    public void insert(ClienteORM obj) {
        Realm realm = Realm.getDefaultInstance();


        Number primaryKey =realm.where(ClienteORM.class).max( "id");
        final int autoincrementPrimaryKey=(primaryKey==null)? 1: primaryKey.intValue()+1;

        obj.setId(autoincrementPrimaryKey);

        realm.beginTransaction();
        realm.copyToRealm(obj);
        realm.commitTransaction();
        realm.close();


    }

    @Override
    public void update(ClienteORM obj) {
        Realm realm=Realm.getDefaultInstance();
        ClienteORM clienteORM= realm.where(ClienteORM.class).equalTo("id", obj.getId())
                .findFirst();

        if(clienteORM!=null){
            realm.beginTransaction();
            clienteORM.setPrimeiroNome(obj.getPrimeiroNome());
            clienteORM.setSobreNome(obj.getSobreNome());
            clienteORM.setEmail(obj.getEmail());
            clienteORM.setSenha(obj.getSenha());
            realm.commitTransaction();
            realm.close();
        }
    }

    @Override
    public void deletar(ClienteORM obj) {
        Realm realm= Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<ClienteORM> results=realm.where(ClienteORM.class).equalTo("id", obj.getId()).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void deleteByID(ClienteORM obj) {
        Realm realm= Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<ClienteORM> results=realm.where(ClienteORM.class).equalTo("id", obj.getId()).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public ClienteORM getById(ClienteORM obj) {

        Realm realm = Realm.getDefaultInstance();

        try{
            obj= realm.copyFromRealm(Objects.requireNonNull(realm.where(ClienteORM.class)).equalTo("id", obj.getId()).findFirst());

        }catch (Exception e){
            Log.e("db_log", "Erro ao execultar getByID: "+e.getMessage());

        }
        return obj;


    }


    @Override
    public List<ClienteORM> listar() {

        Realm realm=null;
        RealmResults<ClienteORM> results=null;
        List<ClienteORM> list=new ArrayList<>();
        try{
            realm=Realm.getDefaultInstance();
            results=realm.where(ClienteORM.class).findAll();
            list=realm.copyFromRealm(results);

        }catch (RealmException e){

        }finally {
            realm.close();
        }

        return list;
    }

    @Override
    public ClienteORM buscaEmailSenha(String x, String y) {
        Realm realm = Realm.getDefaultInstance();
        ClienteORM clienteORM=new ClienteORM();
        ArrayList<ClienteORM> clienteLista=new ArrayList<>();
        try{

           /* clienteLista.addAll(listar());
            for (int i=0; i<clienteLista.size(); i++){
                if(clienteLista.get(i).getSenha().equals(y)&&clienteLista.get(i).getEmail().equals(x)){
                    clienteORM=clienteLista.get(i);

                }

            }*/


            clienteORM= realm.copyFromRealm((Objects.requireNonNull(realm.where(ClienteORM.class)).equalTo("email", x).findFirst()));


        }catch (Exception e){
            Log.e("db_log", "Erro ao execultar getByID: "+e.getMessage());

        }
        return clienteORM;



    }



/*
    public void insert(ClienteORM obj){


        Realm realm = Realm.getDefaultInstance();


        Number primaryKey =realm.where(ClienteORM.class).max( "id");
        final int autoincrementPrimaryKey=(primaryKey==null)? 1: primaryKey.intValue()+1;

        obj.setId(autoincrementPrimaryKey);

        realm.beginTransaction();
        realm.copyToRealm(obj);
        realm.commitTransaction();
        realm.close();


        //  int para =0;
    }
    public void update(ClienteORM obj){
        Realm realm=Realm.getDefaultInstance();
        ClienteORM clienteORM= realm.where(ClienteORM.class).equalTo("id", obj.getId())
                .findFirst();

        if(clienteORM!=null){
            realm.beginTransaction();
            clienteORM.setPrimeiroNome(obj.getPrimeiroNome());
            clienteORM.setSobreNome(obj.getSobreNome());
            clienteORM.setEmail(obj.getEmail());
            clienteORM.setSenha(obj.getSenha());
            realm.commitTransaction();
            realm.close();
        }}
    public void  deletar(ClienteORM obj){
        Realm realm= Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<ClienteORM> results=realm.where(ClienteORM.class).equalTo("id", obj.getId()).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }
    public void  deleteByID(int id){
        Realm realm= Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<ClienteORM> results=realm.where(ClienteORM.class).equalTo("id", id).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }
    public List<ClienteORM> listar(){

        Realm realm=null;
        RealmResults<ClienteORM> results=null;
        List<ClienteORM> list=new ArrayList<>();
        try{
            realm=Realm.getDefaultInstance();
            results=realm.where(ClienteORM.class).findAll();
            list=realm.copyFromRealm(results);

        }catch (RealmException e){

        }finally {
            realm.close();
        }

        return list;
    }
    public ClienteORM getById(int id){
        Realm realm = Realm.getDefaultInstance();
        ClienteORM obj = null;
        try{
            obj= realm.copyFromRealm(Objects.requireNonNull(realm.where(ClienteORM.class)).equalTo("id", id).findFirst());

        }catch (Exception e){
            Log.e("db_log", "Erro ao execultar getByID: "+e.getMessage());

        }
        return obj;


    }
*/

    public static boolean validarDadosDoCliente(String email, String senha){
       return  true;
    }


}
