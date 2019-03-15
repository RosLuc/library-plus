/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.AtualizarMaterial;
import LibraryScreens.GerAcervo;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author death
 */
public class AtualizarMaterialController implements Initializable {

    @FXML
    private Button atPesBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button confMaterial;

    @FXML
    private TextField tituleTxt;

    @FXML
    private TextField volTxt;

    @FXML
    private TextField exemplarTxt;

    @FXML
    private TextField authorTxt;

    @FXML
    private TextField editorTxt;

    @FXML
    private TextField cduTxt;

    @FXML
    private TextField aquisicaoTxt;

    @FXML
    private TextField anoPublicTxt;

    @FXML
    private TextField cddTxt;

    @FXML
    private TextField localPublicTxt;

    @FXML
    private TextField obsTxt;

    @FXML
    private TextField estanteTxt;

    @FXML
    private TextField prateleiraTxt;

    @FXML
    private TextField chamadaTxt;

    @FXML
    private CheckBox multBox;

    @FXML
    private CheckBox livroBox;

    @FXML
    private TextField Txt;

    @FXML
    void atPesBtnAction(ActionEvent event) {

    }

    @FXML
    void cancelBtnAction(ActionEvent event) {
        GerAcervo c = new GerAcervo();
        try {
            c.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(GerAcervoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void confMaterialBtnAction(ActionEvent event) {

    }

    @FXML
    void excluirPesKeyAction(KeyEvent event) {

    }

    @FXML
    void livroSelectBox(ActionEvent event) {

    }

    @FXML
    void multSelectBox(ActionEvent event) {

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
        AtualizarMaterial.getStage().close();
    }

}
