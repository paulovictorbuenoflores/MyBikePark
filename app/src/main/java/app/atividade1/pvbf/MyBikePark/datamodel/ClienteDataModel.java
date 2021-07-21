package app.atividade1.pvbf.MyBikePark.datamodel;


//MOR modelo objeto relacional - SQLSERVER ORACLE POSTEGRE
public class ClienteDataModel {

    /**
     * private int id;
     * private String primeiroNome, sobreNome, email, senha;
     * private  boolean pessoaFisica;
     */

    public static final String TABELA = "cliente";
    public static final String ID = "id";
    public static final String PRIMEIRONOME = "primeiroNome";
    public static final String SOBRE_NOME = "sobreNome";
    public static final String EMAIL = "email";
    public static final String SENHA = "senha";
    public static final String IMAGEM = "imagem";
    private static final String DATA_INC = "dataInc";
    private static final String DATA_ALT = "dataAlt";

    private static String query;

    /**
     * CREATE TABLE cliente (
     * id           INTEGER PRIMARY KEY AUTOINCREMENT,
     * primeiroNome TEXT,
     * sobreNome    TEXT,
     * email        TEXT,
     * senha        TEXT,
     * pessoaFisica INTEGER,
     * dataInc      TEXT,
     * dataAlt      TEXT
     * );
     *
     * @return
     */
    //datetime default current_timestamp
    public static String criarTabela() {
        query = "CREATE TABLE " + TABELA + " ( ";
        query += ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query += PRIMEIRONOME + " TEXT, ";
        query += SOBRE_NOME + " TEXT, ";
        query += EMAIL + " TEXT, ";
        query += SENHA + " TEXT, ";
        query += IMAGEM + " INTEGER, ";
        query += DATA_INC + " datetime default current_timestamp, ";
        query += DATA_ALT + " datetime default current_timestamp ";
        query += ")";
        return query;
    }
}
