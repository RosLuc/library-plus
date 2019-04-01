/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.EsqueciSenha;
import LibraryScreens.EsqueciSenhaEmail;
import LibraryScreens.Login;
import Main.Main;
import Usuario.Usuario;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author death
 */
public class EsqueciSenhaEmailController {

    @FXML
    private Button cancelBtn;
    @FXML
    private Button confirmBtn;
    @FXML
    private TextField emailTxt;
    
    private static Parent telaLogin;
    
    private static Parent telaEsqueciSenha;

    /**
     * Método responsável por enviar um código de verificação para o email do
     * Usuário
     *
     * @param e
     */
    @FXML
    void confirmKeyAction(KeyEvent e) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (e.getCode() == KeyCode.ENTER) {
            emailEsqSenha();
        }
    }

    /**
     * Método responsável por enviar um código de verificação para o email do
     * Usuário
     *
     * @param event
     */
    @FXML
    void confirmBtnAction(ActionEvent event) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        emailEsqSenha();
    }

    /**
     * Método responsável por retornar para a tela de Login e fechar a tela
     * atual
     */
    @FXML
    void cancelBtnAction(ActionEvent event) {
        try{
            telaLogin = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
            Main.trocarTela(telaLogin);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método responsável por fechar a tela atual
     */
    public void fecha() {
        Main.fecharTela();
    }

    /**
     * Método responsável por abrir a tela de Esqueci Senha e fechar a tela
     * atual
     */
    public void esqSenha() {
        try{
            telaEsqueciSenha = FXMLLoader.load(getClass().getResource("/View/EsqueciSenha.fxml"));
            Main.trocarTela(telaEsqueciSenha);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método responsável por enviar o email com o código de recuperação
     * @throws java.io.UnsupportedEncodingException
     * @throws java.security.NoSuchAlgorithmException
     */
    public void emailEsqSenha() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Usuario us = new Usuario();
        us = us.verificarUsuario();
        
        //verificar email
        if(us.getEmail().equals(emailTxt.getText())){
            
            String cod;
            if((cod = us.recupera()) != null){
                us.setCodRed(cod);
                confirmacao();
                if(us.atualizaUsuario() == true){
                    esqSenha();
                }else erro();
            }else erro();
        }else erro();
    }
    
    /**
     * Método responsável por exibir um alerta de erro.
     */
    public void erro(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Email");
            alert.setTitle("Email invalido");
            alert.setContentText("Não foi possivel redefinir a nova senha. Verifique se as informações estão corretas, e tente novamente.");
    }
    
    /**
     * Método responsável por exibir um alerta de confirmação.
     */
    public void confirmacao(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Email");
            alert.setTitle("Email verificado");
            alert.setContentText("Codigo enviado para o seu email.");
            alert.show();
    }

}
