package Usuario;

/**
 *
 * @author maria adriana
 */ 
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Usuario {
    private int userCode;
    private String nome;
    private String senha;
    private String email;
    private String login;
    private String cpf;
   /**
     * @return the usercode
     */
    public int getUserCode() {
        return userCode;
    }
    /**
     * @param userCode
     */
    public void setUserCode(int userCode) {
        this.userCode = userCode;
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
    public Usuario(int userCode, String nome, String senha, String email, String login, String cpf) {
        this.userCode = userCode;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.login = login;
        this.cpf = cpf;
    }
    public Usuario(){
        
    }
    /**
     * Metodo responsável por salvar o objeto pessoa no banco de dados.
     * A operação é realizada utilizando hibernate.
     * @return boolean - Caso a operação for realizada com sucesso retorna true, caso contrário retorna false.
     */
    public boolean salvarUsuario(){
        try{
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            Transaction tx_part = sessao.beginTransaction();
            sessao.save(this);
            tx_part.commit();
            sessao.close();
            fabrica.close();
            System.out.println("Usuario salvo");
            return true;  
        }
        catch(HibernateException e){
            System.out.println("ERRO ao salvar" );
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
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            Transaction tx_part = sessao.beginTransaction();
            sessao.delete(this);
            tx_part.commit();
            sessao.close();
            fabrica.close();
            System.out.println("usuario excluido");
            return true;
        }
        catch(HibernateException e){
            System.out.println("ERRO ao excluir");
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
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            Transaction tx_part = sessao.beginTransaction();
            sessao.update(this);
            tx_part.commit();
            sessao.close();
            fabrica.close();            
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
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            //aqui a sessao é iniciado sem nessecidade de ocorrer um Transaction tx_part = sessao.beginTransaction();
            sessao.getTransaction().begin();
            //aqui é onde é importado a primeira linha da tabela para o objeto usuario
            Usuario temp = (Usuario) sessao.createCriteria(Usuario.class).uniqueResult();
            sessao.close();//sessao finalizada
            fabrica.close();
            return (this.senha.equals(temp.getSenha()) && this.login.equals(temp.getLogin()));      
        }
        catch(Exception e){
            return false;
        }
    }
    /**
     * Metodo responsável por verificar se o e-mail do usuario no banco de dados é valido.
     * A operação é realizada utilizando hibernate.
     * @return boolean - Caso o e-mail for valido retorna true, caso contrário retorna false.
     */
    public boolean verificarEmail(){
        try{
            //é criado uma sessao de acordo com seu banco de dados, caso for tester configure o arquivo hibernate.gfj.xml
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            //aqui a sessao é iniciado sem nessecidade de ocorrer um Transaction tx_part = sessao.beginTransaction();
            sessao.getTransaction().begin();
            //aqui é onde é importado a primeira linha da tabela para o objeto usuario
            Usuario temp = (Usuario) sessao.createCriteria(Usuario.class).uniqueResult();
            sessao.close();//sessao finalizada
            fabrica.close();
            return (this.email.equals(temp.getEmail()));           
        }
        catch(Exception e){
            return false;
        }
    }
      public Usuario verificarUsuario(){
        try{
            //é criado uma sessao de acordo com seu banco de dados, caso for tester configure o arquivo hibernate.gfj.xml
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            //aqui a sessao é iniciado sem nessecidade de ocorrer um Transaction tx_part = sessao.beginTransaction();
            sessao.getTransaction().begin();
            //aqui é onde é importado a primeira linha da tabela para o objeto usuario
            Usuario temp = (Usuario) sessao.createCriteria(Usuario.class).uniqueResult();
            sessao.close();//sessao finalizada
            fabrica.close();
            return temp;          
        }
        catch(Exception e){
            return null;
        }
    }
    /**
     * Metodo responsável por realizar a criptrografia da senha 
     * @param senha a senha do usuario a ser criptografia
     * @return string a senha criptografada
     * @throws java.io.UnsupportedEncodingException
     * @throws java.security.NoSuchAlgorithmException
     */
     public String Cripto(String senha) throws UnsupportedEncodingException, NoSuchAlgorithmException{
	//Criptografa a String passada por parâmetro
	MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));                 
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        String senhahex = hexString.toString();
	return senhahex ;
    }
    /**
     * Metodo responsável por gerar números aleatorios e converter o tipo Int para String
     * A operação é realizada utilizando um gerador de numeros da biblioteca 'Random'
     * @return String - o codigo gerado para ser a nova senha 
     */
    public String retorna_g(){
        Random gerador = new Random();
        int mensagem = gerador.nextInt();
        String strin = String.valueOf(mensagem);
        return strin;
    }
    /**
     * Metodo responsável por recuperar e atualizar a senha no banco de dados
     * @return boolean - caso a atualização da nova senha seja concluida com sucesso
     * @throws java.io.UnsupportedEncodingException
     * @throws java.security.NoSuchAlgorithmException
     */
    public boolean recuperaatualizasenha() throws UnsupportedEncodingException, NoSuchAlgorithmException{
        Usuario n = verificarUsuario();
        String res =  n.recupera();
        n.setSenha(res);
        boolean r = n.atualizaUsuario();
        return (r); 
    }
    /**
     * Metodo responsável por obter a nova senha e envia-la por e-mail
     * A operação é realizada utilizando a chamada do gerador de numero e conexão com o e-mail remetente
     * @return String - a nova senha
     * @throws java.io.UnsupportedEncodingException
     * @throws java.security.NoSuchAlgorithmException
     */
    public String recupera() throws UnsupportedEncodingException, NoSuchAlgorithmException{
            String str = retorna_g();
            String cri;
            String remetente = "libraryalory@gmail.com";
            String senh = "libraryalory12345";
            Usuario u = verificarUsuario();
            Email Email = new Email();
            if(Email.enviarMensagem(remetente, u.getEmail(), "Recuperar senha do sistema", str, Email.conectarConta(Email.conexaoSMTP(remetente), remetente, senh))){
               System.out.println("E-mail enviado!");
               return str;
            }else{
                System.out.println("Ocorreu erro, verifique as informações");
                return null;
            }
           
    }
       /**
     * Metodo responsável por redefinir atualizar a senha no banco de dados, apos ser logado
     * @param ns a nova senha a ser definida
     * @return boolean - caso a atualização da nova senha seja concluida com sucesso
     * @throws java.io.UnsupportedEncodingException
     * @throws java.security.NoSuchAlgorithmException
     */
    public boolean redefinirSenha( String ns) throws UnsupportedEncodingException, NoSuchAlgorithmException{
        ns = Cripto(ns);
        Usuario n = verificarUsuario();
        n.setSenha(ns);
        boolean r = n.atualizaUsuario();
        return (r);     
    }  
   
}
 

    