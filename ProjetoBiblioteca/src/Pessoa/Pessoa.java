package Pessoa;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * Classe para objetos do tipo Pessoa, onde serão contidos, valores e métodos para o mesmo.
 * @author Lucas
 * @version 1.5
 */
public class Pessoa {
    private int codinsc;
    private int usercode;
    private String estado;
    private String numero;
    private String serie;
    private String endereco;
    private String turno;
    private String bairro;
    private String turma;
    private String cidade;
    private String categoria;
    private String email;
    private String contato;
    private String nome;
    private String cep;
    
    /**
     * Método contrutor da classe Pessoa.
     * Responsavel por inicializar o objeto.
     */
    public Pessoa() {
    }
    
    /**
     * Método contrutor completo da classe Pessoa.
     * Responsavel por inicializar o objeto e todos seus metodos.
     * @param codinsc - Código de inscrição da pessoa.
     * @param usercode - Código do usuário.
     * @param estado - Estado da residência da pessoa.
     * @param numero - Nº da residência da pessoa.
     * @param serie - Serie da Pessoa;
     * @param endereco  - Endereço da residência da pessoa.
     * @param turno - Turno da pessoa.
     * @param bairro - Bairro da residência da pessoa.
     * @param turma - Turma da pessoa.
     * @param cidade - Cidade da residência da pessoa.
     * @param categoria - Categoria da pessoa.
     * @param email - Email da pessoa.
     * @param celular - Celular da pessoa.
     * @param nome - Nome da pessoa.
     * @param telefone - Telefone da pessoa.
     * @param cep - CEP da residência da pessoa.
     */
    public Pessoa(int codinsc, int usercode, String estado, String numero,
            String serie, String endereco, String turno, String bairro,
            String turma, String cidade, String categoria, String email,
            String contato, String nome, String cep) {
        this.codinsc = codinsc;
        this.usercode = usercode;
        this.estado = estado;
        this.numero = numero;
        this.serie = serie;
        this.endereco = endereco;
        this.turno = turno;
        this.bairro = bairro;
        this.turma = turma;
        this.cidade = cidade;
        this.categoria = categoria;
        this.email = email;
        this.nome = nome;
        this.cep = cep;
    }

    
    
    /**
    * Método para acessar o codinsc.
    * @return int - Codigo da pessoa.
    */
    public int getCodinsc() {
        return codinsc;
    }

    /**
    * Método para modificar o codinsc.
    * @param codinsc - Codigo da pessoa
    */
    public void setCodinsc(int codinsc) {
        this.codinsc = codinsc;
    }

    /**
    * Método para acessar o usercode.
    * @return int - Codigo do usuario.
    */
    public int getUsercode() {
        return usercode;
    }

    /**
    * Método para modificar o usercode.
    * @param usercode - Codigo do usuario.
    */
    public void setUsercode(int usercode) {
        this.usercode = usercode;
    }

    /**
    * Método para acessar o estado.
    * @return String - Estado do usuario.
    */
    public String getEstado() {
        return estado;
    }

    /**
    * Método para modificar o estado.
    * @param estado - Estado do usuario.
    */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
    * Método para acessar o numero.
    * @return String - Nº da residência.
    */
    public String getNumero() {
        return numero;
    }

    /**
    * Método para modificar o numero.
    * @param numero - Nº da residência.
    */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
    * Método para acessar a serie.
    * @return String - serie da pessoa(aluno).
    */
    public String getSerie() {
        return serie;
    }

    /**
    * Método para modificar o numero.
    * @param serie - série da pessoa(aluno).
    */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
    * Método para acessar a endereco.
    * @return String - Endereço da pessoa.
    */
    public String getEndereco() {
        return endereco;
    }

    /**
    * Método para modificar o endereco.
    * @param endereco - Endereço da pessoa.
    */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
    * Método para acessar o turno.
    * @return String - Turno da pessoa.
    */
    public String getTurno() {
        return turno;
    }

    /**
    * Método para modificar o turno.
    * @param turno - Turno da pessoa.
    */
    public void setTurno(String turno) {
        this.turno = turno;
    }

    /**
    * Método para acessar o bairro.
    * @return String - Bairro da pessoa.
    */
    public String getBairro() {
        return bairro;
    }

    /**
    * Método para modificar o bairro.
    * @param bairro - Bairro da pessoa.
    */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
    * Método para acessar a turma.
    * @return String - Turma da pessoa.
    */
    public String getTurma() {
        return turma;
    }

    /**
    * Método para modificar a turma.
    * @param turma - Turma da pessoa.
    */
    public void setTurma(String turma) {
        this.turma = turma;
    }

    /**
    * Método para acessar a cidade.
    * @return String - Cidade da pessoa.
    */
    public String getCidade() {
        return cidade;
    }

    /**
    * Método para modificar a cidade.
    * @param cidade - Cidade da pessoa.
    */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
    * Método para acessar a categoria.
    * @return String - Categoria da pessoa.
    */
    public String getCategoria() {
        return categoria;
    }

