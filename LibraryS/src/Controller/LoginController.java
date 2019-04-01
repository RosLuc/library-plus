/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.Main;
import Usuario.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author death
 */
public class LoginController implements Initializable {

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

    @FXML
    private Label errorTxt;
    
    private static Parent telaRestSenha;
    
    private static Parent telaPrincipal;
    
    private static Parent telaCadastro;

    /**
     * Método responsável por realizar o login do Usuário
     *
     * @param event
     */
    @FXML
    void loginBtnKeyAction(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            // logar();
            login();
        }
    }

    /**
     * Método responsável por realizar o login do Usuário
     *
     * @param event
     */
    @FXML
    void loginBtnAction(ActionEvent event) {
        //logar();
        login();
    }

    /**
     * Método responsavel por abrir a tela de Esqueci Senha
     *
     * @param event
     */
    @FXML
    void forgotPassBtnAction(ActionEvent event) {
        resetSenha();
        /*EsqueciSenhaEmail es = new EsqueciSenhaEmail();
        try {
            es.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();*/
    }

    /**
     * Método responsável por abrir a tela de Cadastro de Usuário
     *
     * @param event
     */
    @FXML
    void signUpBtnAction(ActionEvent event) {
        cadastro();
    }

    /**
     * Método responsável por fechar a tela atual
     */
    public void fecha() {
        Main.fecharTela();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Usuario us = new Usuario();
        if (us.verificarUsuario() != null) {
            signupBtn.setVisible(false);
        }  
    }

    /**
     * login()
     *
     * Esse metodo é responsavel por verificar as entradas do usuario e
     * disponibilizar a entreada no sistema.
     *
     */
    private void login() {
        String login = userTxt.getText();
        String senha = passTxt.getText();
        Usuario us = new Usuario();
        us.setLogin(userTxt.getText());
        us.setSenha(passTxt.getText());

        userTxt.clear();
        passTxt.clear();

        if (login.length() == 0 && senha.length() == 0) {
            errorTxt.setStyle("-fx-opacity: 1");
            errorTxt.setText("CAMPOS OBRIGATÓRIOS NÃO INFORMADOS!");
            userTxt.setStyle("-fx-border-color: #ff2323");
            passTxt.setStyle("-fx-border-color: #ff2323");
        } else if (login.length() == 0 && senha.length() != 0) {
            errorTxt.setStyle("-fx-opacity: 1");
            errorTxt.setText("CAMPO USUÁRIO OBRIGATÓRIO!");
            userTxt.setStyle("-fx-border-color: #ff2323");
        } else if (login.length() != 0 && senha.length() == 0) {
            errorTxt.setStyle("-fx-opacity: 1");
            errorTxt.setText("CAMPO SENHA OBRIGATÓRIO!");
            passTxt.setStyle("-fx-border-color: #ff2323");
        } else {
            if (us.validarUsuario()) {
                principal();
            } else {
                errorTxt.setStyle("-fx-opacity: 1");
                errorTxt.setText("LOGIN OU SENHA INCORRETOS!");
                userTxt.setStyle("-fx-border-color: #ff2323");
                passTxt.setStyle("-fx-border-color: #ff2323");
            }
        }
    }
    
    private void principal() {
        try {
            telaPrincipal = FXMLLoader.load(getClass().getResource("/View/Principal.fxml"));
            Main.trocarTela(telaPrincipal);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void resetSenha() {
        try{
            telaRestSenha = FXMLLoader.load(getClass().getResource("/View/EsqueciSenhaEmail.fxml"));
            Main.trocarTela(telaRestSenha);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cadastro() {
        try{
            telaCadastro = FXMLLoader.load(getClass().getResource("/View/Cadastrar.fxml"));
            Main.trocarTela(telaCadastro);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
