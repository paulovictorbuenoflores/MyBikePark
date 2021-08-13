package app.atividade1.pvbf.MyBikePark.NAOSEIPRAQUESERVE;

import java.util.List;

public interface ICrud<T> {
    public void insert(T obj);
    public void update(T obj);
    public void  deletar(T obj);
    public void  deleteByID(T obj);
    public  T  getById(T obj);
    public List<T> listar();
    public T buscaEmailSenha(String x,String y);
}
