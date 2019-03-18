/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.AtualizarPessoa;
import LibraryScreens.GerPessoas;
import Pessoa.Pessoa;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.HibernateException;

/**
 * FXML Controller class
 *
 * @author death
 */
public class AtualizarPessoaController {
    
    private Pessoa pessoa;

    @FXML
    private Button atPesBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button confPessoa;

    @FXML
    private TextField nomeTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField turnoTxt;

    @FXML
    private TextField turmaTxt;

    @FXML
    private TextField serieTxt;

    @FXML
    private TextField telTxt;

    @FXML
    private TextField cepTxt;

    @FXML
    private TextField estadoTxt;

    @FXML
    private TextField cidadeTxt;

    @FXML
    private TextField bairroTxt;

    @FXML
    private TextField enderecoTxt;

    @FXML
    private TextField numeroTxt;

    @FXML
    private Label serieLabel;

    @FXML
    private Label turmaLabel;

    @FXML
    private Label turnoLabel;

    @FXML
    private TextField codInscTxt;

    @FXML
    void atPesBtnAction(ActionEvent event) {
        if(pessoa != null) {
            if((pessoa = capturarPessoa()) != null){
                if(pessoa.atualizaPessoa()){
                    retornar();
                    confirma();
                }else erro("Erro ao atualizar");
            }else erro("Erro ao capturar os dados.");
        }else erro("Verifique a pessoa antes de atualizar");
    }

    @FXML
    void cancelBtnAction(ActionEvent event) {
        GerPessoas c = new GerPessoas();
        try {
            c.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(CadPessoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void confPessoaBtnAction(ActionEvent event) {
        String temp = codInscTxt.getText();
        try {
            if(!(temp.trim().equals(""))){
                pessoa = Pessoa.buscarPessoa(Integer.parseUnsignedInt(temp));
                if(pessoa != null){
                    nomeTxt.setPromptText(pessoa.getNome());
                    emailTxt.setPromptText(pessoa.getEmail());
                    if(pessoa.getCategoria().equals("Aluno")){
                        turnoTxt.setPromptText(pessoa.getTurno());
                        turmaTxt.setPromptText(pessoa.getTurma());
                        serieTxt.setPromptText(pessoa.getSerie());
                    }else {
                        turnoTxt.setEditable(false);
                        turnoTxt.setText("-");
                        turmaTxt.setEditable(false);
                        turmaTxt.setText("-");
                        serieTxt.setEditable(false);
                        serieTxt.setText("-");
                    }
                    telTxt.setPromptText(pessoa.getContato());
                    cepTxt.setPromptText(pessoa.getCep());
                    estadoTxt.setPromptText(pessoa.getEstado());
                    cidadeTxt.setPromptText(pessoa.getCidade());
                    bairroTxt.setPromptText(pessoa.getBairro());
                    enderecoTxt.setPromptText(pessoa.getEndereco());
                    numeroTxt.setPromptText(pessoa.getNumero());
                }else erro("Pessoa não encontrada, verifique o código de inscrição.");
            }else erro("Código de inscrição não preenchido.");
        }catch (NumberFormatException e){
            erro("ATENÇÃO: Campos númericos não peenchido ou foi adicionado simbolos como: [,./ -] e outro. Adicione apenas inteiros positivos.");
        }catch (HibernateException e){
            erro(e.toString());
        }catch (Exception e){
            erro("Erro.");
        }
    }
    
    public void retornar() {
        GerPessoas c = new GerPessoas();
        try {
            c.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(CadPessoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fecha() {
        AtualizarPessoa.getStage().close();
    }
    
    public Pessoa capturarPessoa() {
        if(pessoa != null) {
            String temp;
            temp = nomeTxt.getText();
            if(!(temp.trim().equals(""))){
                pessoa.setNome(temp);
            }
            
            temp = emailTxt.getText();
            if(!(temp.trim().equals(""))){
                pessoa.setEmail(temp);
            }
            
            if(pessoa.getCategoria().equals("Aluno")) {
                temp = turnoTxt.getText();
                if(!(temp.trim().equals(""))){
                    pessoa.setTurno(temp);
                }
            
                temp = turmaTxt.getText();
                if(!(temp.trim().equals(""))){
                    pessoa.setTurma(temp);
                }
            
                temp = serieTxt.getText();
                if(!(temp.trim().equals(""))){
                    pessoa.setSerie(temp);
                }
            }
            
            temp = telTxt.getText();
            if(!(temp.trim().equals(""))){
                pessoa.setContato(temp);
            }
            
            temp = cepTxt.getText();
            if(!(temp.trim().equals(""))){
                pessoa.setCep(temp);
            }
            
            temp = estadoTxt.getText();
            if(!(temp.trim().equals(""))){
                pessoa.setEstado(temp);
            }
            
            temp = cidadeTxt.getText();
            if(!(temp.trim().equals(""))){
                pessoa.setCidade(temp);
            }
            
            temp = bairroTxt.getText();
            if(!(temp.trim().equals(""))){
                pessoa.setBairro(temp);
            }
            
            temp = enderecoTxt.getText();
            if(!(temp.trim().equals(""))){
                pessoa.setEndereco(temp);
            }
            
            temp = numeroTxt.getText();
            if(!(temp.trim().equals(""))){
                pessoa.setNumero(temp);
            }
        }
        return pessoa;
    }
    
    /**
     * Método responsável por confirmar.
     */
    public void confirma(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("PESSOA ATUALIZADA COM SUCESSO");
        alert.setTitle("Atualizar pessoa");
        alert.setContentText("Pessoa atualizada com sucesso.");
        alert.show();
    }
    
    /**
     * Método responsável por alertar erro.
     * @param mensagem Mensagem a ser exibida.
     */
    public void erro(String mensagem){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("FALHA AO ATUALIZAR PESSOA.");
        alert.setTitle("Atualizar pessoa");
        alert.setContentText(mensagem);
        alert.show();
    }
}
