/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Emprestimo.TableEmprestimo;
import LibraryScreens.GerEmprestimos;
import LibraryScreens.ListarEmprestimos;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<TableEmprestimo, Integer> nChamadaColumn;

    @FXML
    private TableColumn<TableEmprestimo, String> dataEmpColumn;

    @FXML
    private TableColumn<TableEmprestimo, String> statusColumn;

    @FXML
    private TableColumn<TableEmprestimo, Integer> codInscColumn;

    @FXML
    private TableView<TableEmprestimo> tableEmprestimo;

    @FXML
    private TableColumn<TableEmprestimo, String> nomeColumn;

    @FXML
    private TableColumn<TableEmprestimo, String> datDevColumn;

    @FXML
    private TableColumn<TableEmprestimo, String> tituloColumn;

    @FXML
    void cancelBtnAction(ActionEvent event) {
        GerEmprestimos p = new GerEmprestimos();
        try {
            p.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(ListarEmprestimosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nChamadaColumn.setCellValueFactory(new PropertyValueFactory<>("chamada"));  
        tituloColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        dataEmpColumn.setCellValueFactory(new PropertyValueFactory<>("dataemp"));
        datDevColumn.setCellValueFactory(new PropertyValueFactory<>("datadev"));
        codInscColumn.setCellValueFactory(new PropertyValueFactory<>("codinsc"));    
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        tableEmprestimo.setItems(listaDeEmprestimos(TableEmprestimo.converteEmprestimos()));
        
    }
    
    /**
     * Método responsavel por converter a List adequada para a TableView.
     * @param list Lista de Emprestimos.
     * @return Lista apropriada para a TableView.
     */
    private ObservableList<TableEmprestimo> listaDeEmprestimos(List<TableEmprestimo> list){
        return FXCollections.observableArrayList(list);
    }

    public void fecha() {
        ListarEmprestimos.getStage().close();
    }
}