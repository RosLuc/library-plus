/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.Cadastrar;
import LibraryScreens.Login;
import Usuario.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author death
 */
public class CadastrarController {

    @FXML
    private Button cancelBtn;
    @FXML
    private PasswordField passTxt;
    @FXML
    private TextField telTxt;
    @FXML
    private TextField usernameTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField emailTxt;
    @FXML
    private PasswordField confirmPassTxt;
    @FXML
    private Button signInBtn;
    @FXML
    private TextField cpfTxt;
    
    private static final String EMAIL_PATTERN = 
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
    
    /**
     * Método reponsável por identificar um KeyEvent na tecla enter.
     * @param e 
     */
    @FXML
    void signUpBtnKeyAction(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            cadastrar();
        }
    }

    /**
     * Método responsável por identificar um ActionEvent no butão de cadastro.
     *
     * @param event
     */
    @FXML
    void signInBtnAction(ActionEvent event) {
        cadastrar();
    }

    /**
     * Método responssável por cancelar a ação atual e retornar para a tela
     * antetior.
     *
     * @param event
     */
    @FXML
    void cancelBtnAction(ActionEvent event) {
        returnLogin();
    }

    /**
     * Método responsável por fechar a tela atual
     */
    public void fecha() {
        Cadastrar.getStage().close();
    }

    /**
     * Método responsável por retornar para a tela anterior e fechar a tela
     * atual
     */
    public void returnLogin() {
        Login l = new Login();
        try {
            l.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CadastrarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }
    
    /**
     * Método responsável por validar o email.
     * @param email String email a ser verificado.
     * @return Boolean - true caso seja valido e false caso não seja.
     */
    public static boolean validarEmail(String email){
        Matcher matcher = PATTERN.matcher(email);
        return matcher.matches();
    }
    
    /**
     * Método responsável pelo cadastro do usuário no banco de dados.
     */
    public void cadastrar() {
        if(!(cpfTxt.getText().trim().equals("") || emailTxt.getText().trim().equals("")
                || usernameTxt.getText().trim().equals("") || nameTxt.getText().trim().equals("")
                || passTxt.getText().trim().equals(""))){
            
            if(validarEmail(emailTxt.getText())){
                
                if(passTxt.getText().equals(confirmPassTxt.getText())) {
                    Usuario user = new Usuario();
                    user.setCpf(cpfTxt.getText());
                    user.setEmail(emailTxt.getText());
                    user.setLogin(usernameTxt.getText());
                    user.setNome(nameTxt.getText());
                    user.setSenha(passTxt.getText());
                    user.setUserCode(11111);
            
                    if (!user.salvarUsuario()) {
                        error("Erro! Verifique se as informações informadas estão corretas e tente novamente.");
                    } else {
                        returnLogin();
                        comfirmacao();
                    }
                } else {
                    error("Erro! Confirmação de senha inválida.");
                }
            } else {
                error("Erro! Email inválido");
            }  
        }else {
            error("Erro! Campos em branco.");
        }
    }
    
    /**
     * Exibe um alerta de erro.
     * @param erro Mensagem a ser exibida no alerta.
     */
    static private void error(String erro){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("CADASTRO");
        alert.setTitle("Novo Cadastro");
        alert.setContentText(erro);
        alert.show();
    }
    
    /**
     * Exibe um alerta de comfirmacao.
     */
    static private void comfirmacao() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("CADASTRO");
        alert.setTitle("Novo Cadastro");
        alert.setContentText("Cadastro realizado com sucesso");
        alert.show();
    }

}
