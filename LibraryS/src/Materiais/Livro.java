package Materiais;

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
     private int cdu;
     private int cdd;
     
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
     * @param corestante Cor da estante relacionado ao BD (passado para superclasse).
     * @param codprateleira Código da prateleira relacionado ao BD (passado para superclasse).
     * @param cdu Número do CDU.
     * @param cdd Número do CDD.
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
    public Livro(String autor, String editora, int cdu, int cdd, int nchamada, int usercode, String corestante, int codprateleira, Date data, String titulo, int exemplar, int volume, String local, int anopublicacao, String formadeaquisicao, String observacao, String status) {
        super(nchamada, usercode, corestante, codprateleira, data, titulo, exemplar, volume, local, anopublicacao, formadeaquisicao, observacao, status);
        this.autor = autor;
        this.editora = editora;
        this.cdu = cdu;
        this.cdd = cdd;
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
     * Método de acesso ao CDU.
     * @return int -  Número do CDU.
     */
    public int getCdu() {
        return cdu;
    }

    /**
     * Método modificador para cdu.  
     * @param cdu Número do cdu.
     */
    public void setCdu(int cdu) {
        this.cdu = cdu;
    }
    /*
     * Método de acesso ao cdd.
     * @return int -  Número do cdd.
     */
    public int getCdd() {
        return cdd;
    }

    /**
     * Método modificador para cdd.  
     * @param cdd Número do cddd.
     */
    public void setCdd(int cdd) {
        this.cdd = cdd;
    }

    @Override
    public String toString() {
        return "Livro{" + "autor=" + autor + ", editora=" + editora + ", cdu=" + cdu + ", cdd=" + cdd + '}';
    }
    
    /**
     * Método sobreposto da superclasse Material responsável por cadastrar um objeto Livro no banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return boolean - Caso a operação for realizada com sucesso returna true, caso contrário false.
     */
     @Override
    public boolean cadastrarMaterial(){
        try {
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(this);
            tx.commit();
            session.close();
            factory.close();
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
     @Override
    public boolean updateMaterial(){
        try {
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.update(this);
            tx.commit();
            session.close();
            factory.close();
            return true;
        } catch (HibernateException e) {
            System.err.println("Erro na atualização Material: " + e);
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     *Método sobreposto da superclasse Material responsável por obter uma lista de objetos do tipo Livro do banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return List - Caso a operação for realizada com sucesso retorna lista de objetos do tipo Livro, caso contrário retorna null.
     */
     @Override
    public List<Livro> listarMaterial(){
        try {
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            @SuppressWarnings("unchecked")
            List<Livro> listLivro= session.createCriteria(Livro.class).addOrder(Order.asc("nchamada")).list();
            session.close();
            factory.close();
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
     @Override
    public boolean deleteMaterial(){
        try {
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();            
            Transaction tx = session.beginTransaction();
            session.delete(this);
            tx.commit();
            session.close();
            factory.close();
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
     @Override
     @SuppressWarnings("unchecked")
    public List<Livro> filtrarMaterialCMP(){
        List<Livro> listLivro;
        SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();
        try{
            Example exp = Example.create(this).enableLike(MatchMode.ANYWHERE).excludeZeroes().ignoreCase();
            Criteria cri = session.createCriteria(Livro.class).add(exp).addOrder(Order.asc("nchamada"));
            if(this.getNchamada() != 0) cri.add(Restrictions.eq("id", this.getNchamada()));
            listLivro = cri.list();
            return listLivro;
        }catch(HibernateException e){
            System.err.println("Erro ao filtrar: " + e);
            e.printStackTrace();
            return null;
        }finally{
            session.close();
            factory.close();
        }   
    }

    /**
     * Método sobreposto da superclasse Material responsável em obter um objeto do tipo Livro no banco de dados,
     * sendo este com nchamada igual ao objeto instanciado.
     * A operação é realizada utilizando hibernate.
     * @return Livro - Caso a operação for realizada com sucesso retorna um objeto Livro, caso contrário retorna null.
     */
     @Override
    public Livro buscarMaterialNC(){        
        try{
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            Criterion cri1 = Restrictions.eq("nchamada", this.getNchamada());
            Livro l = (Livro) session.createCriteria(Livro.class).add(cri1).uniqueResult();
            session.close();
            factory.close();
            return l;
        }catch(NonUniqueResultException er){
            System.err.println("Erro ao filtrar: " + er);
            return null;
        }catch(HibernateException e){
            System.err.println("Erro ao filtrar: " + e);
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Método responsável em obter um objeto do tipo Livro no banco de dados,
     * sendo este com cdu igual ao objeto instanciado.
     * A operação é realizada utilizando hibernate.
     * @return Livro - Caso a operação for realizada com sucesso retorna um objeto Livro, caso contrário retorna null.
     */
    public Livro buscarLivroCDU(){
        try{
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            Criterion cri1 = Restrictions.eq("cdu", this.getCdu());
            Livro l = (Livro) session.createCriteria(Livro.class).add(cri1).uniqueResult();
            session.close();
            factory.close();
            return l;
        }catch(NonUniqueResultException er){
            System.err.println("Erro ao filtrar: " + er);
            return null;
        }catch(HibernateException e){
            System.err.println("Erro ao filtrar: " + e);
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Método responsável em obter um objeto do tipo Livro no banco de dados,
     * sendo este com cdd igual ao objeto instanciado.
     * A operação é realizada utilizando hibernate.
     * @return Livro - Caso a operação for realizada com sucesso retorna um objeto Livro, caso contrário retorna null.
     */
    public Livro buscarLivroCDD(){
        try{
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            Criterion cri1 = Restrictions.eq("cdd", this.getCdd());
            Livro l = (Livro) session.createCriteria(Livro.class).add(cri1).uniqueResult();
            session.close();
            factory.close();
            return l;
        }catch(NonUniqueResultException er){
            System.err.println("Erro ao filtrar: " + er);
            return null;
        }catch(HibernateException e){
            System.err.println("Erro ao filtrar: " + e);
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Método responsável em obter um objeto do tipo Livro no banco de dados,
     * sendo este com titulo igual ao objeto instanciado.
     * A operação é realizada utilizando hibernate.
     * @return Livro - Caso a operação for realizada com sucesso retorna um objeto Livro, caso contrário retorna null.
     */
    public Livro buscarLivroNomePrim(){
        try{
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            Criterion cri1 = Restrictions.eq("titulo", this.getTitulo());
            Livro l = (Livro) session.createCriteria(Livro.class).add(cri1).list().get(0);
            session.close();
            factory.close();
            return l;
        }catch(NonUniqueResultException er){
            System.err.println("Erro ao filtrar: " + er);
            return null;
        }catch(HibernateException e){
            System.err.println("Erro ao filtrar: " + e);
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Método responsável em obter um objeto do tipo Livro no banco de dados,
     * sendo este com cdu titulo ao objeto instanciado.
     * A operação é realizada utilizando hibernate.
     * @return Livro - Caso a operação for realizada com sucesso retorna um objeto Livro, caso contrário retorna null.
     */
    public Livro buscarLivroNomeUlt(){
        try{
            SessionFactory factory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession();
            Criterion cri1 = Restrictions.eq("titulo", this.getTitulo());
            Livro l = (Livro) session.createCriteria(Livro.class).add(cri1).addOrder(Order.desc("exemplar")).list().get(0);
            session.close();
            factory.close();
            return l;
        }catch(NonUniqueResultException er){
            System.err.println("Erro ao filtrar: " + er);
            return null;
        }catch(HibernateException e){
            System.err.println("Erro ao filtrar: " + e);
            e.printStackTrace();
            return null;
        }
    }
}



