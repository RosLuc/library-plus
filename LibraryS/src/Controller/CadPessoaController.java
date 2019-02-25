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
import Pessoa.Pessoa;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author death
 */
public class CadPessoaController implements Initializable {

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
    @FXML
    private ComboBox<String> categoriaBox;
    @FXML
    private Button ButtonCategoria;

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
    
     @Override
    public void initialize(URL url, ResourceBundle rb){
        String[] lista = {"Funcionario", "Professor", "Aluno"};
        categoriaBox.getItems().addAll(lista);
    }

    /**
     * Método responsável por cadastrar uma pessoa
     *
     * @param event
     */
    @FXML
    void addBtnAction(ActionEvent event) {
        Pessoa p = new Pessoa();
        p.setBairro(bairroTxt.getText());
        p.setCategoria(categoriaBox.getValue());
        p.setCep(cepTxt.getText());
        p.setCidade(cidadeTxt.getText());
        p.setContato(telTxt.getText());
        p.setEmail(emailTxt.getText());
        p.setEndereco(enderecoTxt.getText());
        p.setEstado(estadoTxt.getText());
        p.setNome(nomeTxt.getText());
        p.setNumero(numeroTxt.getText());
        p.setSerie(serieTxt.getText());
        p.setTurma(turmaTxt.getText());
        p.setTurno(turnoTxt.getText());
        p.setUsercode(11111);
        if(!"".equals(p.getNome()) && !"".equals(p.getCategoria())){
            if(p.getCategoria().equalsIgnoreCase("Aluno")){
                if(!"".equals(p.getTurno()) && !"".equals(p.getTurno()) && !"".equals(p.getTurno())){
                    if(p.salvarPessoa()){
                        confirma();
                        returnPes();
                    }else{
                        erro();
                    }
                }else{
                    erro();
                }
            }else{
                if(p.salvarPessoa()){
                    confirma();
                    returnPes();
                }else{
                    erro();
                }
            }
        }else erro();
        
        
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
    
    /**
     * Método responsável por confirmar.
     */
    public void confirma(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("CADASTRO");
        alert.setTitle("Nova Pessoa");
        alert.setContentText("Pessoa adicionada com sucesso");
        alert.show();
    }
    
    /**
     * Método responsável por alertar erro.
     */
    public void erro(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("CADASTRO");
        alert.setTitle("Nova Pessoa");
        alert.setContentText("Erro ao cadastrar pessoa.");
        alert.show();
    }
    
}
