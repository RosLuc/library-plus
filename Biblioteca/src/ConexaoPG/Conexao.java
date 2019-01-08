
package ConexaoPG;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 * Classe responsavel por realizar a conexão com o banco de dados PostgreSQL.
 * @author lucas
 */
public class Conexao {
    static private String url;
    static private String user;
    static private String pass;
    static private Connection con;
    
    /**
     * Metodo construtor.
     * @param strUrl URL do banco de dados PostgreSQL.
     * @param strUser Usuario do Banco de Dados.
     * @param strPass Senha do Banco de Dados.
     */
    Conexao(String strUrl, String strUser, String strPass) {     
        url = strUrl;
        user = strUser;
        pass = strPass;
    }
    
    /**
     * Metodo Responsavel por inicializar a conexão com o PostgreSQL.
     * @return Retorna true caso a conexão seja estabelecida e false caso tenha erro.
     */
    private static boolean iniciarConexao(){
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, user, pass);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 
     * @param sql Comando SQL a ser executado no Banco de Dados.
     * @return Para valores maiores que 0 a operação foi realizada com sucesso.
     */
    public int executaSQL(String sql){
        try {
            iniciarConexao();
            Statement stmt = con.createStatement();
            int res = stmt.executeUpdate(sql);
            con.close();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
}
