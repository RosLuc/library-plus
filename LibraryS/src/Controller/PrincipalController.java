/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.EmtDoc;
import LibraryScreens.GerAcervo;
import LibraryScreens.GerEmprestimos;
import LibraryScreens.GerPessoas;
import LibraryScreens.Login;
import LibraryScreens.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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

    /**
     * Método responsável por abrir a tela de Emitir Documentos
     *
     * @param event
     */
    @FXML
    void emtDocBtnAction(ActionEvent event) {
        EmtDoc p = new EmtDoc();
        try {
            p.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }

    /**
     * Método responsável por abrir a tela de Gerenciar Pessoas
     *
     * @param event
     */
    @FXML
    void gerPesBtnAction(ActionEvent event) {
        GerPessoas ac = new GerPessoas();
        try {
            ac.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }

    /**
     * Método responsável por abrir a tela de Gerenciar Acervo
     *
     * @param event
     */
    @FXML
    void gerAcBtnAction(ActionEvent event) {
        GerAcervo ac = new GerAcervo();
        try {
            ac.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }

    /**
     * Método responsável abrir a tela de Gerenciar Empréstimos
     *
     * @param event
     */
    @FXML
    void gerEmpBtnAction(ActionEvent event) {
        GerEmprestimos cm = new GerEmprestimos();
        try {
            cm.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
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
        Login l = new Login();
        try {
            l.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }

    /**
     * Método responsável por fechar a tela atual
     */
    public void fecha() {
        Principal.getStage().close();
    }

}
