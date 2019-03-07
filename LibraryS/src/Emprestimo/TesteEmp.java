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
import java.util.List;
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
    @SuppressWarnings("unchecked")
    public static void main(String[] args){
        Emprestimo emp = new Emprestimo();
        emp.setCodinsc(1);
        emp.setUsercode(11111);
        
        GregorianCalendar c = new GregorianCalendar();
        emp.setDataemp(c.getTime());
        c.add(GregorianCalendar.DAY_OF_WEEK_IN_MONTH, 7);
        emp.setDatadev(c.getTime());
        emp.setStatus("Ativo");
        
        Set<Material> mat = new HashSet<>();
        Livro l = new Livro();
        l.setNchamada(1);
        mat.add(l.buscarMaterialNC());
        l.setNchamada(2);
        mat.add(l.buscarMaterialNC());
        
        emp.setMateriais(mat);
        emp.salvarEmprestimo();
        /*Emprestimo emp = Emprestimo.buscarEmprestimo(8);
        Set<Material> mat = emp.getMateriais();
        Iterator<Material> i = mat.iterator();
        while(i.hasNext()){
           Material m = i.next();
           System.out.println(m);
        }*/
        /*
        Emprestimo emp = Emprestimo.buscarEmprestimo(8);
        emp.excluirEmprestimo();*/
        /*Emprestimo emp = Emprestimo.buscarEmprestimo(8);
        Set<Material> mat = new HashSet<>();
        mat = emp.getMateriais();
        Material mm=null;
        Iterator<Material> i = mat.iterator();
        while(i.hasNext()){
           Material m = i.next();
           System.out.println(m);
           if(m.getNchamada()== 6){
               mm = m;
           }
        }
        System.out.println(mm);
        if(mat.remove(mm)){
            System.out.print("BOA");
        }
       emp.setMateriais(mat);
       emp.atualizarEmprestimo*/
    }
    
    
    
}
