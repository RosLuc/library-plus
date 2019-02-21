/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.GerEmprestimos;
import LibraryScreens.RealizarEmp;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author death
 */
public class RealizarEmpController implements Initializable {

    @FXML
    private Button limparBtn;

    @FXML
    private TextField codInscTxt;

    @FXML
    private TableColumn<?, ?> nseqTb;

    @FXML
    private TableColumn<?, ?> nchamTb;

    @FXML
    private TextField emailTxt;

    @FXML
    private TableColumn<?, ?> tituloTb;

    @FXML
    private TableColumn<?, ?> npraTb;

    @FXML
    private TableColumn<?, ?> nchamTb1;

    @FXML
    private TableView<?> tablePes;

    @FXML
    private TextField nomeTxt;

    @FXML
    private TableView<?> tableMat;

    @FXML
    private TableColumn<?, ?> npraTb1;

    @FXML
    private Button realizarEmpBtn;

    @FXML
    private TableColumn<?, ?> tituloTb1;

    @FXML
    private Button backBtn;

    @FXML
    private Button pesquisarBtn;

    @FXML
    private TableColumn<?, ?> nestTb1;

    @FXML
    private TableColumn<?, ?> nestTb;

    @FXML
    private TableColumn<?, ?> nseqTb1;

    @FXML
    void backBtnAction(ActionEvent event) {
        returnBack();
    }

    @FXML
    void realizarEmpBtnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Método responsável por retornar para a tela anterior e fechar a tela
     * atual
     */
    public void returnBack() {
        GerEmprestimos p = new GerEmprestimos();
        try {
            p.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(RealizarEmpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }

    /**
     * Método responsável por fechar a tela atual
     */
    public void fecha() {
        RealizarEmp.getStage().close();
    }

}
