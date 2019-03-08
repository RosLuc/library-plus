/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.EmtDoc;
import LibraryScreens.GerarRel;
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
public class GerarRelController {

    @FXML
    private Button relLivroBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button relMultBtn;

    @FXML
    void cancelBtnAction(ActionEvent event) {
        EmtDoc p = new EmtDoc();
        try {
            p.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(GerarCertController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void relLivroBtnAction(ActionEvent event) {

    }

    @FXML
    void relMultBtnAction(ActionEvent event) {

    }

    public void fecha() {
        GerarRel.getStage().close();
    }

}
