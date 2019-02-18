/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emprestimo;

import Classes.Livro;
import Classes.Material;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
       /*emp = emp.buscarEmprestimo(6);
       Set<Material> mat = emp.getMateriais();
       Iterator<Material> i = mat.iterator();
       while(i.hasNext()){
           Material m = i.next();
           System.out.println(m);
       }*/
        emp.setCodinsc(1);
        emp.setUsercode(11111);
        GregorianCalendar c = new GregorianCalendar();
        emp.setDataemp(c.getTime());
        c.add(GregorianCalendar.DAY_OF_WEEK_IN_MONTH, 7);
        emp.setDatadev(c.getTime());
        emp.setStatus("Ativo");
        Set<Material> m = new HashSet<>(5);
        Livro e = new Livro();
        e.setNchamada(7);
        m.add(e.buscarMaterialNC());
        e.setNchamada(5);
        m.add(e.buscarMaterialNC());
        e.setNchamada(6);
        m.add(e.buscarMaterialNC());
        emp.setMateriais(m);
        emp.salvarEmprestimo();
    }
    
    
    
}
