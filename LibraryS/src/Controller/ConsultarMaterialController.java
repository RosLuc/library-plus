/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.ConsultarMaterial;
import LibraryScreens.GerAcervo;
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
public class ConsultarMaterialController implements Initializable {

    @FXML
    private Button backBtn;

    @FXML
    private TableView<?> livroTbl;

    /**
     * Método responssável por cancelar a ação atual e retornar para a tela
     * antetior
     *
     * @param event
     */
    @FXML
    void backBtnAction(ActionEvent event) {
        GerAcervo g = new GerAcervo();
        try {
            g.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(ConsultarMaterialController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método responsável por listar o Material
     *
     * @param event
     */
    @FXML
    void livroTblAction(ActionEvent event) {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Método responsável por fechar a tela atual
     */
    public void fecha() {
        ConsultarMaterial.getStage().close();
    }

}
