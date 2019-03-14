/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classes.Livro;
import Classes.Multimidia;
import LibraryScreens.ConsultarMateriais;
import LibraryScreens.GerAcervo;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author luand
 */
public class ConsultarMateriaisController implements Initializable {

    @FXML
    private TableView<Livro> table;

    @FXML
    private TableColumn<Livro, Integer> cduTb;

    @FXML
    private TableColumn<Livro, Integer> cddTb;

    @FXML
    private TableColumn<Livro, Integer> nchamTb;

    @FXML
    private TableColumn<Livro, String> tituloTb;

    @FXML
    private TableColumn<Livro, String> autorTb;

    @FXML
    private TableColumn<Livro, String> nestTb;

    @FXML
    private TableColumn<Livro, Integer> npraTb;

    @FXML
    private TableColumn<Livro, Integer> volTb;

    @FXML
    private TableColumn<Livro, String> editTb;

    @FXML
    private TableColumn<Livro, String> statusTb;

    @FXML
    private TableColumn<Livro, Integer> anoTb;

    @FXML
    private TableColumn<Livro, Integer> exemplarTb;

    @FXML
    private TableColumn<Livro, String> dataTb;

    @FXML
    private Button limparBtn;

    @FXML
    private TextField tituloTxt;

    @FXML
    private TextField cduTxt;

    @FXML
    private TextField cddTxt;

    @FXML
    private TextField localTxt;

    @FXML
    private TextField editTxt;

    @FXML
    private TextField nChamTxt;

    @FXML
    private Button backBtn;

    @FXML
    private Button pesquisarBtn;

    @FXML
    private TextField autorTx;

    /**
     * Método responsável por fechar a tela atual
     */
    public void fecha() {
        ConsultarMateriais.getStage().close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cduTb.setCellValueFactory(new PropertyValueFactory<>("cdu"));
        cddTb.setCellValueFactory(new PropertyValueFactory<>("cdd"));
        nchamTb.setCellValueFactory(new PropertyValueFactory<>("nchamada"));
        nestTb.setCellValueFactory(new PropertyValueFactory<>("corestante"));
        npraTb.setCellValueFactory(new PropertyValueFactory<>("codprateleira"));
        tituloTb.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        autorTb.setCellValueFactory(new PropertyValueFactory<>("autor"));
        editTb.setCellValueFactory(new PropertyValueFactory<>("editora"));
        exemplarTb.setCellValueFactory(new PropertyValueFactory<>("exemplar"));
        volTb.setCellValueFactory(new PropertyValueFactory<>("volume"));
        anoTb.setCellValueFactory(new PropertyValueFactory<>("anopublicacao"));
        statusTb.setCellValueFactory(new PropertyValueFactory<>("status"));
        dataTb.setCellValueFactory(new PropertyValueFactory<>("data"));

        nchamTb2.setCellValueFactory(new PropertyValueFactory<>("nchamada"));
        nestTb2.setCellValueFactory(new PropertyValueFactory<>("corestante"));
        npraTb2.setCellValueFactory(new PropertyValueFactory<>("codprateleira"));
        tituloTb2.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        produtorTb.setCellValueFactory(new PropertyValueFactory<>("produtor"));
        estudioTb.setCellValueFactory(new PropertyValueFactory<>("estudio"));
        exemplarTb2.setCellValueFactory(new PropertyValueFactory<>("exemplar"));
        volTb2.setCellValueFactory(new PropertyValueFactory<>("volume"));
        anoTb2.setCellValueFactory(new PropertyValueFactory<>("anopublicacao"));
        statusTb2.setCellValueFactory(new PropertyValueFactory<>("status"));
        dataTb2.setCellValueFactory(new PropertyValueFactory<>("data"));

    }

    /**
     * Método responsável por detectar um ActionEvent no backBtn e voltar para
     * tela anterior.
     *
     * @param event
     */
    @FXML
    void backBtnAction(ActionEvent event) {
        GerAcervo ga = new GerAcervo();
        try {
            ga.start(new Stage());
        } catch (Exception e) {
            Logger.getLogger(ConsultarMateriaisController.class.getName()).log(Level.SEVERE, null, e);
        }
        fecha();
    }

