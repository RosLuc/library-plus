/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emprestimo;


import Classes.Material;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
/**
 * Classe para objetos do tipo Emprestimo, onde serão contidos, valores e métodos para o mesmo.
 * @author Adriana
 */
public class Emprestimo {
    private int codemp; 
    private int usercode;
    private int codinsc;
    private Date dataemp;
    private Date datadev;
    private String status;
    private Set<Material> materiais;
    
    /**
     * Construtor padrão.
     */
    public Emprestimo() {
    }

    /**
     * Construtor Completo.
     * @param codemp
     * @param usercode
     * @param codinsc
     * @param dataemp
     * @param datadev
     * @param status 
     */
    public Emprestimo(int codemp, int usercode, int codinsc, Date dataemp, Date datadev, String status) {
        this.codemp = codemp;
        this.usercode = usercode;
        this.codinsc = codinsc;
        this.dataemp = dataemp;
        this.datadev = datadev;
        this.status = status;
    }

    /**
     * Método de acesso à codemp.
     * @return int - Código do emprestimo
     */
    public int getCodemp() {
        return codemp;
    }

    /**
     * Métedo modificador de codemp.
     * @param codiemp Código do emprestimo.
     */
    public void setCodemp(int codiemp) {
        this.codemp = codiemp;
    }

    /**
     * Método de acesso à usecode.
     * @return int - Código de usuário.
     */
    public int getUsercode() {
        return usercode;
    }

    /**
     * Método modificador de usercode.
     * @param usercode Código de usuário.
     */
    public void setUsercode(int usercode) {
        this.usercode = usercode;
    }

    /**
     * Método de acesso à codinsc.
     * @return int - Código de Inscrição.
     */
    public int getCodinsc() {
        return codinsc;
    }

    /**
     * Método modificador de codinsc.
     * @param codinsc Código de Inscrição.
     */
    public void setCodinsc(int codinsc) {
        this.codinsc = codinsc;
    }

    /**
     * Método de acesso à dataemp.
     * @return Date - Data de emprestmo.
     */
    public Date getDataemp() {
        return dataemp;
    }

    /**
     * Método modificador de dataemp.
     * @param dataemp Data de emprestimo.
     */
    public void setDataemp(Date dataemp) {
        this.dataemp = dataemp;
    }

    /**
     * Método de acesso a datadev.
     * @return Date - Data de devolução.
     */
    public Date getDatadev() {
        return datadev;
    }

    /**
     * Método modificador de datadev.
     * @param datadev - Data de devolução.
     */
    public void setDatadev(Date datadev) {
        this.datadev = datadev;
    }

    /**
     * Método de acesso a status.
     * @return String - Status de emprestimo.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Método modificador de status.
     * @param status - Status de emprestimo.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Método de acessor à referencia de Set do tipo Material.
     * @return Set - Set do tipo material.
     */
    public Set<Material> getMateriais() {
        return materiais;
    }
    /**
     * Método para modificar referencia de Set do tipo Material.
     * @param materiais Set do tipo material.
     */
    public void setMateriais(Set<Material> materiais) {
        this.materiais = materiais;
    }
    
    
    
    /**
     * Método  responsável por salvar um objeto Emprestimo no banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return boolean - Caso a operação for realizada com sucesso returna true, caso contrário false.
     */
    public boolean salvarEmprestimo(){
        try{
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            Transaction tx_part = sessao.beginTransaction();
            sessao.save(this);
            tx_part.commit();
            sessao.close();
            return true;  
        }
        catch(HibernateException e){
            return false;
        }
    }
    
    /**
     * Método  responsável por excluir um objeto Emprestimo no banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return boolean - Caso a operação for realizada com sucesso returna true, caso contrário false.
     */ 
    public boolean excluirEmprestimo() {
        try{
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            Transaction tx_part = sessao.beginTransaction();
            sessao.delete(this);
            tx_part.commit();
            sessao.close();
            fabrica.close();
            return true;
        }
        catch(HibernateException e){
            return false;
        }
    }
    
    /**
     * Método  responsável por atualizar um objeto Emprestimo no banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return boolean - Caso a operação for realizada com sucesso returna true, caso contrário false.
     */   
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
    
     
    
    /**
     * Método responsável por obter uma lista de objetos do tipo Emprestimo do banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return List - Caso a operação for realizada com sucesso retorna lista de objetos do tipo Livro, caso contrário retorna null.
     */    
    @SuppressWarnings("unchecked")
    public List<Emprestimo> ListaDeEmprestimo(){
        List<Emprestimo> lista_Emprestimo;
        try{
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
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
    
    /**
     * Método static responsável em obter um objeto do tipo Emprestimo no banco de dados,
     * sendo este com codemp igual parâmentro enviado.
     * A operação é realizada utilizando hibernate.
     * @param codemp Código de emprestimo a ser comparado.
     * @return Emprestimo - Caso a operação for realizada com sucesso retorna um objeto Emprestimo, caso contrário retorna null.
     */    
    static public Emprestimo buscarEmprestimo(int codemp){
        try{
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            Emprestimo emp =(Emprestimo) sessao.createCriteria(Emprestimo.class).add(Restrictions.eq("codemp", codemp)).uniqueResult();
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
    
    /**
     * Método static responsável em obter uma List de objetos do tipo Emprestimo no banco de dados,
     * sendo esta contendo emprestimos relacionados com codinsc igual parâmentro enviado.
     * A operação é realizada utilizando hibernate.
     * @param codinsc Código de inscrição de Pessoa que possue algum emprestimo a ser comparado.
     * @return List - Caso a operação seja realizada com sucesso retorna uma List de Emprestimo, caso contrário retorna null.
     */    
    static public List<Emprestimo> buscarEmprestimoDePessoa(int codinsc){
        try{
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            @SuppressWarnings("unchecked")
            List<Emprestimo> listEmp = sessao.createCriteria(Emprestimo.class).add(Restrictions.eq("codinsc", codinsc)).list();
            sessao.close();
            fabrica.close();
            return listEmp;
        }
        catch(HibernateException e){
            System.err.println("Erro: " + e);
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Método sobreposto da superclasse Material responsável em obter uma lista de objetos do tipo Livro no banco de dados, 
     * filtrados conforme data de emprestimo atrasada.
     * A operação é realizada utilizando hibernate.
     * @return List - Caso a operação for realizada com sucesso retorna lista de objetos do tipo Livro,  caso contrário retorna null.
     *//*
    public List<Livro> filtrarMaterialAtraso(){
        List<Livro> listLivro;
        try{
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            listLivro = session.createCriteria(Livro.class).add(Restrictions.le("data", new Date())).addOrder(Order.desc("data")).list();
            session.close();
            factory.close();
            return listLivro;
        }catch(HibernateException e){
             System.err.println("Erro ao filtrar: " + e);
             e.printStackTrace();
             return null;
        } 
    }*/
}
