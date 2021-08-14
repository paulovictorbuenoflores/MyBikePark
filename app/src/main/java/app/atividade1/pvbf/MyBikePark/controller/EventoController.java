package app.atividade1.pvbf.MyBikePark.controller;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import app.atividade1.pvbf.MyBikePark.model.Evento;
import app.atividade1.pvbf.MyBikePark.model.Parque;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

public class EventoController implements ICrud<Evento> {
    @Override
    public void insert(Evento evento) {
        Realm realm = Realm.getDefaultInstance();
        Number primaryKey = realm.where(Evento.class).max("id");
        final int autoIncrementPrimaryKey = (primaryKey == null) ? 1 : primaryKey.intValue() + 1;
        evento.setId(autoIncrementPrimaryKey);
        realm.beginTransaction();
        realm.copyToRealm(evento);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void update(Evento evento) {
        Realm realm = Realm.getDefaultInstance();
        Evento eventoTemp = realm.where(Evento.class).equalTo("id", evento.getId())
                .findFirst();
        if (evento != null) {
            realm.beginTransaction();
            eventoTemp.setNome(evento.getNome());
            eventoTemp.setDescricao(evento.getDescricao());
            eventoTemp.setData(evento.getData());
            eventoTemp.setPessoas(evento.getPessoas());
            realm.commitTransaction();
            realm.close();
        }
    }

    @Override
    public void deletar(Evento obj) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Evento> results = realm.where(Evento.class).equalTo("id", obj.getId()).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void deleteByID(Evento obj) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Evento> results = realm.where(Evento.class).equalTo("id", obj.getId()).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public Evento getById(Evento obj) {
        Realm realm = Realm.getDefaultInstance();

        try {
            obj = realm.copyFromRealm(Objects.requireNonNull(realm.where(Evento.class)).equalTo("id", obj.getId()).findFirst());

        } catch (Exception e) {
            Log.e("db_log", "Erro ao execultar getByID: " + e.getMessage());

        }
        return obj;
    }

    @Override
    public List<Evento> listar() {
        Realm realm = null;
        RealmResults<Evento> results = null;
        List<Evento> list = new ArrayList<>();
        try {
            realm = Realm.getDefaultInstance();
            results = realm.where(Evento.class).findAll();
            list = realm.copyFromRealm(results);

        } catch (RealmException e) {

        } finally {
            realm.close();
        }

        return list;
    }

    @Override
    public Evento buscaEmailSenha(String x, String y) {
        return null;
    }
}
