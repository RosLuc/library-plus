/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Indicar;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maria
 */
public class Indicar {
    private int n_chamada;
    private int codiemp;

    public int getN_chamada() {
        return n_chamada;
    }

    public void setN_chamada(int n_chamada) {
        this.n_chamada = n_chamada;
    }

    public int getCodiemp() {
        return codiemp;
    }

    public void setCodiemp(int codiemp) {
        this.codiemp = codiemp;
    }

    public Indicar() {
    }

    public Indicar(int n_chamada, int codiemp) {
        this.n_chamada = n_chamada;
        this.codiemp = codiemp;
    }
    
     public boolean salvarEmprestimo(){
        try{
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            Transaction tx_part = sessao.beginTransaction();
            sessao.save(this);
            tx_part.commit();
            sessao.close();
            fabrica.close();
            System.out.println("Emprestimo salvo");
            return true;  
        }
        catch(HibernateException e){
            System.out.println("ERRO ao salvar" );
            return false;
        }
    }
    
    public boolean excluirEmprestimo() {
        try{
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            Transaction tx_part = sessao.beginTransaction();
            sessao.delete(this);
            tx_part.commit();
            sessao.close();
            fabrica.close();
            System.out.println("Emprestimo excluido");
            return true;
        }
        catch(HibernateException e){
            System.out.println("ERRO ao excluir");
            return false;
        }
    }
   
     public boolean atualizarEmprestimo(){
        try{
            //é criado uma sessao de acordo com seu banco de dados, caso for tester configure o arquivo hibernate.gfj.xml
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            Transaction tx_part = sessao.beginTransaction();
            sessao.update(this);
            tx_part.commit();
            sessao.close();
            fabrica.close();            
            return true;
        }
        catch(HibernateException e){
            return false;
        }
    }
        
    public Emprestimo verificarEmprestimo(){
        try{
            //é criado uma sessao de acordo com seu banco de dados, caso for tester configure o arquivo hibernate.gfj.xml
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            //aqui a sessao é iniciado sem nessecidade de ocorrer um Transaction tx_part = sessao.beginTransaction();
            sessao.getTransaction().begin();
            //aqui é onde é importado a primeira linha da tabela para o objeto usuario
            Emprestimo temp = (Emprestimo) sessao.createCriteria(Emprestimo.class).uniqueResult();
            sessao.close();//sessao finalizada
            fabrica.close();
            return temp;          
        }
        catch(HibernateException e){
            return null;
        }
    }
   
}

    
    
}
