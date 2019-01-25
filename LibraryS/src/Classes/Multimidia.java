package Classes;

import java.util.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.criterion.*;

/**
 * Classe para objetos do tipo Multimidia, onde serão contidos, valores e métodos para o mesmo.
 * Subclasse da classe Material.
 * @author LGDantas
 * @version 1.0
 */
public class Multimidia extends Material {
    
     private String produtor;
     private String estudio;
     
    /**
     * Construtor padrão.
     */
    public Multimidia() {
    }
    
    /** 
     * Construtor completo que recebe argumentos para inicializar os campos da classe.
     * Utilizar a palavra chave super para chamar o construtor da superclasse. 
     * @param produtor Nome do produtor.
     * @param estudio Nome do studio.
     * @param nchamada Número de chamada relacionado ao BD (passado para superclasse).
     * @param usercode Código usuário relacionado ao BD (passado para superclasse).
     * @param codestante Código da estante relacionado ao BD (passado para superclasse).
     * @param codprateleira Código da prateleira relacionado ao BD (passado para superclasse).
     * @param nsequencia Número de sequência(passado para superclasse).
     * @param data Data de cadastro (passado para superclasse).
     * @param titulo Título (passado para superclasse).
     * @param exemplar Número do exemplar (passado para superclasse).
     * @param volume Número do volume (passado para superclasse).
     * @param local Local de publicação (passado para superclasse).
     * @param anopublicacao Ano de publicação (passado para superclasse).
     * @param formadeaquisicao Forma de Aquisição (passado para superclasse).
     * @param observacao Observação (passado para superclasse).
     * @param status Status (passado para superclasse).
     */
    public Multimidia(String produtor, String estudio, String nchamada, int usercode, int codestante, int codprateleira, int nsequencia, Date data, String titulo, int exemplar, int volume, String local, int anopublicacao, String formadeaquisicao, String observacao, String status) {
        super(nchamada, usercode, codestante, codprateleira, nsequencia, data, titulo, exemplar, volume, local, anopublicacao, formadeaquisicao, observacao, status);
        this.produtor = produtor;
        this.estudio = estudio;
    }

    /**
     * Método de acesso à produtor.
     * @return - String Nome do produtor.
     */
    public String getProdutor() {
        return this.produtor;
    }
    
    /**
     * Método modificador de produtor.
     * @param produtor Nome do produtor.
     */
    public void setProdutor(String produtor) {
        this.produtor = produtor;
    }
    
    /**
     * Método de acesso à studio.
     * @return - String Nome do studio.
     */
    public String getEstudio() {
        return this.estudio;
    }
    
    /**
     * Método modificador de studio.
     * @param estudio Nome do studio.
     */
    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    /**
     * Método sobreposto da superclasse Material responsável por cadastrar um objeto Multimidia no banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return boolean - Caso a operação for realizada com sucesso returna true, caso contrário false.
     */
    public boolean cadastrarMaterial(){
        try {
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(this);
            tx.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
             System.err.println("Erro na insersão de Material:." + e);
             e.printStackTrace();
             return false;
        }
    }
    
    /**
     * Método sobreposto da superclasse Material responsável por atualizar um objeto Multimidia no banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return boolean - Caso a operação for realizada com sucesso returna true, caso contrário false.
     */
    public boolean updateMaterial(){
        try {
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.update(this);
            tx.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
             System.err.println("Erro na atualização Material: " + e);
             e.printStackTrace();
             return false;
        }
    }
    
    /**
     * Método sobreposto da superclasse Material responsável por obter uma lista de objetos do tipo Multimídia do banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return List - Caso a operação for realizada com sucesso retorna uma lista de objetos do tipo Multimídia, caso contrário retorna null.
     */
    public List listarMaterial(){
        List<Multimidia> listMultimidia;
        try {
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            listMultimidia= new ArrayList();
            listMultimidia = session.createQuery("from Classes.Multimidia").list();
            session.close();
            return listMultimidia;
        } catch (HibernateException e) {
             System.err.println("Erro na seleção: " + e);
             e.printStackTrace();
             return null;            
        }
    }
        
