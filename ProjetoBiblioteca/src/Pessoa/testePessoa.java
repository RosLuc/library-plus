/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pessoa;

import java.util.List;
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
            p.setCodinsc(3334);
            p.setEmail("lucas-rosendo@hotmail.com");
            p.setEndereco("RN-PDF");
            p.setEstado("RN");
            p.setNome("rosendo");
            p.setNumero(9);
            p.setSerie("b");
            p.setTelefone(40028922);
            p.setTurma("1");
            p.setTurno("nocturne");
            p.setUsercode(1111);
            p.salvarPessoa();
            Pessoa temp = new Pessoa();
            List<Pessoa> lista = temp.filtrarPessoa("nome", "rosendo");
            temp = lista.get(0);
            System.out.println(temp.getCodinsc()+ "\n" +temp.getNome());
            
    }
    
}
