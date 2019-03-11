/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.List;

/**
 *
 * @author lucas
 */
public class teste {
    public static void main(String[] args) {
        Livro livro = new Livro();
        List<Livro> lista = livro.listarMaterial();
        if(lista == null) System.out.println("OK!");
        else System.out.println("erro!");
    }
}
