package Classes;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Classe para objetos do tipo Prateleira, onde serão contidos, valores e métodos para o mesmo.
 * @author LGDantas
 * @version 1.0
 */
public class Prateleira {
     private int codestante;
     private int codPrateleira;
     private byte corredor;
     private String genero;
     
    /**
     * Construtor padrão.
     */
    public Prateleira() {
    }
    
    /**
     * Construtor completo que recebe argumentos para inicializar os campos da classe.
     * @param codestante Codígo da estante
     * @param codPrateleira Codígo da prateleira
     * @param corredor Número do corredor
     * @param genero Gênero
     */
    public Prateleira(int codestante, int codPrateleira, byte corredor, String genero) {
       this.codestante = codestante;
       this.codPrateleira = codPrateleira;
       this.corredor = corredor;
       this.genero = genero;
    }
    
    /**
    * Método de acesso à codestante.
    * @return - int Código da estante
    */   
    public int getCodestante() {
        return this.codestante;
    }
    
    /**
    * Método modificador de codestante.
    * @param codestante Código da estante
    */    
    public void setCodestante(int codestante) {
        this.codestante = codestante;
    }
    
    /**
    * Método de acesso à codprateleira.
     * @return - int Codígo da prateleira
    */  
    public int getCodPrateleira() {
        return this.codPrateleira;
    }
    
    /**
     *  Método modificador de codprateleira.
     * @param codPrateleira Código da patreleira
     */
    public void setCodPrateleira(int codPrateleira) {
        this.codPrateleira = codPrateleira;
    }
    
    /**
     *  Método de acesso à corredor.
     * @return - int Número do corredor
    */  
    public byte getCorredor() {
        return this.corredor;
    }
    
    /**
     *  Método modificador de corredor.
     * @param corredor Número de corredor
     */
    public void setCorredor(byte corredor) {
        this.corredor = corredor;
    }
    
    /**
    *  Método de acesso à corredor.
    * @return - String Gênero do livro
    */
    public String getGenero() {
        return this.genero;
    }
    
    /**
     *  Método modificador de genero.
     * @param genero Gênero do livro
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    /**
     * Método responsavel por cadastrar um objeto Prateleira no banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return boolean - Caso a operação for realizada com sucesso returna true, caso contrário false.
     */
    public boolean addPrateleira(){
        try {
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(this);
            tx.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
             System.err.println("Erro na insersão Prateleira:." + e);
             e.printStackTrace();
             return false;
        }
    }
    
    /**
     * Método para atualizar um objeto Prateleira no banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return boolean - Caso a operação for realizada com sucesso returna true, caso contrário false.
     */
    public boolean updatePrateleira(){
        try {
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.update(this);
            tx.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
             System.err.println("Erro na atualização Prateleira: " + e);
             e.printStackTrace();
             return false;
        }
    }
    
    /**
     * Método responsável por obter uma lista de objetos do tipo Prateleira do banco de dados.
     * A operação é realizada utilizando hibernate. 
     * @return List - Caso a operação for realizada com sucesso retorna uma lista de objetos do tipo Pateleira, caso contrário retorna null.
     */
    public List<Prateleira> listarPrateleira(){
        try {
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            List<Prateleira> listPrateleira= new ArrayList();
            listPrateleira = session.createQuery("from Classes.Livro").list();
            session.close();
            return listPrateleira;
        } catch (HibernateException e) {
             System.err.println("Erro na seleção Prateleira: " + e);
             e.printStackTrace();
             return null;
        }
        
    }
    
    /**
     * Método responsável por deletar um objeto Prateleira no banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return boolean - Caso a operação for realizada com sucesso returna true, caso contrário false.
     */
    public boolean deletePrateleira(){
        try {
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();            
            Transaction tx = session.beginTransaction();
            session.delete(this);
            tx.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
             System.err.println("Erro ao deletar Prateleira: " + e);
             e.printStackTrace();
             return false;
        }
    }
    
    /**
     * Método static responsável por verificar se existir uma prateleira com o número de estante e número de prateleira passada como parametro.
     * @param nestante Número de estante.
     * @param nprateleira Número de prateleira.
     * @return boolean - Caso houver prateleira com as informações returno true, caso não retorne false.
     */
    public static boolean verifcarPrateleira(int nestante, int nprateleira){
        try{
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            Prateleira p = (Prateleira) session.createCriteria(Prateleira.class).add(Restrictions.eq("codestante", nestante)).add(Restrictions.eq("codPrateleira", nprateleira)).uniqueResult();
            session.close();
            return p!=null? true:false;
        }catch(HibernateException e){
            System.err.println("Erro ao filtrar: " + e);
            e.printStackTrace();
            return false;
        }
    }
}


