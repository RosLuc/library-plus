/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.ConsultarMaterial;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author death
 */
public class ConsultarMaterialController implements Initializable {

    @FXML
    private Button limparBtn;

    @FXML
    private TableColumn<?, ?> nseqTb;

    @FXML
    private TableColumn<?, ?> nchamTb;

    @FXML
    private TextField tituloTxt;

    @FXML
    private TableColumn<?, ?> tituloTb;

    @FXML
    private TableColumn<?, ?> autorTb;

    @FXML
    private TableColumn<?, ?> npraTb;

    @FXML
    private TextField nSeqTxt;

    @FXML
    private TextField localTxt;

    @FXML
    private TextField editTxt;

    @FXML
    private TableColumn<?, ?> volTb;

    @FXML
    private TableColumn<?, ?> editTb;

    @FXML
    private TextField nChamTxt;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<?, ?> statusTb;

    @FXML
    private Button pesquisarBtn;

    @FXML
    private TableColumn<?, ?> anoTb;

    @FXML
    private TableColumn<?, ?> exemplarTb;

    @FXML
    private TableColumn<?, ?> nestTb;

    @FXML
    private TextField autorTx;

    @FXML
    private TableView<?> table;

    @FXML
    void backBtnAction(ActionEvent event) {

    }

    @FXML
    void pesquisarBtnAction(ActionEvent event) {

    }

    @FXML
    void limparBtnAction(ActionEvent event) {

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
