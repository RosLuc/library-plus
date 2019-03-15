/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.AtualizarPessoa;
import LibraryScreens.GerPessoas;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author death
 */
public class AtualizarPessoaController implements Initializable {

    @FXML
    private Button atPesBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button confPessoa;

    @FXML
    private TextField nomeTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField turnoTxt;

    @FXML
    private TextField turmaTxt;

    @FXML
    private TextField serieTxt;

    @FXML
    private TextField telTxt;

    @FXML
    private TextField cepTxt;

    @FXML
    private TextField estadoTxt;

    @FXML
    private TextField cidadeTxt;

    @FXML
    private TextField bairroTxt;

    @FXML
    private TextField enderecoTxt;

    @FXML
    private TextField numeroTxt;

    @FXML
    private Label serieLabel;

    @FXML
    private Label turmaLabel;

    @FXML
    private Label turnoLabel;

    @FXML
    private TextField codInscTxt;

    @FXML
    void atPesBtnAction(ActionEvent event) {

    }

    @FXML
    void cancelBtnAction(ActionEvent event) {
        GerPessoas c = new GerPessoas();
        try {
            c.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(CadPessoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void confPessoaBtnAction(ActionEvent event) {

    }

    @FXML
    void excluirPesKeyAction(KeyEvent event) {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void fecha() {
        AtualizarPessoa.getStage().close();
    }

}