    /**
     * Método responsável por detectar um ActionEvent no pesquisarBtn e
     * pesquisar por livros.
     *
     * @param event
     */
    @FXML
    void pesquisarBtnAction(ActionEvent event) {
        try {
            Livro l = new Livro();
            String temp = nChamTxt.getText().trim();
            if (!(temp.equals(""))) {
                l.setNchamada(Integer.parseUnsignedInt(temp));
            }

            temp = cddTxt.getText().trim();
            if (!(temp.equals(""))) {
                l.setCdd(Integer.parseUnsignedInt(temp));
            }

            temp = cduTxt.getText().trim();
            if (!(temp.equals(""))) {

                l.setCdu(Integer.parseUnsignedInt(temp));
            }

            l.setTitulo(tituloTxt.getText().trim());
            l.setAutor(autorTx.getText().trim());
            l.setEditora(editTxt.getText().trim());
            l.setLocal(localTxt.getText().trim());
            List<Livro> list = l.filtrarMaterialCMP();

            if (list != null) {
                table.setItems(FXCollections.observableList(list));
            }
        } catch (NumberFormatException e) {
            alertaErro("Erro ao pesquisar", "Nós campos númericos digite apenas números inteiros.");
            Logger.getLogger(ConsultarMateriaisController.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(ConsultarMateriaisController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Método responsável por detectar um ActionEvent no limparBtn e limpar e
     * TableView de livros.
     *
     * @param event
     */
    @FXML
    void limparBtnAction(ActionEvent event) {
        nChamTxt.setText("");
        cddTxt.setText("");
        cduTxt.setText("");
        tituloTxt.setText("");
        autorTx.setText("");
        editTxt.setText("");
        localTxt.setText("");
        table.setItems(null);
    }

    //Referente a aba de multimídia.
    @FXML
    private TableView<Multimidia> tableMult;

    @FXML
    private TableColumn<Multimidia, Integer> exemplarTb2;

    @FXML
    private TableColumn<Multimidia, Integer> volTb2;

    @FXML
    private TableColumn<Multimidia, Integer> anoTb2;

    @FXML
    private TableColumn<Multimidia, String> produtorTb;

    @FXML
    private TableColumn<Multimidia, String> estudioTb;

    @FXML
    private TableColumn<Multimidia, Integer> nchamTb2;

    @FXML
    private TableColumn<Multimidia, Integer> npraTb2;

    @FXML
    private TableColumn<Multimidia, String> nestTb2;

    @FXML
    private TableColumn<Multimidia, String> statusTb2;

    @FXML
    private TableColumn<Multimidia, String> tituloTb2;

    @FXML
    private TableColumn<Multimidia, String> dataTb2;

    @FXML
    private TextField tituloTxt2;

    @FXML
    private TextField produtorTx2;

    @FXML
    private TextField estudioTxt2;

    @FXML
    private TextField localTxt2;

    @FXML
    private TextField nChamaTxt2;

    @FXML
    private Button pesquisarBtn2;

    @FXML
    private Button limparBtn2;

    @FXML
    private Button backBtn2;

    /**
     * Método responsável por detectar um ActionEvent no pesquisarMultBtn e
     * pesquisar por multimídias.
     *
     * @param event
     */
    @FXML
    void pesquisarMultBtnAction(ActionEvent event) {
        try {
            Multimidia m = new Multimidia();
            String temp = nChamaTxt2.getText().trim();
            if (!(temp.equals(""))) {
                m.setNchamada(Integer.parseUnsignedInt(temp));
            }

            m.setTitulo(tituloTxt2.getText().trim());
            m.setProdutor(produtorTx2.getText().trim());
            m.setEstudio(estudioTxt2.getText().trim());
            m.setLocal(localTxt2.getText().trim());
            List<Multimidia> list = m.filtrarMaterialCMP();

            if (list != null) {
                tableMult.setItems(FXCollections.observableList(list));
            }
        } catch (NumberFormatException e) {
            alertaErro("Erro ao pesquisar", "Nós campos númericos digite apenas números inteiros.");
            Logger.getLogger(ConsultarMateriaisController.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(ConsultarMateriaisController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Método responsável por detectar um ActionEvent no limparMultBtn e limpar
     * e TableView de multimídias.
     *
     * @param event
     */
    @FXML
    void limparMultBtnAction(ActionEvent event) {
        nChamaTxt2.setText("");
        tituloTxt2.setText("");
        produtorTx2.setText("");
        estudioTxt2.setText("");
        localTxt2.setText("");
        tableMult.setItems(null);
    }

    /**
     * Método responsável por alerta de erro.
     *
     * @param HeaderText String exibida no HeaderText.
     * @param ContentText String exibida no ContextText.
     */
    private void alertaErro(String HeaderText, String ContentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(HeaderText);
        alert.setTitle("Cadastro de Livro");
        alert.setContentText(ContentText);
        alert.show();
    }

}
