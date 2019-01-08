/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexaoPG;


/**
 *
 * @author lucas
 */
public class TesteConexao {
    
    public static void main(String[] args) {		
	String url = "jdbc:postgresql://25.84.198.61:5432/Bando_Biblioteca";
        String usuario = "postgres";
        String senha = "8237";
        Conexao c = new Conexao(url, usuario, senha);
        String sql = "INSERT INTO pessoa  values (0003,'ch11','RN',171,'2','jose','1','Manoel','B',"
                    + "'PDF','2','lucas-rosendo09@hotmail.com','40028922',"
                    + "'Rose','Telefone','CEP')";
        //String sql = "INSERT INTO pessoa values(1,'rosendo')";
        int res = c.executaSQL(sql);
        if(res > 0) System.out.print("OK");
        else System.out.print("NOT");
        
    }
    
}
