package app.atividade1.pvbf.MyBikePark.controller;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import app.atividade1.pvbf.MyBikePark.model.Usuario;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

public class UsuarioController implements ICrud<Usuario> {

    @Override
    public void insert(Usuario obj) {
        Realm realm = Realm.getDefaultInstance();
        Number primaryKey =realm.where(Usuario.class).max( "id");
        final int autoincrementPrimaryKey=(primaryKey==null)? 1: primaryKey.intValue()+1;
        obj.setId(autoincrementPrimaryKey);
        realm.beginTransaction();
        realm.copyToRealm(obj);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void update(Usuario obj) {
        Realm realm=Realm.getDefaultInstance();
        Usuario usuario = realm.where(Usuario.class).equalTo("id", obj.getId())
                .findFirst();
        if(usuario !=null){
            realm.beginTransaction();
            usuario.setPrimeiroNome(obj.getPrimeiroNome());
            usuario.setSobreNome(obj.getSobreNome());
            usuario.setEmail(obj.getEmail());
            usuario.setSenha(obj.getSenha());
            usuario.setImagem(obj.getImagem());
            realm.commitTransaction();
            realm.close();
        }
    }

    @Override
    public void deletar(Usuario obj) {
        Realm realm= Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Usuario> results=realm.where(Usuario.class).equalTo("id", obj.getId()).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void deleteByID(Usuario obj) {
        Realm realm= Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Usuario> results=realm.where(Usuario.class).equalTo("id", obj.getId()).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public Usuario getById(Usuario obj) {

        Realm realm = Realm.getDefaultInstance();

        try{
            obj= realm.copyFromRealm(Objects.requireNonNull(realm.where(Usuario.class)).equalTo("id", obj.getId()).findFirst());

        }catch (Exception e){
            Log.e("db_log", "Erro ao execultar getByID: "+e.getMessage());

        }
        return obj;


    }

    @Override
    public List<Usuario> listar() {

        Realm realm=null;
        RealmResults<Usuario> results=null;
        List<Usuario> list=new ArrayList<>();
        try{
            realm=Realm.getDefaultInstance();
            results=realm.where(Usuario.class).findAll();
            list=realm.copyFromRealm(results);

        }catch (RealmException e){

        }finally {
            realm.close();
        }

        return list;
    }

    public String[] listarPorNome(){
        List<Usuario> usuarios = listar();
        String[] usuariosString = new String[usuarios.size()];
        int i = 0;
        for (Usuario a : usuarios) {
            usuariosString[i] = a.getPrimeiroNome();
            i++;
        }
        return usuariosString;
    }

    @Override
    public Usuario buscaEmailSenha(String email, String senha) {
        Realm realm = Realm.getDefaultInstance();
        Usuario usuario =new Usuario();
        ArrayList<Usuario> clienteLista=new ArrayList<>();
        try{

          usuario = realm.copyFromRealm((Objects.requireNonNull(realm.where(Usuario.class)).equalTo("email", email).findFirst()));


        }catch (Exception e){
            Log.e("db_log", "Erro ao execultar getByID: "+e.getMessage());

        }
        return usuario;



    }

    public  boolean validarDadosDoUsuario(Usuario usuario){
    List<Usuario> usuarios;
    usuarios=listar();
    boolean retorno=false;
    for (int i=0; i<usuarios.size(); i++){

        if((usuario.getEmail().equals(usuarios.get(i).getEmail()))&&(usuario.getSenha().equals(usuarios.get(i).getSenha()))){
            retorno= true;
        }

    }
       return retorno;
    }


}
