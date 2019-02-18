/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classes.Livro;
import LibraryScreens.ConsultarMaterial;
import LibraryScreens.GerAcervo;
import java.util.List;
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

        table.setItems(list);
            
    }

    @FXML
    void pesquisarBtnAction(ActionEvent event) {
        try{
            Livro l = new Livro();
            String temp = nChamTxt.getText();
            if(!(temp.equals(""))){
                l.setNchamada(Integer.parseUnsignedInt(temp));
            }
            
            temp = cddTxt.getText();
            if(!(temp.equals(""))){
                l.setCdd(Integer.parseUnsignedInt(temp));
            }
            
            temp = cduTxt.getText();
            if(!(temp.equals(""))){
                
                l.setCdu(Integer.parseUnsignedInt(temp));
            }
            
            l.setTitulo(tituloTxt.getText());
            l.setAutor(autorTx.getText());
            l.setEditora(editTxt.getText());
            l.setLocal(localTxt.getText());
            List<Livro> list = l.filtrarMaterialCMP(); 
            
            if(list != null){
                ObservableList<Livro> Obslist = FXCollections.observableList(list);
                initTable(Obslist);                
            }
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
