/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.GerEmprestimos;
import LibraryScreens.ListarEmprestimos;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author death
 */
public class ListarEmprestimosController implements Initializable {

    @FXML
    private Button cancelBtn;

    @FXML
    private TableView<?> tableEmprestimo;

    @FXML
    void cancelBtnAction(ActionEvent event) {
        GerEmprestimos p = new GerEmprestimos();
        try {
            p.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(GerPessoasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void fecha() {
        ListarEmprestimos.getStage().close();
    }
}
