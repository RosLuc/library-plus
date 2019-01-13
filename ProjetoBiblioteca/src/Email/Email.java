package Email;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
/**
 * Classe Email tem funcionalidade de realizar conexões SMTP e enviar mensagens.
 * @author lucas
 * @version 1.0
 */
public class Email {
    /**
     * Metodo responsavel por fazer conexão com os servidores da Microsoft.
     * @return as configurações do servidor da Microsoft. 
     */
    private static Properties smtpMicrosoft() {
        Properties props = new Properties();
            /** Parâmetros de conexão com servidor Hotmail */
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.live.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        return props;
    }
    /**
     * Metodo responsavel por fazer conexão com os servidores da Google.
     * @return as configurações do servidor da Google. 
     */
    private static Properties smtpGmail() {
        Properties props = new Properties();
        /** Parâmetros de conexão com servidor Gmail.com */
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");
        return props;
        
    }
    /**
     * Metodo responsavel por identificar o servidor do remetente.
     * @param  email umemaila string que indica o email do remetente.
     * @return as configurações do servidor do email remetente. 
     */
    public static Properties conexaoSMTP(String email) {
        int i;
        Properties props = new Properties();
        for(i = 0; i < email.length(); i++ ) if(email.charAt(i) == '@') break;
        String smtpServer = email.substring(i+1);
        if("gmail.com".equals(smtpServer)){
            props = smtpGmail();
        }
        else if("hotmail.com".equals(smtpServer)){
            props = smtpMicrosoft();
        }
        return props;
    }
    
    /**
     * Metodo responsavel por identificar o servidor do remetente.
     * @param props configurações do servidor remetente.
     * @param email uma string que indica o email do remetente.
     * @param senha uma string que indica a senha do remetente. 
     * @return uma sessão estabelecida com o servidor. 
     */
    public static Session conectarConta(Properties props, String email, String senha) {
        Session session;
        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(email, senha);
                    }
                });
        /** Ativa Debug para sessão */
        session.setDebug(true);
        return session;
    }
    
    /**
     * Metodo responsavel por identificar o servidor do remetente.
     * @param remetente uma string que indica o email do remetente.
     * @param destinatario uma string que indica o email do destinatario.
     * @param titulo uma string que indica o titulo da mensagem.
     * @param mensagem uma string que indica a mensagem a ser enviada.
     * @param session sessão estabelecida com o servidor.
     * @return as configurações do servidor do email remetente. 
     */
    public static boolean enviarMensagem(String remetente, String destinatario, String titulo,
            String mensagem, Session session) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remetente)); //Remetente
 
            Address[] toUser = InternetAddress //Destinatário(s)
                        .parse(destinatario);  
 
            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(titulo);//Assunto
            message.setText(mensagem);
            /**Método para enviar a mensagem criada*/
            Transport.send(message);
 
            System.out.println("Enviado com sucesso!");
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}