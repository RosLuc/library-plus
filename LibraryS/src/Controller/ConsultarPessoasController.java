/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.ConsultarPessoas;
import LibraryScreens.GerPessoas;
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
public class ConsultarPessoasController implements Initializable {

    @FXML
    private TableView<?> pesTbl;

    @FXML
    private Button backBtn;

    /**
     * Método responssável por cancelar a ação atual e retornar para a tela
     * antetior
     *
     * @param event
     */
    @FXML
    void backBtnAction(ActionEvent event) {
        GerPessoas g = new GerPessoas();
        try {
            g.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(ConsultarPessoasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método responsável por listar Pessoas
     *
     * @param event
     */
    @FXML
    void pesTblAction(ActionEvent event) {

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
        ConsultarPessoas.getStage().close();
    }

}
