/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classes.Livro;
import LibraryScreens.ConsultarMaterial;
import LibraryScreens.GerAcervo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
 * @author death
 */
public class ConsultarMaterialController{
    
    @FXML
    private TableView<Livro> table;
        
    @FXML
    private TableColumn<Livro, Integer> nseqTb;
    
    @FXML
    private TableColumn<Livro, String> nchamTb;
        
    @FXML
    private TableColumn<Livro, String> tituloTb;

    @FXML
    private TableColumn<Livro, String> autorTb;

    @FXML
    private TableColumn<Livro, Integer> nestTb;
    
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
    private Button limparBtn;

    @FXML
    private TextField tituloTxt;
    
    @FXML
    private TextField nSeqTxt;

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

    @FXML
    void backBtnAction(ActionEvent event) {
        GerAcervo ga = new GerAcervo();
        try {
            ga.start(new Stage());
        } catch (Exception e) {
            Logger.getLogger(ConsultarMaterialController.class.getName()).log(Level.SEVERE, null, e);
        }
        fecha();
    }
    
    @SuppressWarnings("unchecked")
    private void initTable(ObservableList<Livro> list) throws Exception{
        
        nseqTb.setCellValueFactory(new PropertyValueFactory<>("nsequencia"));
        nchamTb.setCellValueFactory(new PropertyValueFactory<>("nchamada"));
        nestTb.setCellValueFactory(new PropertyValueFactory<>("codestante"));
        npraTb.setCellValueFactory(new PropertyValueFactory<>("codprateleira"));
        tituloTb.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        autorTb.setCellValueFactory(new PropertyValueFactory<>("autor"));
        editTb.setCellValueFactory(new PropertyValueFactory<>("editora"));
        exemplarTb.setCellValueFactory(new PropertyValueFactory<>("exemplar"));
        volTb.setCellValueFactory(new PropertyValueFactory<>("volume"));
        anoTb.setCellValueFactory(new PropertyValueFactory<>("anopublicacao"));
        statusTb.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.setItems(list);
            
    }

    @FXML
    void pesquisarBtnAction(ActionEvent event) {
        try{
            Livro l = new Livro();
            String temp = nChamTxt.getText();
            if(!(temp.equals(""))){
                l.setNchamada(temp);
            }
            
            temp = nSeqTxt.getText();
            if(!(temp.equals(""))){
                l.setNsequencia(Integer.parseInt(temp));
            }
            
            temp = tituloTxt.getText();
            if(!(temp.equals(""))){
                l.setTitulo(temp);
            }
            
            temp = autorTx.getText();
            if(!(temp.equals(""))){
               l.setAutor(temp);
            }
            
            temp = editTxt.getText();
            if(!(temp.equals(""))){
               l.setEditora(temp);
            }
            
            temp = localTxt.getText();
            if(!(temp.equals(""))){
               l.setLocal(temp);
            }
        
            @SuppressWarnings("unchecked")
            ObservableList<Livro> list = FXCollections.observableList(l.filtrarMaterialCMP());
            initTable(list);
        }catch(NumberFormatException e){
            alertaErro("Erro ao pesquisar", "Nós campos númericos digite apenas números inteiros.");
            Logger.getLogger(ConsultarMaterialController.class.getName()).log(Level.SEVERE, null, e);
        }catch (Exception e) {
            Logger.getLogger(ConsultarMaterialController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void limparBtnAction(ActionEvent event) {

    }

    /**
     * Método responsável por fechar a tela atual
     */
    public void fecha() {
        ConsultarMaterial.getStage().close();
    }
    
    /**
     * Método responsável por alerta de erro.
     * @param HeaderText String exibida no HeaderText.
     * @param ContentText String exibida no ContextText.
     */
    private void alertaErro(String HeaderText, String ContentText){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(HeaderText);
            alert.setTitle("Cadastro de Livro");
            alert.setContentText(ContentText);
            alert.show();
    }
    
    /**
     * Método responsável por alerta de confirmação.
     * @param HeaderText String exibida no HeaderText.
     * @param ContentText String exibida no ContextText.
     */
    private void alertaComf(String HeaderText, String ContentText){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(HeaderText);
        alert.setTitle("Cadastro de Livro");
        alert.setContentText(ContentText);
        alert.show();
    }  


}
