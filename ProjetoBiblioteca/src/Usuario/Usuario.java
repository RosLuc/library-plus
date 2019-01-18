
package Usuario;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class Usuario {
    private int usercode;
    private String login;
    private String senha;
    private String nome;
    private String email;
    private String cpf;

    /**
     * @return the usercode
     */
    public int getUsercode() {
        return usercode;
    }

    /**
     * @param usercode the usercode to set
     */
    public void setUsercode(int usercode) {
        this.usercode = usercode;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Metodo responsável por salvar o objeto pessoa no banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return boolean - Caso a operação for realizada com sucesso retorna true, caso contrário retorna false.
     */
    public boolean salvarUsuario(){
        try{
            //é criado uma sessao de acordo com seu banco de dados, caso for tester configure o arquivo hibernate.gfj.xml
            SessionFactory fabrica = new Configuration().configure("Hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            Transaction tx_part = sessao.beginTransaction();
            sessao.save(this);
            tx_part.commit();
            sessao.close();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    /**
     * Metodo responsável por exluir o objeto usuario do banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return boolean - Caso a operação for realizada com sucesso retorna true, caso contrário retorna false.
     */
    public boolean excluirUsuario(){
        try{
            //é criado uma sessao de acordo com seu banco de dados, caso for tester configure o arquivo hibernate.gfj.xml
            SessionFactory fabrica = new Configuration().configure("Hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            Transaction tx_part = sessao.beginTransaction();
            sessao.delete(this);
            tx_part.commit();
            sessao.close();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    /**
     * Metodo responsável por atualizar o objeto usuario no banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return boolean - Caso a operação for realizada com sucesso retorna true, caso contrário retorna false.
     */
    public boolean atualizaUsuario(){
        try{
            //é criado uma sessao de acordo com seu banco de dados, caso for tester configure o arquivo hibernate.gfj.xml
            SessionFactory fabrica = new Configuration().configure("Hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            Transaction tx_part = sessao.beginTransaction();
            sessao.update(this);
            tx_part.commit();
            sessao.close();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    /**
     * Metodo responsável por validar o usuario no banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return boolean - Caso o usuario for valido retorna true, caso contrário retorna false.
     */
    public boolean validarUsuario(){
        try{
            //é criado uma sessao de acordo com seu banco de dados, caso for tester configure o arquivo hibernate.gfj.xml
            SessionFactory fabrica = new Configuration().configure("Hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            //aqui a sessao é iniciado sem nessecidade de ocorrer um Transaction tx_part = sessao.beginTransaction();
            sessao.getTransaction().begin();
            //aqui é onde é importado a primeira linha da tabela para o objeto usuario
            Usuario temp =(Usuario) sessao.createCriteria(Usuario.class).uniqueResult();
            sessao.close();//sessao finalizada
            return this.senha.equals(temp.getSenha()) && this.login.equals(temp.getLogin());
        }
        catch(Exception e){
            return false;
        }
    }
}
