package app.atividade1.pvbf.MyBikePark.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Evento extends RealmObject implements Serializable {

    @PrimaryKey
    private int id;
    @Required
    private String nome;
    private String data;
    private String descricao;
    private int pessoas;

    public Evento() {
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Evento(String nome, String data, String descricao, int pessoas) {
        this.nome = nome;
        this.data = data;
        this.data = descricao;
        this.pessoas = pessoas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getPessoas() {
        return pessoas;
    }

    public void setPessoas(int pessoas) {
        this.pessoas = pessoas;
    }
}
