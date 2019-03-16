/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classes.Material;
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
import Emprestimo.Emprestimo;
//import Pessoa.Pessoa;
import java.util.Date;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author death
 */
public class ListarEmprestimosController implements Initializable {

    @FXML
    private Button cancelBtn;

    @FXML
    private TableView<Emprestimo> tableEmprestimo;
    
    @FXML
    private TableColumn<Emprestimo, Integer> codinscTb;

    @FXML
    private TableColumn<Emprestimo, String> nomeTb;

    @FXML
    private TableColumn<Emprestimo, Set<Material>> tituloTb;

    @FXML
    private TableColumn<Emprestimo, String> chamadaTb;

    @FXML
    private TableColumn<Emprestimo, Date> dataEmpTb;

    @FXML
    private TableColumn<Emprestimo, Date> dataDevTb;

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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        codinscTb.setCellValueFactory(
            new PropertyValueFactory<>("codinsc"));
        nomeTb.setCellValueFactory(
            new PropertyValueFactory<>("nome"));
        tituloTb.setCellValueFactory(
            new PropertyValueFactory<>("Set<Material>"));
        dataEmpTb.setCellValueFactory(
            new PropertyValueFactory<>("dataemp"));
        dataDevTb.setCellValueFactory(
            new PropertyValueFactory<>("datadev"));
        tableEmprestimo.setItems(FXCollections.observableArrayList(new Emprestimo().ListaDeEmprestimo()));
    }

    public void fecha() {
        ListarEmprestimos.getStage().close();
    }
}
