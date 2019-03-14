/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.CadPessoa;
import LibraryScreens.ConsultarPessoas;
import LibraryScreens.ExcluirPessoa;
import LibraryScreens.GerPessoas;
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
public class GerPessoasController {

    @FXML
    private Button cadPesBtn;

    @FXML
    private Button consPesBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Button excluirPesBtn;

    /**
     * Método responsável por abrir a tela de Consultar Pessoas
     *
     * @param event
     */
    @FXML
    void consPesBtnAction(ActionEvent event) {
        ConsultarPessoas c = new ConsultarPessoas();
        try {
            c.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(GerPessoasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método responsável por abrir a tela de Cadastrar Pessoa
     *
     * @param event
     */
    @FXML
    void cadPesBtnAction(ActionEvent event) {
        CadPessoa c = new CadPessoa();
        try {
            c.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(GerPessoasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método responssável por cancelar a ação atual e retornar para a tela
     * antetior
     *
     * @param event
     */
    @FXML
    void backBtnAction(ActionEvent event) {
        returnPrincipal();
    }

    /**
     * Método responsável por retornar para a tela anterior e fechar a tela
     * atual
     */
    public void returnPrincipal() {
        Principal p = new Principal();
        try {
            p.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(GerPessoasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }

    @FXML
    void excluirPesBtnAction(ActionEvent event) {
        ExcluirPessoa p = new ExcluirPessoa();
        try {
            p.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(GerPessoasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }

    /**
     * Método responsável por fechar a tela atual
     */
    public void fecha() {
        GerPessoas.getStage().close();
    }

}
