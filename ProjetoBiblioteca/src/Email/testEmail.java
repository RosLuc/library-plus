/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

/**
 *
 * @author lucas
 */
public class testEmail {
    
    public static void main(String[] args){
        String remetente = "lucas-rosendo09@hotmail.com";
        String senha = "";
        String destinatario = "maria.anairda@gmail.com";
        Email email = new Email();
        Email.enviarMensagem(remetente, destinatario, "Teste", "Testando.",
                Email.conectarConta(Email.conexaoSMTP(remetente), remetente, senha));
    }
}
