/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

/**
 *
 * @author lucas
 */
public class TesteUser {
    
    public static void main(String[] args){
        Usuario user = new Usuario();
        user.setLogin("roseluk");
        user.setSenha("8237");
        if(user.validarUsuario()) System.out.println("OK");
        else System.out.println("ERRO");
    }
}
