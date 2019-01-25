/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.EsqueciSenha;
import LibraryScreens.Login;
import Usuario.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author death
 */
public class EsqueciSenhaController {

    @FXML
    private PasswordField newPassTxt;
    @FXML
    private Button changePassBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField codVerTxt;

    /**
     * Método responsável por mudar a senha do Usuário
     *
     * @param event
     */
    @FXML
    void changePassBtnAction(ActionEvent event) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Usuario user = new Usuario();
        user = user.verificarUsuario();
        if(user.getCodRed().equals(codVerTxt.getText())){
            user.setSenha(newPassTxt.getText());
            user.setCodRed(null);
            if(user.atualizaUsuario() == true){
                returnLogin();
                confirmacao();
            }else erro();  
        }else erro();
    }

    /**
     * Método responsável por cancelar a ação atual e retornar para a tela de
     * Login
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
        EsqueciSenha.getStage().close();
    }

    /**
     * Método responsável por abrir a tela de Login e fechar a tela atual
     */
    public void returnLogin() {
        Login l = new Login();
        try {
            l.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(EsqueciSenhaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }
    
    public void erro(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Redefinir senha");
            alert.setTitle("Codigo invalido");
            alert.setContentText("Não foi possivel redefinir a nova senha. Verifique se as informações estão corretas, e tente novamente.");
    }
    
    public void confirmacao(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Redefinir senha");
            alert.setTitle("Senha redefinida");
            alert.setContentText("Senha alterada com sucesso!");
            alert.show();
    }

}
