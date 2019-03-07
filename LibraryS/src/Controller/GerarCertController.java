/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.EmtDoc;
import LibraryScreens.GerarCert;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author death
 */
public class GerarCertController implements Initializable {

    @FXML
    private Button gerCertBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TableView<?> tableCert;

    @FXML
    private TextField nomeDocTxt;

    @FXML
    private Button zerarBtn;

    @FXML
    void cancelBtnAction(ActionEvent event) {
        EmtDoc p = new EmtDoc();
        try {
            p.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(GerarCertController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }

    @FXML
    void gerCertBtnAction(ActionEvent event) {

    }

    @FXML
    void zerarBtnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void fecha() {
        GerarCert.getStage().close();
    }
}
