package app.atividade1.pvbf.MyBikePark.controller;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import app.atividade1.pvbf.MyBikePark.model.Parque;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

public class ParqueController  implements ICrud<Parque> {




    @Override
    public void insert(Parque obj) {

        Realm realm = Realm.getDefaultInstance();


        Number primaryKey =realm.where(Parque.class).max( "id");
        final int autoincrementPrimaryKey=(primaryKey==null)? 1: primaryKey.intValue()+1;

        obj.setId(autoincrementPrimaryKey);

        realm.beginTransaction();
        realm.copyToRealm(obj);
        realm.commitTransaction();
        realm.close();


    }

    @Override
    public void update(Parque obj) {
        Realm realm=Realm.getDefaultInstance();
        Parque parque = realm.where(Parque.class).equalTo("id", obj.getId())
                .findFirst();

        if(parque !=null) {
            realm.beginTransaction();
            parque.setNome(obj.getNome());
            parque.setFoto(obj.getFoto());
            parque.setLatitude(obj.getLatitude());
            parque.setLongitude(obj.getLongitude());
            realm.commitTransaction();
            realm.close();
        }
    }

    @Override
    public void deletar(Parque obj) {
        Realm realm= Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Parque> results=realm.where(Parque.class).equalTo("id", obj.getId()).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void deleteByID(Parque obj) {
        Realm realm= Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Parque> results=realm.where(Parque.class).equalTo("id", obj.getId()).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public Parque getById(Parque obj) {
        Realm realm = Realm.getDefaultInstance();

        try{
            obj= realm.copyFromRealm(Objects.requireNonNull(realm.where(Parque.class)).equalTo("id", obj.getId()).findFirst());

        }catch (Exception e){
            Log.e("db_log", "Erro ao execultar getByID: "+e.getMessage());

        }
        return obj;
    }


    @Override
    public List<Parque> listar() {
        Realm realm=null;
        RealmResults<Parque> results=null;
        List<Parque> list=new ArrayList<>();
        try{
            realm=Realm.getDefaultInstance();
            results=realm.where(Parque.class).findAll();
            list=realm.copyFromRealm(results);

        }catch (RealmException e){

        }finally {
            realm.close();
        }

        return list;
    }

    @Override
    public Parque buscaEmailSenha(String x, String y) {
        return null;
    }



}