    /**
     * Método sobreposto da superclasse Material responsável por deletar um objeto Multimídia no banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return boolean - Caso a operação for realizada com sucesso returna true, caso contrário false. 
     */
    public boolean deleteMaterial(){
        try {
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();            
            Transaction tx = session.beginTransaction();
            session.delete(this);
            tx.commit();
            session.close(); 
            return true;
        } catch (HibernateException e) {
             System.err.println("Erro ao deletar: " + e);
             e.printStackTrace();
             return false;
        }
    }
        /**
     * Método sobreposto da superclasse Material responsável em obter uma lista de objetos do tipo Multimídia no banco de dados, 
     * filtrados conforme campos digitados e adicionados ao objeto enviado como parâmetro.
     * A operação é realizada utilizando hibernate.
     * @return List - Caso a operação for realizada com sucesso retorna uma lista de objetos do tipo Multimídia, caso contrário retorna null.
     */
    public List filtrarMaterialCMP(){
        try{
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            Example exp = Example.create(this).enableLike(MatchMode.ANYWHERE).excludeZeroes().ignoreCase();
            List<Multimidia> listMultimidia = session.createCriteria(Multimidia.class).add(exp).addOrder(Order.desc("nsequencia")).list();
            session.close();
            return listMultimidia;
        }catch(HibernateException e){
             System.err.println("Erro ao filtrar: " + e);
             e.printStackTrace();
             return null;
        }
    }
    
    /**
     * Método sobreposto da superclasse Material responsável em obter uma lista de objetos do tipo Multimídia no banco de dados, 
     * filtrados conforme data de emprestimo atrasada.
     * A operação é realizada utilizando hibernate.
     * @return List - Caso a operação for realizada com sucesso retorna uma lista de objetos do tipo Multimídia, caso contrário retorna null.
     */
    public List filtrarMaterialAtraso(){
        try{
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            List<Multimidia> listMultimidia = session.createCriteria(Multimidia.class).add(Restrictions.gt("data", new Date())).addOrder(Order.desc("data")).list();
            session.close();
            return listMultimidia;
        }catch(HibernateException e){
             System.err.println("Erro ao filtrar: " + e);
             e.printStackTrace();
             return null;
        } 
    }
    
    /**
     * Método sobreposto da superclasse Material responsável em obter um objeto do tipo Multimídia no banco de dados,
     * sendo este com nchamada igual ao objeto instanciado.
     * A operação é realizada utilizando hibernate.
     * @param nchamada Número de sequencia a ser comparado.
     * @return Livro - Caso a operação for realizada com sucesso retorna um objeto Multimídia, caso contrário retorna null.
     */
    public Material buscarMaterialNC(String nchamada){
        Multimidia l = null;
        try{
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            Criterion cri1 = Restrictions.ilike("nchamada", nchamada);
            l = (Multimidia) session.createCriteria(Multimidia.class).add(cri1).uniqueResult();
            session.close();
            return l;
        }catch(NonUniqueResultException er){
            System.err.println("Erro ao filtrar: " + er);
            return l;
        }catch(HibernateException e){
            System.err.println("Erro ao filtrar: " + e);
            e.printStackTrace();
            return l;
        }
    }
        /**
     * Método sobreposto da superclasse Material responsável em obter um objeto do tipo Livro no banco de dados,
     * sendo este com nsequencia igual ao objeto instanciado.
     * A operação é realizada utilizando hibernate.
     * @param nseq Número de sequencia a ser comparado.
     * @return Multimidia - Caso a operação for realizada com sucesso retorna um objeto Livro, caso contrário retorna null.
     */
    public Material buscarMaterialNS(int nseq){
        Multimidia l = null;
        try{
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            Criterion cri1 = Restrictions.eq("nsequencia", nseq);
            l = (Multimidia) session.createCriteria(Multimidia.class).add(cri1).uniqueResult();
            session.close();
            return l;
        }catch(NonUniqueResultException er){
            System.err.println("Erro ao filtrar: " + er);
            return l;
        }catch(HibernateException e){
            System.err.println("Erro ao filtrar: " + e);
            e.printStackTrace();
            return l;
        }
    }
}


