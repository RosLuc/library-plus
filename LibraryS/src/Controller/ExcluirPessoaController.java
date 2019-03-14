/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.ExcluirPessoa;
import LibraryScreens.GerPessoas;
import Pessoa.Pessoa;
import static Pessoa.Pessoa.buscarPessoa;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author death
 */
public class ExcluirPessoaController {

    @FXML
    private Button excluirPesBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField codInscTxt;

    @FXML
    void excluirPesBtnAction(ActionEvent event) {
        excluirPes();
    }

    @FXML
    void excluirPesKeyAction(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            excluirPes();
        }
    }

    @FXML
    void cancelBtnAction(ActionEvent event) {
        returnScreen();
    }

    /**
     * Método responsável por fechar a tela atual
     */
    public void fecha() {
        ExcluirPessoa.getStage().close();
    }

    public void returnScreen() {
        GerPessoas g = new GerPessoas();
        try {
            g.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(ExcluirPessoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluirPes() {
        int cod = Integer.parseInt(codInscTxt.getText());
        Pessoa p = buscarPessoa(cod);
        p.excluirPessoa();
        System.out.println("Excluiu");
        returnScreen();
        alertaComf("Excluir Pessoa", "Pessoa excluida com sucesso");
    }

    /**
     * Método responsável por alerta de confirmação.
     *
     * @param HeaderText String exibida no HeaderText.
     * @param ContentText String exibida no ContextText.
     */
    private void alertaComf(String HeaderText, String ContentText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(HeaderText);
        alert.setTitle("Cadastro de Livro");
        alert.setContentText(ContentText);
        alert.show();
    }

}
