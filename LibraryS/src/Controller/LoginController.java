/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.Cadastrar;
import LibraryScreens.EsqueciSenhaEmail;
import LibraryScreens.Login;
import LibraryScreens.Principal;
import Usuario.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author death
 */
public class LoginController {

    @FXML
    private Button signupBtn;
    @FXML
    private Button loginBtn;
    @FXML
    private PasswordField passTxt;
    @FXML
    private Button forgotpassBtn;
    @FXML
    private TextField userTxt;

    /**
     * Método responsável por realizar o login do Usuário
     *
     * @param event
     */
    @FXML
    void loginBtnAction(ActionEvent event) {
        Usuario us = new Usuario();
        us.setLogin(userTxt.getText());
        us.setSenha(passTxt.getText());
        if (us.validarUsuario()) {
            Principal p = new Principal();
            try {
                p.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            fecha();
        } else {
            erro();
        }
    }

    /**
     * Método responsavel por abrir a tela de Esqueci Senha
     *
     * @param event
     */
    @FXML
    void forgotPassBtnAction(ActionEvent event) {
        EsqueciSenhaEmail es = new EsqueciSenhaEmail();
        try {
            es.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }

    /**
     * Método responsável por abrir a tela de Cadastro de Usuário
     *
     * @param event
     */
    @FXML
    void signUpBtnAction(ActionEvent event) {
        Cadastrar c = new Cadastrar();
        try {
            c.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }

    /**
     * Método responsável por fechar a tela atual
     */
    public void fecha() {
        Login.getStage().close();
    }
    
    public void erro() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText("ERRO");
        alert.setTitle("Erro");
        alert.setContentText("Login ou Senha Incorretos");
        alert.show();
    }
}
