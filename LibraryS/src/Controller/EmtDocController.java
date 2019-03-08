/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.EmtDoc;
import LibraryScreens.GerarCert;
import LibraryScreens.GerarRel;
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
public class EmtDocController {

    @FXML
    private Button gerRelBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button gerCertBtn;

    @FXML
    void gerCertBtnAction(ActionEvent event) {
        GerarCert cm = new GerarCert();
        try {
            cm.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(EmtDocController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }

    @FXML
    void gerRelBtnAction(ActionEvent event) {
        GerarRel p = new GerarRel();
        try {
            p.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(GerPessoasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void cancelBtnAction(ActionEvent event) {
        Principal p = new Principal();
        try {
            p.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(GerPessoasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }

    public void fecha() {
        EmtDoc.getStage().close();
    }

}