    /**
    * Método para modificar a categoria.
    * @param categoria - Categoria da pessoa.
    */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
    * Método para acessar a Email.
    * @return String - Email da pessoa.
    */
    public String getEmail() {
        return email;
    }

    /**
    * Método para modificar a email.
    * @param email - Email da pessoa.
    */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
    * Método para acessar o contato.
    * @return int - Celular da pessoa.
    */
    public String getContato() {
        return contato;
    }

    /**
    * Método para modificar o contato.
    * @param celular - Celular da pessoa.
    */
    public void setContato(String celular) {
        this.contato = celular;
    }

    /**
    * Método para acessar a Nome.
    * @return String - Nome da pessoa.
    */
    public String getNome() {
        return nome;
    }

    /**
    * Método para modificar a nome.
    * @param nome - Nome da pessoa.
    */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
    * Método para acessar a CEP.
    * @return int - CEP da pessoa.
    */
    public String getCep() {
        return cep;
    }

    /**
    * Método para modificar a cep.
    * @param cep - CEP da pessoa.
    */
    public void setCep(String cep) {
        this.cep = cep;
    }
    
    /**
     * Metodo responsável por salvar o objeto pessoa no banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return boolean - Caso a operação for realizada com sucesso retorna true, caso contrário retorna false.
     */
    public boolean salvarPessoa(){
        try{
            SessionFactory fabrica = new Configuration().configure("Hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            Transaction tx_part = sessao.beginTransaction();
            sessao.save(this);
            tx_part.commit();
            sessao.close();
            fabrica.close();
            return true;
        }
        catch(HibernateException e){
            System.err.println("Erro: "+e);
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Metodo responsável por atualizar o objeto pessoa no banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return boolean - Caso a operação for realizada com sucesso retorna true, caso contrário retorna false.
     */
    public boolean atualizaPessoa(){
        try{
            SessionFactory fabrica = new Configuration().configure("Hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            Transaction tx_part = sessao.beginTransaction();
            sessao.update(this);
            tx_part.commit();
            sessao.close();
            fabrica.close();
            return true;
        }
        catch(HibernateException e){
            System.err.println("Erro: "+e);
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Metodo responsável por listar os objetos pessoa salvos no banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return List - Caso a operação for realizada com sucesso retorna uma lista de pessoa, caso contrário retorna null.
     */
    public List ListaDePessoa(){
        List<Pessoa> lista_Pessoa;
        try{
            SessionFactory fabrica = new Configuration().configure("Hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            lista_Pessoa = new ArrayList();
            lista_Pessoa = sessao.createCriteria(Pessoa.class).addOrder(Order.asc("nome")).list();
            sessao.close();
            fabrica.close();
            return lista_Pessoa;
        }
        catch(HibernateException e){
            System.err.println("Erro: "+e);
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Metodo responsável por excluir o objeto pessoa do banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return boolean - Caso a operação for realizada com sucesso retorna true, caso contrário retorna false.
     */
    public boolean excluirPessoa(){
        try{
            SessionFactory fabrica = new Configuration().configure("Hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            Transaction tx_part = sessao.beginTransaction();
            sessao.delete(this);
            tx_part.commit();
            sessao.close();
            fabrica.close();
            return true;
        }
        catch(HibernateException e){
            System.err.println("Erro: "+e);
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Metodo responsável por buscar o objeto pessoa salvo no banco de dados pelo codinsc.
     * A operação é realizada utilizando hibernate.
     * @param codInsc - Codigo de inscrição da pessoa.
     * @return List - Caso a operação for realizada com sucesso retorna uma lista de pessoa, caso contrário retorna null.
     */
    public Pessoa buscarPessoa(int codInsc){
        try{
            SessionFactory fabrica = new Configuration().configure("Hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            Pessoa p =(Pessoa) sessao.createCriteria(Pessoa.class).add(Restrictions.eq("codinsc", codInsc)).uniqueResult();
            sessao.close();
            fabrica.close();
            return p;
        }
        catch(HibernateException e){
            System.err.println("Erro: "+e);
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Metodo responsável por filtrar uma lista dos objetos pessoa salvos no banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return List - Caso a operação for realizada com sucesso retorna uma lista de pessoa, caso contrário retorna null.
     */
    public List filtrarPessoa(){
        try{
            SessionFactory fabrica = new Configuration().configure("Hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            List<Pessoa> listaPessoa = new ArrayList();
            Example exp = Example.create(this).enableLike(MatchMode.ANYWHERE).excludeZeroes().ignoreCase();
            listaPessoa = sessao.createCriteria(Pessoa.class).add(exp).addOrder(Order.desc("nome")).list();
            sessao.close();
            fabrica.close();
            return listaPessoa;
        }
        catch(HibernateException e){
            System.err.println("Erro: "+e);
            e.printStackTrace();
            return null;
        }
    }
}
