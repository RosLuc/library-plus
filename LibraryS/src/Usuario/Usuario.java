package Usuario;

/**
 *
 * @author maria adriana
 */
import java.io.UnsupportedEncodingException;
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
    private String codRed;

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCodRed() {
        return codRed;
    }

    public void setCodRed(String codRed) {
        this.codRed = codRed;
    }

    public Usuario(int userCode, String nome, String senha, String email, String login, String cpf, String codRed) {
        this.userCode = userCode;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.login = login;
        this.codRed = codRed;
    }

    public Usuario() {
    }

    /**
     * Metodo responsável por salvar o objeto pessoa no banco de dados. A
     * operação é realizada utilizando hibernate.
     *
     * @return boolean - Caso a operação for realizada com sucesso retorna true,
     * caso contrário retorna false.
     */
    public boolean salvarUsuario() {
        try {
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            Transaction tx_part = sessao.beginTransaction();
            sessao.save(this);
            tx_part.commit();
            sessao.close();
            fabrica.close();
            System.out.println("Usuario salvo");
            return true;
        } catch (HibernateException e) {
            System.out.println("ERRO ao salvar");
            return false;
        }
    }

    /**
     * Metodo responsável por exluir o objeto usuario do banco de dados. A
     * operação é realizada utilizando hibernate.
     *
     * @return boolean - Caso a operação for realizada com sucesso retorna true,
     * caso contrário retorna false.
     */
    public boolean excluirUsuario() {
        try {
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            Transaction tx_part = sessao.beginTransaction();
            sessao.delete(this);
            tx_part.commit();
            sessao.close();
            fabrica.close();
            System.out.println("usuario excluido");
            return true;
        } catch (HibernateException e) {
            System.out.println("ERRO ao excluir");
            return false;
        }
    }

    /**
     * Metodo responsável por atualizar o objeto usuario no banco de dados. A
     * operação é realizada utilizando hibernate.
     *
     * @return boolean - Caso a operação for realizada com sucesso retorna true,
     * caso contrário retorna false.
     */
    public boolean atualizaUsuario() {
        try {
            //é criado uma sessao de acordo com seu banco de dados, caso for tester configure o arquivo hibernate.gfj.xml
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            Transaction tx_part = sessao.beginTransaction();
            sessao.update(this);
            tx_part.commit();
            sessao.close();
            fabrica.close();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    /**
     * Metodo responsável por validar o usuario no banco de dados. A operação é
     * realizada utilizando hibernate.
     *
     * @return boolean - Caso o usuario for valido retorna true, caso contrário
     * retorna false.
     */
    public boolean validarUsuario() {
        try {
            //é criado uma sessao de acordo com seu banco de dados, caso for tester configure o arquivo hibernate.gfj.xml
            SessionFactory fabrica = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
            Session sessao = fabrica.openSession();
            //aqui a sessao é iniciado sem nessecidade de ocorrer um Transaction tx_part = sessao.beginTransaction();
            sessao.getTransaction().begin();
            //aqui é onde é importado a primeira linha da tabela para o objeto usuario
            Usuario temp = (Usuario) sessao.createCriteria(Usuario.class).uniqueResult();
            sessao.close();//sessao finalizada
            fabrica.close();
            if (temp == null) {
                return false;
            }
            return (this.senha.equals(temp.getSenha()) && this.login.equals(temp.getLogin()));
        } catch (HibernateException e) {
            return false;
        }
    }

    /**
     *
     * @return
     */
    public Usuario verificarUsuario() {
        try {
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
        } catch (HibernateException e) {
            return null;
        }
    }

    /**
     * Metodo responsável por gerar números aleatorios e converter o tipo Int
     * para String A operação é realizada utilizando um gerador de numeros da
     * biblioteca 'Random'
     *
     * @return String - o codigo gerado para ser a nova senha
     */
    public String retorna_g() {
        Random gerador = new Random();
        int mensagem;
        do {
            mensagem = gerador.nextInt();
        } while (mensagem <= 0);
        String strin = String.valueOf(mensagem);
        return strin;
    }

    /**
     * Metodo responsável por obter a nova senha e envia-la por e-mail A
     * operação é realizada utilizando a chamada do gerador de numero e conexão
     * com o e-mail remetente
     *
     * @return String - a nova senha
     * @throws java.io.UnsupportedEncodingException
     * @throws java.security.NoSuchAlgorithmException
     */
    public String recupera() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String str = retorna_g();
        String remetente = "libraryalory@gmail.com";
        String senh = "libraryalory12345";
        Usuario u = verificarUsuario();
        Email Email = new Email();
        if (Email.enviarMensagem(remetente, u.getEmail(), "Recuperar senha do sistema", str, Email.conectarConta(Email.conexaoSMTP(remetente), remetente, senh))) {
            System.out.println("E-mail enviado!");
            return str;
        } else {
            System.out.println("Ocorreu erro, verifique as informações");
            return null;
        }

    }

}
