/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classes.Multimidia;
import LibraryScreens.CadMaterial;
import LibraryScreens.CadMult;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.HibernateException;

/**
 * FXML Controller class
 *
 * @author death
 */
public class CadMultController {

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField estudioTxt;

    @FXML
    private TextField aquisicaoTxt;

    @FXML
    private Button addBtn;

    @FXML
    private TextField estanteTxt;

    @FXML
    private TextField exemplarTxt;

    @FXML
    private TextField cddTxt;

    @FXML
    private TextField prateleiraTxt;

    @FXML
    private TextField obsTxt;

    @FXML
    private TextField volTxt;

    @FXML
    private TextField produtorTxt;

    @FXML
    private TextField nchamadaTxt;

    @FXML
    private TextField tituleTxt;

    @FXML
    private TextField anoPublicTxt;

    @FXML
    private TextField cduTxt;

    @FXML
    private TextField localPublicTxt;

    /**
     * Método responssável por cancelar a ação atual e retornar para a tela
     * antetior
     *
     * @param event
     */
    @FXML
    void cancelBtnAction(ActionEvent event) {
        returnCad();
    }

    /**
     * Método responsável por Cadastrar Multimídia
     *
     * @param event
     */
    @FXML
    void addBtnAction(ActionEvent event) {
        returnCad();
        addMultimidia();
    }

    /**
     * Método responsável por fechar a tela atual
     */
    public void fecha() {
        CadMult.getStage().close();
    }

    /**
     * Método responsável por retornar para a tela anterior e fechar a tela
     * atual
     */
    public void returnCad() {
        CadMaterial l = new CadMaterial();
        try {
            l.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CadMultController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }
    
        private void addMultimidia(){
        try{
            Multimidia mult = new Multimidia();
            //Verificar usuario
            mult.setUsercode(11111);
            
            String temp = estanteTxt.getText();
            if(!(temp.equals(""))){
                mult.setCorestante(temp);
            }
            else{
                alertaErro("FALHA AO CADASTRAR MULTIMÍDIA", "ATENÇÃO: Campo de obrigatorio Cor da estante não preenchido.");
                return;
            }
            mult.setCodprateleira(Integer.parseUnsignedInt(prateleiraTxt.getText()));
            
            mult.setCdu(Integer.parseUnsignedInt(cduTxt.getText()));
            if((mult.buscarMaterialCDU())!= null){
                alertaErro("FALHA AO CADASTRAR MULTIMÍDIA","ATENÇÃO: Já existir um multimídia com número de CDU digitado.");
                return;
            }
            mult.setCdd(Integer.parseUnsignedInt(cddTxt.getText()));
            if((mult.buscarMaterialCDD())!= null){
                alertaErro("FALHA AO CADASTRAR MULTIMÍDIA","ATENÇÃO: Já existir um multimídia com número de CDD digitado.");
                return;
            }

            mult.setData(new Date());

            String titulo = tituleTxt.getText();
            if(!(titulo.equals(""))) mult.setTitulo(titulo);
            else{
                alertaErro("FALHA AO CADASTRAR MULTIMÍDIA","ATENÇÃO: Campo título obrigatorio não preenchido");
                return;            
            }

            int exemplar = 0;
            temp = exemplarTxt.getText();
            if(!(temp.equals(""))){
               exemplar = Integer.parseUnsignedInt(temp);
            }
            mult.setExemplar(exemplar);

            int volume = 0;
            temp = volTxt.getText();
            if(!(temp.equals(""))){
               volume = Integer.parseUnsignedInt(temp);
            }
            mult.setVolume(volume);

            temp = localPublicTxt.getText();
            if(!(temp.equals(""))){
                mult.setLocal(temp);
            }else{
                alertaErro("FALHA AO CADASTRAR MULTIMÍDIA", "ATENÇÃO: Campo local de publicação obrigatorio não foi preenchido");
                return;
            } 

            mult.setAnopublicacao(Integer.parseUnsignedInt(anoPublicTxt.getText())); 
            
            temp = aquisicaoTxt.getText();
            if(!(temp.equals(""))){
                mult.setFormadeaquisicao(temp);
            }else{
                alertaErro("FALHA AO CADASTRAR MULTIMÍDIA", "ATENÇÃO: Campo forma de arquisição obrigatorio não foi preenchido");
                return;
            }
            
            mult.setObservacao(obsTxt.getText());
            mult.setStatus("Disponivel");
            
            temp = produtorTxt.getText();
            if(!(temp.equals(""))){
                mult.setProdutor(temp);
            }else{
                alertaErro("FALHA AO CADASTRAR MULTIMÍDIA", "ATENÇÃO: Campo produtor obrigatorio não foi preenchido");
                return;
            }
            
            temp = estudioTxt.getText();
            if(!(temp.equals(""))){
                mult.setEstudio(temp);
            }else{
                alertaErro("FALHA AO CADASTRAR MULTIMÍDIA", "ATENÇÃO: Campo estudio obrigatorio não foi preenchido");
                return;
            }

            if(mult.cadastrarMaterial()){
                alertaComf("MULTIMÍDIA CADASTRADA", "Cadastro da multimídia realizado com sucesso");
            }else{
                alertaErro("MULTIMÍDIA NÃO CADASTRADO", "Falha ao cadastrar nova multimídia");          
            }
            
        }catch (NumberFormatException e){
            alertaErro("FALHA AO CADASTRAR MULTIMÍDIA","ATENÇÃO: Campos númericos não peenchido ou se adicionou simbolos como: [,./ -] e outro. Adicione apenas inteiros");
        }catch (HibernateException er){
            alertaErro("FALHA AO CADASTRAR LIVRO MULTIMÍDIA", er.toString());
        }catch (Exception er){
            alertaErro("FALHA AO CADASTRA MULTIMÍDIA", "Erro");
        }
    }
    
    private void alertaErro(String HeaderText, String ContentText){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(HeaderText);
            alert.setTitle("Cadastro de Livro");
            alert.setContentText(ContentText);
            alert.show();
    }
    
    private void alertaComf(String HeaderText, String ContentText){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(HeaderText);
        alert.setTitle("Cadastro de Livro");
        alert.setContentText(ContentText);
        alert.show();
    }

}
