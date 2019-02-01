/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.CadPessoa;
import LibraryScreens.GerPessoas;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author death
 */
public class CadPessoaController {

    @FXML
    private Button cancelBtn;
    @FXML
    private TextField telTxt;
    @FXML
    private TextField enderecoTxt;
    @FXML
    private TextField codInsTxt;
    @FXML
    private TextField emailTxt;
    @FXML
    private Button addBtn;
    @FXML
    private TextField serieTxt;
    @FXML
    private TextField nomeTxt;
    @FXML
    private TextField cepTxt;
    @FXML
    private TextField turnoTxt;
    @FXML
    private TextField categoriaTxt;
    @FXML
    private TextField numeroTxt;
    @FXML
    private TextField cidadeTxt;
    @FXML
    private TextField turmaTxt;
    @FXML
    private TextField bairroTxt;
    @FXML
    private TextField estadoTxt;

    /**
     * Método responssável por cancelar a ação atual e retornar para a tela
     * antetior
     *
     * @param event
     */
    @FXML
    void cancelBtnAction(ActionEvent event) {
        returnPes();
    }

    /**
     * Método responsável por cadastrar uma pessoa
     *
     * @param event
     */
    @FXML
    void addBtnAction(ActionEvent event) {
        returnPes();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("CADASTRO");
        alert.setTitle("Nova Pessoa");
        alert.setContentText("Pessoa adicionada com sucesso");
        alert.show();
    }

    /**
     * Método responsável por retornar para a tela anterior e fechar a tela
     * atual
     */
    public void returnPes() {
        GerPessoas c = new GerPessoas();
        try {
            c.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(CadPessoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método responsável por fechar a tela atual
     */
    public void fecha() {
        CadPessoa.getStage().close();
    }

}
