/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.GerEmprestimos;
import LibraryScreens.Principal;
import LibraryScreens.RealizarEmp;
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
public class GerEmprestimosController {

    @FXML
    private Button consEmpBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Button empBtn;

    @FXML
    void consEmpBtnAction(ActionEvent event) {

    }

    @FXML
    void empBtnAction(ActionEvent event) {
        RealizarEmp cm = new RealizarEmp();
        try {
            cm.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(GerEmprestimosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }

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
            Logger.getLogger(GerEmprestimosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }

    /**
     * Método responsável por fechar a tela atual
     */
    public void fecha() {
        GerEmprestimos.getStage().close();
    }

}
