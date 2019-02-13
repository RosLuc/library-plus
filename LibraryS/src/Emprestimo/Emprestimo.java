/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emprestimo;


import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.GregorianCalendar;
import java.util.List;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author maria
 */
public class Emprestimo {
    private int codiemp; 
    private int usercode;
    private int codinsc;
    private GregorianCalendar dataemp;
    private GregorianCalendar datadev;
    private String status;

    public Emprestimo() {
    }

    public Emprestimo(int codiemp, int usercode, int codinsc, GregorianCalendar dataemp, GregorianCalendar datadev, String status) {
        this.codiemp = codiemp;
        this.usercode = usercode;
        this.codinsc = codinsc;
        this.dataemp = dataemp;
        this.datadev = datadev;
        this.status = status;
    }

    public int getCodiemp() {
        return codiemp;
    }

    public void setCodiemp(int codiemp) {
        this.codiemp = codiemp;
    }

    public int getUsercode() {
        return usercode;
    }

    public void setUsercode(int usercode) {
        this.usercode = usercode;
    }

    public int getCodinsc() {
        return codinsc;
    }

    public void setCodinsc(int codinsc) {
        this.codinsc = codinsc;
    }

    /*public Date getDataemp() {
        return dataemp;
    }

    public void setDataemp(Date dataemp) {
        this.dataemp = dataemp;
    }

    public Date getDatadev() {
        return datadev;
    }

    public void setDatadev(Date datadev) {
        this.datadev = datadev;
    }*/

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    
    public List ListaDeEmprestimo(){
        List<Emprestimo> lista_Emprestimo;
        try{
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            lista_Emprestimo = new ArrayList();
            lista_Emprestimo = sessao.createCriteria(Emprestimo.class).addOrder(Order.asc("nome")).list();
            sessao.close();
            fabrica.close();
            return lista_Emprestimo;
        }
        catch(HibernateException e){
            System.err.println("Erro: "+e);
            e.printStackTrace();
            return null;
        }
    }
    
    public Emprestimo buscarEmprestimo(int ccodiemp){
        try{
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            Emprestimo emp =(Emprestimo) sessao.createCriteria(Emprestimo.class).add(Restrictions.eq("codiemp", codiemp)).uniqueResult();
            sessao.close();
            fabrica.close();
            return emp;
        }
        catch(HibernateException e){
            System.err.println("Erro: "+e);
            e.printStackTrace();
            return null;
        }
    }
     public List filtrarEmprestimo(){
        try{
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            List<Emprestimo> listaEmprestimo = new ArrayList();
            Example exp = Example.create(this).enableLike(MatchMode.ANYWHERE).excludeZeroes().ignoreCase();
            listaEmprestimo = sessao.createCriteria(Emprestimo.class).add(exp).addOrder(Order.desc("nome")).list();
            sessao.close();
            fabrica.close();
            return listaEmprestimo;
        }
        catch(HibernateException e){
            System.err.println("Erro: "+e);
            e.printStackTrace();
            return null;
        }
    } 
}
