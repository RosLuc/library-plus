/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.Main;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author death
 */
public class PrincipalController {

    @FXML
    private Button gerEmpBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button emtDocBtn;

    @FXML
    private Button gerPesBtn;

    @FXML
    private Button gerAcBtn;
    
    private static Parent telaEmprestimos;
    
    private static Parent telaAcervo;
    
    private static Parent telaPessoa;
    
    private static Parent telaDocumentos;
    
    private static Parent telaLogin;

    /**
     * Método responsável por abrir a tela de Emitir Documentos
     *
     * @param event
     */
    @FXML
    void emtDocBtnAction(ActionEvent event) {
        try {
            telaDocumentos = FXMLLoader.load(getClass().getResource("/View/EmtDoc.fxml"));
            Main.trocarTela(telaDocumentos);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método responsável por abrir a tela de Gerenciar Pessoas
     *
     * @param event
     */
    @FXML
    void gerPesBtnAction(ActionEvent event) {
        try {
            telaPessoa = FXMLLoader.load(getClass().getResource("/View/GerPessoas.fxml"));
            Main.trocarTela(telaPessoa);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método responsável por abrir a tela de Gerenciar Acervo
     *
     * @param event
     */
    @FXML
    void gerAcBtnAction(ActionEvent event) {
        try {
            telaAcervo = FXMLLoader.load(getClass().getResource("/View/GerAcervo.fxml"));
            Main.trocarTela(telaAcervo);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método responsável abrir a tela de Gerenciar Empréstimos
     *
     * @param event
     */
    @FXML
    void gerEmpBtnAction(ActionEvent event) {
        try {
            telaEmprestimos = FXMLLoader.load(getClass().getResource("/View/GerEmprestimos.fxml"));
            Main.trocarTela(telaEmprestimos);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método responsável por retornar para a tela do Login
     *
     * @param event
     */
    @FXML
    void cancelBtnAction(ActionEvent event) {
        returnLogin();
    }

    /**
     * Método responsável por retornar para a tela anterior e fechar a tela
     * atual
     */
    public void returnLogin() {
        try {
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

}
