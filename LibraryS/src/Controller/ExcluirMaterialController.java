/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.ExcluirMaterial;
import LibraryScreens.GerAcervo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author death
 */
public class ExcluirMaterialController {

    @FXML
    private Button excluirMatBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField numChamTxt;

    @FXML
    void cancelBtnAction(ActionEvent event) {
        returnScreen();
    }

    @FXML
    void excluirMatBtnAction(ActionEvent event) {

    }

    @FXML
    void excluirMatKeyAction(KeyEvent event) {

    }

    public void fecha() {
        ExcluirMaterial.getStage().close();
    }

    public void returnScreen() {
        GerAcervo g = new GerAcervo();
        try {
            g.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(ExcluirPessoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
