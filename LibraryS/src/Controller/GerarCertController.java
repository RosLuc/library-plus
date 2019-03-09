/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Documentos.PDF;
import LibraryScreens.EmtDoc;
import LibraryScreens.GerarCert;
import Pessoa.Pessoa;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
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
import javafx.scene.control.Label;
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
public class GerarCertController implements Initializable {

    @FXML
    private Button gerCertBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TableView<Pessoa> tableCert;
    
     @FXML
    private TableColumn<Pessoa, Integer> codinscTb;

    @FXML
    private TableColumn<Pessoa, String> nomeTb;

    @FXML
    private TableColumn<Pessoa, Integer> pontuacaoTb;

    @FXML
    private TextField nomeDocTxt;

    @FXML
    private Button zerarBtn;
    
    @FXML
    private Label caminhoLabel;
    
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
        pontuacaoTb.setCellValueFactory(
            new PropertyValueFactory<>("total_emprestimos"));
        List listpessoa = Pessoa.ListaDePessoa();
        Collections.sort(listpessoa, (t, t1) -> {
            return compareTo((Pessoa) t,(Pessoa) t1);
        });
        tableCert.setItems(listaDePessoas(listpessoa));
    }
    
    public int compareTo(Pessoa p, Pessoa p2){
        if(p.getTotal_emprestimos() < p2.getTotal_emprestimos()){
            return 1;
        }if(p.getTotal_emprestimos() > p2.getTotal_emprestimos()){
            return -1;
        }
        return 0;
    }
    
    /**
     * Método responsavel por converter a List adequada para a TableView.
     * @param list Lista de Pessoa
     * @return Lista apropriada para a TableView.
     */
    private ObservableList<Pessoa> listaDePessoas(List<Pessoa> list){
        return FXCollections.observableArrayList(list);
    }

    @FXML
    void cancelBtnAction(ActionEvent event) {
        EmtDoc p = new EmtDoc();
        try {
            p.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(GerarCertController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }

    @FXML
    void gerCertBtnAction(ActionEvent event) {
        String temp = nomeDocTxt.getText();
        if(!(temp.trim().equals(""))){
            try {
                String diretorio = PDF.Gerar_certificado(temp);
                caminhoLabel.setText("Arquivo salvo em: "+ diretorio + "\\Modelo_Certificado.pdf" );
            } catch (DocumentException | IOException ex) {
                caminhoLabel.setText("Erro ao gerar documento.");
            }
        }else caminhoLabel.setText("Campo do nome vazio.");
    }

    @FXML
    void zerarBtnAction(ActionEvent event) {
        if(Pessoa.zerarTotal()){
            List listpessoa = Pessoa.ListaDePessoa();
            Collections.sort(listpessoa, (t, t1) -> {
                return compareTo((Pessoa) t,(Pessoa) t1);
            });
            tableCert.setItems(listaDePessoas(listpessoa));
            caminhoLabel.setText("Pontuação zerada com sucesso.");
        }else caminhoLabel.setText("Erro ao zerar pontuação.");
    }

    public void fecha() {
        GerarCert.getStage().close();
    }
    
}
