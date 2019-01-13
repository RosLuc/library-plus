/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pessoa;

/**
 *
 * @author lucas
 */
public class testePessoa {
    
    public static void main(String[] args) {
        Pessoa p = new Pessoa();
            p.setBairro("Manoel");
            p.setCategoria("a");
            p.setCelular(99695866);
            p.setCep("099");
            p.setCidade("pdf");
            p.setCodinsc(3335);
            p.setEmail("lucas-rosendo@hotmail.com");
            p.setEndereco("RN-PDF");
            p.setEstado("RN");
            p.setNome("rosendo");
            p.setNumero(171);
            p.setSerie("b");
            p.setTelefone(40028922);
            p.setTurma("1");
            p.setTurno("nocturne");
            p.setUsercode(1111);
            if(p.salvarPessoa()) System.out.println("OK");
            else System.err.println("ERRO");
    }
    
}
