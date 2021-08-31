package app.atividade1.pvbf.MyBikePark.controller;


import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import app.atividade1.pvbf.MyBikePark.R;
import app.atividade1.pvbf.MyBikePark.model.Parque;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

public class ParqueController implements ICrud<Parque> {
      ImageView imageView;

    @Override
    public void insert(Parque obj) {

        Realm realm = Realm.getDefaultInstance();


        Number primaryKey = realm.where(Parque.class).max("id");
        final int autoincrementPrimaryKey = (primaryKey == null) ? 1 : primaryKey.intValue() + 1;

        obj.setId(autoincrementPrimaryKey);

        realm.beginTransaction();
        realm.copyToRealm(obj);
        realm.commitTransaction();
        realm.close();


    }

    @Override
    public void update(Parque obj) {
        Realm realm = Realm.getDefaultInstance();
        Parque parque = realm.where(Parque.class).equalTo("id", obj.getId())
                .findFirst();

        if (parque != null) {
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
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Parque> results = realm.where(Parque.class).equalTo("id", obj.getId()).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void deleteByID(Parque obj) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Parque> results = realm.where(Parque.class).equalTo("id", obj.getId()).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public Parque getById(Parque obj) {
        Realm realm = Realm.getDefaultInstance();

        try {
            obj = realm.copyFromRealm(Objects.requireNonNull(realm.where(Parque.class)).equalTo("id", obj.getId()).findFirst());

        } catch (Exception e) {
            Log.e("db_log", "Erro ao execultar getByID: " + e.getMessage());

        }
        return obj;
    }


    @Override
    public List<Parque> listar() {
        Realm realm = null;
        RealmResults<Parque> results = null;
        List<Parque> list = new ArrayList<>();
        try {
            realm = Realm.getDefaultInstance();
            results = realm.where(Parque.class).findAll();
            list = realm.copyFromRealm(results);

        } catch (RealmException e) {

        } finally {
            realm.close();
        }

        return list;
    }

    @Override
    public Parque buscaEmailSenha(String x, String y) {
        return null;
    }

    public void addBikeParqueAuto() {
        Parque parque1 = new Parque();
        Parque parque2 = new Parque();
        Parque parque3 = new Parque();
        Parque parque4 = new Parque();
        Parque parque5 = new Parque();
        Parque parque6 = new Parque();
        Parque parque7 = new Parque();
        Parque parque8 = new Parque();





        List<Parque> parques;
        parques=listar();

if (parques.size()<=0){


           // imageView.setImageResource(R.drawable.img1);

            //parque1.setFoto(AppUtil.convertImageViewToByteArray(imageView));
            parque1.setNome("Parque Bike Granja Marileusa");
            parque1.setLatitude(-18.618734007459388);
            parque1.setLongitude(-48.206952525631024);
            insert(parque1);
            //imageView.setImageResource(R.drawable.img2);
            //parque2.setFoto(AppUtil.convertImageViewToByteArray(imageView));
            parque2.setNome("Bike Park");
            parque2.setLatitude(-20.80087169355104);
            parque2.setLongitude(-47.81144475546648);
            insert(parque2);
            //imageView.setImageResource(R.drawable.img3);
            //parque3.setFoto(AppUtil.convertImageViewToByteArray(imageView));
            parque3.setNome("Alfenas Bike Park");
            parque3.setLatitude(-21.262320236909865);
            parque3.setLongitude(-45.92179652023586);
            insert(parque3);
           // imageView.setImageResource(R.drawable.img4);
            //parque4.setFoto(AppUtil.convertImageViewToByteArray(imageView));
            parque4.setNome("Circuito Bike");
            parque4.setLatitude(-22.16051930593243);
            parque4.setLongitude(-46.93253859954525);
            insert(parque4);
            //imageView.setImageResource(R.drawable.img5);
            //parque5.setFoto(AppUtil.convertImageViewToByteArray(imageView));
            parque5.setNome("Gorilas Bike Park");
            parque5.setLatitude(-22.60749329051669);
            parque5.setLongitude(-47.41593698530193);
            insert(parque5);
            //imageView.setImageResource(R.drawable.img6);
            //parque6.setFoto(AppUtil.convertImageViewToByteArray(imageView));
            parque6.setNome("Nore Bike Park");
            parque6.setLatitude(-23.224762948769975);
            parque6.setLongitude(-47.30607371581178);
            insert(parque6);
            //imageView.setImageResource(R.drawable.img7);
            //parque7.setFoto(AppUtil.convertImageViewToByteArray(imageView));
            parque7.setNome("Athor Sucupira Bike Parque");
            parque7.setLatitude(-19.96128979163641);
            parque7.setLongitude(-45.6289917951094);
            insert(parque7);
            //imageView.setImageResource(R.drawable.img8);
            //parque8.setFoto(AppUtil.convertImageViewToByteArray(imageView));
            parque8.setNome("Zoom Bike Park");
            parque8.setLatitude(-22.485081851884093);
            parque8.setLongitude(-45.501146869730796);
            insert(parque8);

    }}

}
