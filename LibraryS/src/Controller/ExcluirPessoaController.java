/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Emprestimo.Emprestimo;
import LibraryScreens.ExcluirPessoa;
import LibraryScreens.GerPessoas;
import Pessoa.Pessoa;
import java.util.List;
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
import org.hibernate.HibernateException;

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
        try {
            if(!(codInscTxt.getText().trim().equals(""))){
                int cod = Integer.parseUnsignedInt(codInscTxt.getText());
                Pessoa p;
                if((p = Pessoa.buscarPessoa(cod)) != null){
                    List<Emprestimo> list = Emprestimo.buscarEmprestimoDePessoa(p.getCodinsc());
                    if(list.isEmpty()) {
                        if(p.excluirPessoa()){
                            returnScreen();
                            alertaComf("PESSOA EXCLUIDO COM SUCESSO.", "");
                        }else {
                            alertaErro("FALHA AO EXCLUIR.","Tente novamente.");
                        }
                    }alertaErro("FALHA AO EXCLUIR.","Pessoa com emprestimo ativo.");
                }else {
                    alertaErro("FAHA AO EXCLUIR PESSOA.","Pessoa não encontrada no sistema.");
                }
            }else alertaErro("FAHA AO EXCLUIR LIVRO","Código de inscrição não preenchido.");
        } catch (NumberFormatException e) {
            alertaErro("FALHA AO CADASTRAR.","ATENÇÃO:"
                    + " Campos númericos não peenchido ou foi adicionado simbolos como:"
                    + " [,./ -] e outro. Adicione apenas inteiros positivos.");
        }catch (HibernateException e){
            alertaErro("FALHA AO EXCLUIR.", e.toString());
        }catch (Exception e){
            alertaErro("FALHA AO EXCLUIR.", "Erro.");
        }
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
        alert.setTitle("Deletar pessoa");
        alert.setContentText(ContentText);
        alert.show();
    }
    
    /**
     * Método responsável por alerta de erro.
     * @param HeaderText String exibida no HeaderText.
     * @param ContentText String exibida no ContextText.
     */
    private void alertaErro(String HeaderText, String ContentText){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(HeaderText);
            alert.setTitle("Deletar pessoa");
            alert.setContentText(ContentText);
            alert.show();
    }

}
