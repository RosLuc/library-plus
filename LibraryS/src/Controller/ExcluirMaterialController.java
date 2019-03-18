/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Materiais.Livro;
import Materiais.Material;
import Materiais.Multimidia;
import LibraryScreens.ExcluirMaterial;
import LibraryScreens.GerAcervo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
public class ExcluirMaterialController {

    @FXML
    private Button excluirMatBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField numChamTxt;
    
    @FXML
    private CheckBox livroBox;

    @FXML
    private CheckBox multBox;
    
    /**
     * Método responssável por bloquear o livroSelectBox caso multSelectBox
     * @param event
     */
    @FXML
    void multSelectBox(ActionEvent event) {
        livroBox.setIndeterminate(true);
    }
    
    /**
     * Método responssável por bloquear o multSelectBox caso livroSelectBox
     * @param event
     */
    @FXML
    void livroSelectBox(ActionEvent event) {
        multBox.setIndeterminate(true);
    }

    @FXML
    void cancelBtnAction(ActionEvent event) {
        returnScreen();
    }

    @FXML
    void excluirMatBtnAction(ActionEvent event) {
        excluirMaterial();
    }

    @FXML
    void excluirMatKeyAction(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            excluirMaterial();
        }
    }
    
    public void excluirMaterial(){
        try {
            if(!(numChamTxt.getText().trim().equals(""))){
                Material mat;
                if(livroBox.isSelected()){
                    mat = new Livro();
                    mat.setNchamada(Integer.parseUnsignedInt(numChamTxt.getText()));
                    if((mat = mat.buscarMaterialNC()) != null){
                        Emprestimo emp = Emprestimo.buscarEmprestimoDeMaterial(mat.getNchamada());
                        if(emp == null) {
                            if(mat.deleteMaterial()) {
                                returnScreen();
                                alertaComf("LIVRO EXCLUIDO COM SUCESSO","");
                            }else {
                                alertaErro("FALHA AO EXCLUIR LIVRO","Tente novamente.");
                            }
                        }alertaErro("FALHA AO EXCLUIR LIVRO","Livro está em um emprestimo ativo");
                    }else{
                        alertaErro("FALHA AO EXCLUIR LIVRO","Livro não encontrada no sistema..");
                    }
                }else if(multBox.isSelected()){
                    mat = new Multimidia();
                    mat.setNchamada(Integer.parseUnsignedInt(numChamTxt.getText()));
                    if((mat = mat.buscarMaterialNC()) != null){
                        Emprestimo emp = Emprestimo.buscarEmprestimoDeMaterial(mat.getNchamada());
                        if(emp == null) {
                            if(mat.deleteMaterial()) {
                                returnScreen();
                                alertaComf("MULTIMIDIA EXCLUIDO COM SUCESSO","");
                            }else {
                                alertaErro("FALHA AO EXCLUIR MULTIMIDIA","Tente novamente.");
                            }
                        }else alertaErro("FALHA AO EXCLUIR LIVRO","Multimídia está em um emprestimo ativo.");
                    }else {
                        alertaErro("FALHA AO EXCLUIR MULTIMIDIA","Multimidia não encontrada no sistema.");
                    }
                }else {
                    alertaErro("FALHA AO EXCLUIR.", "Selecione o tipo de material a ser excluido.");
                }
            } else alertaErro("FALHA AO EXCLUIR LIVRO","Nº de chamada não preenchido.");
        }catch (NumberFormatException e){
            alertaErro("FALHA AO EXCLUIR.","ATENÇÃO:"
                    + " Campos númericos não peenchido ou foi adicionado simbolos como:"
                    + " [,./ -] e outro. Adicione apenas inteiros positivos.");
        }catch (HibernateException e){
            alertaErro("FALHA AO EXCLUIR.", e.toString());
        }catch (Exception e){
            alertaErro("FALHA AO EXCLUIR.", "Erro.");
        }
    }

    public void fecha() {
        ExcluirMaterial.getStage().close();
    }

    public void returnScreen() {
        GerAcervo g = new GerAcervo();
        try {
            g.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(ExcluirPessoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Método responsável por alerta de erro.
     * @param HeaderText String exibida no HeaderText.
     * @param ContentText String exibida no ContextText.
     */
    private void alertaErro(String HeaderText, String ContentText){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(HeaderText);
            alert.setTitle("Excluir Material");
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
        alert.setTitle("Excluir Material");
        alert.setContentText(ContentText);
        alert.show();
    }

}
