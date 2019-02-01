/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.EsqueciSenha;
import LibraryScreens.EsqueciSenhaEmail;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    /**
     * Método responsável por enviar um código de verificação para o email do
     * Usuário
     *
     * @param event
     */
    @FXML
    void confirmBtnAction(ActionEvent event) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Usuario novo = new Usuario();
        novo.setEmail(emailTxt.getText());
                
        if(!novo.verificarEmail()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("EMAIL INVALIDO");
            alert.setTitle("Código de Verificação"); 
            alert.setContentText("Error");
            alert.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("EMAIL VALIDO");
            alert.setTitle("Código de Verificação");
            String res = novo.recupera();
            if(res == null){
                alert.setContentText("ERROR");
                alert.setContentText("Não foi possivel recuperar sua nova senha. Verifique se as informações estão corretas, e tente novamente");
                alert.show();
            }else{
               
                novo.verificarUsuario();
                novo.setSenha(res);
                novo.atualizaUsuario();
  
            }
        }
        esqSenha();
    }

    /**
     * Método responsável por retornar para a tela de Login e fechar a tela
     * atual
     */
    @FXML
    void cancelBtnAction(ActionEvent event) {
        Login l = new Login();
        try {
            l.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(EsqueciSenhaEmailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }

    /**
     * Método responsável por fechar a tela atual
     */
    public void fecha() {
        EsqueciSenhaEmail.getStage().close();
    }

    /**
     * Método responsável por abrir a tela de Esqueci Senha e fechar a tela
     * atual
     */
    public void esqSenha() {
        EsqueciSenha l = new EsqueciSenha();
        try {
            l.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(EsqueciSenhaEmailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }

}
