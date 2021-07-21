package app.atividade1.pvbf.MyBikePark.controller;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import app.atividade1.pvbf.MyBikePark.datamodel.ClienteDataModel;
import app.atividade1.pvbf.MyBikePark.datasource.AppDataBase;
import app.atividade1.pvbf.MyBikePark.model.ClienteORM;
import app.atividade1.pvbf.MyBikePark.model.ICrud;
import app.atividade1.pvbf.MyBikePark.model.ParquesORM;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

public class ParqueController extends AppDataBase implements ICrud<ParquesORM> {

    private static final String TABELA = ClienteDataModel.TABELA;
    private ContentValues dados;

    public ParqueController(@Nullable Context context) {
        super(context);
    }

    @Override
    public void insert(ParquesORM obj) {

        Realm realm = Realm.getDefaultInstance();


        Number primaryKey =realm.where(ParquesORM.class).max( "id");
        final int autoincrementPrimaryKey=(primaryKey==null)? 1: primaryKey.intValue()+1;

        obj.setId(autoincrementPrimaryKey);

        realm.beginTransaction();
        realm.copyToRealm(obj);
        realm.commitTransaction();
        realm.close();


    }

    @Override
    public void update(ParquesORM obj) {
        Realm realm=Realm.getDefaultInstance();
        ParquesORM parquesORM= realm.where(ParquesORM.class).equalTo("id", obj.getId())
                .findFirst();

        if(parquesORM!=null) {
            realm.beginTransaction();
            parquesORM.setNome(obj.getNome());
            parquesORM.setFoto(obj.getFoto());
            parquesORM.setLatitude(obj.getLatitude());
            parquesORM.setLongitude(obj.getLongitude());
            realm.commitTransaction();
            realm.close();
        }
    }

    @Override
    public void deletar(ParquesORM obj) {
        Realm realm= Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<ParquesORM> results=realm.where(ParquesORM.class).equalTo("id", obj.getId()).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void deleteByID(ParquesORM obj) {
        Realm realm= Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<ParquesORM> results=realm.where(ParquesORM.class).equalTo("id", obj.getId()).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public ParquesORM getById(ParquesORM obj) {
        Realm realm = Realm.getDefaultInstance();

        try{
            obj= realm.copyFromRealm(Objects.requireNonNull(realm.where(ParquesORM.class)).equalTo("id", obj.getId()).findFirst());

        }catch (Exception e){
            Log.e("db_log", "Erro ao execultar getByID: "+e.getMessage());

        }
        return obj;
    }


    @Override
    public List<ParquesORM> listar() {
        Realm realm=null;
        RealmResults<ParquesORM> results=null;
        List<ParquesORM> list=new ArrayList<>();
        try{
            realm=Realm.getDefaultInstance();
            results=realm.where(ParquesORM.class).findAll();
            list=realm.copyFromRealm(results);

        }catch (RealmException e){

        }finally {
            realm.close();
        }

        return list;
    }

    @Override
    public ParquesORM buscaEmailSenha(String x, String y) {
        return null;
    }
/*
    public void insert(ParquesORM obj){


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
    public void update(ParquesORM obj){
        Realm realm=Realm.getDefaultInstance();
        ParquesORM parquesORM= realm.where(ParquesORM.class).equalTo("id", obj.getId())
                .findFirst();

        if(parquesORM!=null){
            realm.beginTransaction();
            parquesORM.setNome(obj.getNome());
            parquesORM.setFoto(obj.getFoto());
            parquesORM.setLatitude(obj.getLatitude());
            parquesORM.setLongitude(obj.getLongitude());
            realm.commitTransaction();
            realm.close();
        }}
    public void  deletar(ParquesORM obj){
        Realm realm= Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<ParquesORM> results=realm.where(ParquesORM.class).equalTo("id", obj.getId()).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }
    public void  deleteByID(int id){
        Realm realm= Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<ParquesORM> results=realm.where(ParquesORM.class).equalTo("id", id).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }
    public List<ParquesORM> listar(){

        Realm realm=null;
        RealmResults<ParquesORM> results=null;
        List<ParquesORM> list=new ArrayList<>();
        try{
            realm=Realm.getDefaultInstance();
            results=realm.where(ParquesORM.class).findAll();
            list=realm.copyFromRealm(results);

        }catch (RealmException e){

        }finally {
            realm.close();
        }

        return list;
    }
    public ParquesORM getById(int id){
        Realm realm = Realm.getDefaultInstance();
        ParquesORM obj = null;
        try{
            obj= realm.copyFromRealm(Objects.requireNonNull(realm.where(ParquesORM.class)).equalTo("id", id).findFirst());

        }catch (Exception e){
            Log.e("db_log", "Erro ao execultar getByID: "+e.getMessage());

        }
        return obj;


    }*/
}
