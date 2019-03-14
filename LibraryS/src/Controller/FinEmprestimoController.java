/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Emprestimo.Emprestimo;
import static Emprestimo.Emprestimo.buscarEmprestimoDePessoa;
import LibraryScreens.FinEmprestimo;
import LibraryScreens.GerEmprestimos;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author death
 */
public class FinEmprestimoController {

    @FXML
    private Button finEmpBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField numChamTxt;

    @FXML
    private TextField codInscTxt;

    @FXML
    void cancelBtnAction(ActionEvent event) {
        GerEmprestimos cm = new GerEmprestimos();
        try {
            cm.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(FinEmprestimoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void finEmpBtnAction(ActionEvent event) {
        List<Emprestimo> listEmp;
        int cod = Integer.parseInt(codInscTxt.getText());
        listEmp = buscarEmprestimoDePessoa(cod);
        listEmp.get(cod).getMateriais();
    }

    /**
     * Método responsável por fechar a tela atual
     */
    public void fecha() {
        FinEmprestimo.getStage().close();
    }

}
