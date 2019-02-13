/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emprestimo;

import java.util.Date;

/**
 *
 * @author maria
 */
public class TesteEmp {

    /**
     *
     * @param args
     */
    public static void main(String[] args){
       Emprestimo emp = new Emprestimo();
       emp.setCodiemp(1);
       emp.setCodinsc(3);
       emp.setUsercode(11111);
       emp.setDataemp(new Date());
       emp.setDatadev(new Date());
       emp.setStatus("Disp");
       emp.salvarEmprestimo();
       
    }
    
    
    
}
