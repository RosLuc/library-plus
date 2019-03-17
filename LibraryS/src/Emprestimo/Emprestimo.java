/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emprestimo;


import Classes.Material;
import Pessoa.Pessoa;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
/**
 * Classe para objetos do tipo Emprestimo, onde serão contidos, valores e métodos para o mesmo.
 * @author Adriana
 */
public class Emprestimo {
    private int codemp; 
    private int usercode;
    private Pessoa pessoa;
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
     * @param codemp Código do emprestimo.
     * @param usercode Código do usúario, relacionado ao banco de dados. 
     * @param pessoa Pessoa que realiza o emprestimo.
     * @param dataemp Data da realização do emprestimo.
     * @param datadev Data para devolução do emprestimo.
     * @param status status do emprestimo.
     */
    public Emprestimo(int codemp, int usercode, Pessoa pessoa, Date dataemp, Date datadev, String status) {
        this.codemp = codemp;
        this.usercode = usercode;
        this.pessoa = pessoa;
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
     * Método de acesso a pessoa.
     * @return Pessoa - Objeto do tipo Pessoa
     */
    public Pessoa getPessoa() {
        return pessoa;
    }

    /**
     * Método modificador de pessoa.
     * @param pessoa Objeto Pessoa.
     */
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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
     * Método responsável por obter uma lista de objetos do tipo Emprestimo do banco de dados
     * ordenado conforme data de devolução do emprestimo.
     * A operação é realizada utilizando hibernate.
     * @return List - Caso a operação for realizada com sucesso retorna lista de objetos do tipo Livro, caso contrário retorna null.
     */    
    @SuppressWarnings("unchecked")
    public static List<Emprestimo> ListaDeEmprestimo(){
        List<Emprestimo> lista_Emprestimo;
        try{
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            lista_Emprestimo = sessao.createCriteria(Emprestimo.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).addOrder(Order.desc("datadev")).list();
            sessao.close();
            fabrica.close();
            return lista_Emprestimo;
        }
        catch(HibernateException e){
            System.err.println("Erro: " + e);
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
     public static Emprestimo buscarEmprestimo(int codemp){
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
     public static List<Emprestimo> buscarEmprestimoDePessoa(int codinsc){
        try{
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            @SuppressWarnings("unchecked")
            List<Emprestimo> listEmp = sessao.createCriteria(Emprestimo.class).createCriteria("pessoa").add(Restrictions.eq("codinsc", codinsc)).list();
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
     * Método static responsável em obter um objetos do tipo Emprestimo no banco de dados,
     * sendo relacionado com um Material com número de chamada igual parâmentro enviado.
     * A operação é realizada utilizando hibernate.
     * @param nchamada Número de chamada de um material associado em algum emprestimo a ser comparado.
     * @return List - Caso a operação seja realizada com sucesso retorna um Objeto de Emprestimo, caso contrário retorna null.
     */    
     public static Emprestimo buscarEmprestimoDeMaterial(int nchamada){
        try{
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            @SuppressWarnings("unchecked")
            Emprestimo Emp = (Emprestimo) sessao.createCriteria(Emprestimo.class).createAlias("materiais", "m").add(Restrictions.eq("m.nchamada", nchamada)).uniqueResult();
            sessao.close();
            fabrica.close();
            return Emp;
        }
        catch(HibernateException e){
            System.err.println("Erro: " + e);
            e.printStackTrace();
            return null;
        }
    }
}
