package Classes;

import java.sql.Connection;
import java.util.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.criterion.*;

/**
 * Classe para objetos do tipo Livro, onde serão contidos, valores e métodos para o mesmo.
 * Subclasse da classe Material.
 * @author LGDantas
 * @version 1.0
 */
public class Livro  extends Material{
   
     private String autor;
     private String editora;
     
    /**
     * Construto padrão.
     */
    public Livro() {
    }
    
    /**
     * Construtor completo que recebe argumentos para inicializar os campos da classe.
     * Utilizar a palavra chave super para chamar o construtor da superclasse.
     * @param autor Autor do livro.
     * @param editora Editora do livro.
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
    public Livro(String autor, String editora, String nchamada, int usercode, int codestante, int codprateleira, int nsequencia, Date data, String titulo, int exemplar, int volume, String local, int anopublicacao, String formadeaquisicao, String observacao, String status) {
        super(nchamada, usercode, codestante, codprateleira, nsequencia, data, titulo, exemplar, volume, local, anopublicacao, formadeaquisicao, observacao, status);
        this.autor = autor;
        this.editora = editora;
    }
    
    /**
     * Método de acesso à autor.
     * @return String - Nome do autor do livro.
     */
    public String getAutor() {
        return this.autor;
    }
    
    /**
     * Método modificador para autor.
     * @param autor Nome do autor do livro.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    /**
     * Método de acesso à editora.
     * @return String -  Nome da editora do livro.
     */
    public String getEditora() {
        return this.editora;
    }
    
    /**
     * Método modificador para editora.
     * @param editora Nome da editora do livro.
     */
    public void setEditora(String editora) {
        this.editora = editora;
    }
    
    /**
     * Método sobreposto da superclasse Material responsável por cadastrar um objeto Livro no banco de dados.
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
     * Método sobreposto da superclasse Material responsável por atualizar um objeto Livro no banco de dados.
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
     *Método sobreposto da superclasse Material responsável por obter uma lista de objetos do tipo Livro do banco de dados conforme clásula de seleção enviada para str.
     * A operação é realizada utilizando hibernate.
     * @return List - Caso a operação for realizada com sucesso retorna lista de objetos do tipo Livro, caso contrário retorna null.
     */
    public List listarMaterial(){
        try {
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            List<Livro> listLivro= new ArrayList();
            listLivro = session.createQuery("from Classes.Livro").list();
            session.close();
            return listLivro;
        } catch (HibernateException e) {
             System.err.println("Erro na seleção: " + e);
             e.printStackTrace();
             return null;
        }
    }
        
    /**
     * Método sobreposto da superclasse Material responsável por deletar um objeto Livro no banco de dados.
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
     * Método sobresposto da superclasse Material responsável em obter uma lista de objetos do tipo Livro no banco de dados, 
     * filtrados conforme campos digitados e adicionados ao objeto enviado como parâmetro.
     * A operação é realizada utilizando hibernate.
     * @return List - Caso a operação for realizada com sucesso retorna lista de objetos do tipo Livro, caso contrário retorna null.
     */
    public List filtrarMaterialCMP(){
        List<Livro> listLivro = null;
        try{
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            Example exp = Example.create(this).enableLike(MatchMode.ANYWHERE).excludeZeroes().ignoreCase();
            listLivro = session.createCriteria(Livro.class).add(exp).addOrder(Order.desc("nsequencia")).list();
            session.close();
            return listLivro;
        }catch(HibernateException e){
             System.err.println("Erro ao filtrar: " + e);
             e.printStackTrace();
             return null;
        }   
    }
    
    /**
     * Método sobreposto da superclasse Material responsável em obter uma lista de objetos do tipo Livro no banco de dados, 
     * filtrados conforme data de emprestimo atrasada.
     * A operação é realizada utilizando hibernate.
     * @return List - Caso a operação for realizada com sucesso retorna lista de objetos do tipo Livro,  caso contrário retorna null.
     */
    public List filtrarMaterialAtraso(){
        List<Livro> listLivro = null;
        try{
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            listLivro = session.createCriteria(Livro.class).add(Restrictions.le("data", new Date())).addOrder(Order.desc("data")).list();
            session.close();
            return listLivro;
        }catch(HibernateException e){
             System.err.println("Erro ao filtrar: " + e);
             e.printStackTrace();
             return null;
        } 
    }

    /**
     * Método sobreposto da superclasse Material responsável em obter um objeto do tipo Livro no banco de dados,
     * sendo este com nchamada igual ao objeto instanciado.
     * A operação é realizada utilizando hibernate.
     * @param nchamada Número de sequencia a ser comparado.
     * @return Livro - Caso a operação for realizada com sucesso retorna um objeto Livro, caso contrário retorna null.
     */
    public Material buscarMaterialNC(String nchamada){
        Livro l = null;
        try{
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            Criterion cri1 = Restrictions.ilike("nchamada", nchamada);
            l = (Livro) session.createCriteria(Livro.class).add(cri1).uniqueResult();
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
     * @return Livro - Caso a operação for realizada com sucesso retorna um objeto Livro, caso contrário retorna null.
     */
    public Material buscarMaterialNS(int nseq){
        Livro l = null;
        try{
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            Criterion cri1 = Restrictions.eq("nsequencia", nseq);
            l = (Livro) session.createCriteria(Livro.class).add(cri1).uniqueResult();
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



