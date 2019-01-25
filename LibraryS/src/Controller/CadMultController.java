/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classes.Multimidia;
import Classes.Prateleira;
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

/**
 * FXML Controller class
 *
 * @author death
 */
public class CadMultController {

    @FXML
    private TextField numChamTxt;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField estudioTxt;

    @FXML
    private TextField aquisicaoTxt;

    @FXML
    private Button addBtn;

    @FXML
    private TextField numSeqTxt1;

    @FXML
    private TextField estanteTxt;

    @FXML
    private TextField exemplarTxt;

    @FXML
    private TextField prateleiraTxt;

    @FXML
    private TextField obsTxt;

    @FXML
    private TextField produtorTxt;

    @FXML
    private TextField volTxt;

    @FXML
    private TextField tituleTxt;

    @FXML
    private TextField anoPublicTxt;

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
            String nchamada = numChamTxt.getText();
            if(mult.buscarMaterialNC(nchamada) == null && !(nchamada.equals(""))) mult.setNchamada(nchamada);
            else{
                alertaErro("FALHA AO CADASTRAR MULTIMÍDIA","ATENÇÃO: Já existir uma multimídia com número de chamada digitado ou campo não preenchido");
                return;
            }
            //Verificar usuario
            mult.setUsercode(11111);
            
            int Codestante = Integer.parseInt(estanteTxt.getText());
            int CodPrateleira = Integer.parseInt(prateleiraTxt.getText());
            if(Prateleira.verifcarPrateleira(Codestante, CodPrateleira)){
                mult.setCodestante(Codestante);
                mult.setCodprateleira(CodPrateleira);
            }
            else{
                alertaErro("FALHA AO CADASTRAR LIVRO", "ATENÇÃO: Não existir uma estante com esse número e número de prateleira");
                return;
            }
            
            int nsequencia = Integer.parseInt(numSeqTxt1.getText());
            if(mult.buscarMaterialNS(nsequencia) == null)  mult.setNsequencia(nsequencia);
            else{
                alertaErro("FALHA AO CADASTRAR LIVRO","ATENÇÃO: Já existir um livro com número de sequência digitado ou campo não preenchido");
                return;
            }


            mult.setData(new Date());

            String titulo = tituleTxt.getText();
            if(!(titulo.equals(""))) mult.setTitulo(titulo);
            else{
                alertaErro("FALHA AO CADASTRAR MULTIMÍDIA","ATENÇÃO: Campo título não preenchido");
                return;            
            }

            int exemplar = 0;
            String temp = exemplarTxt.getText();
            if(!(temp.equals(""))){
               exemplar = Integer.parseInt(temp);
            }
            mult.setExemplar(exemplar);

            int volume = 0;
            temp = volTxt.getText();
            if(!(temp.equals(""))){
               volume = Integer.parseInt(temp);
            }
            mult.setVolume(volume);

            temp = localPublicTxt.getText();
            if(!(temp.equals(""))){
                mult.setLocal(temp);
            }else{
                alertaErro("FALHA AO CADASTRAR LIVRO", "ATENÇÃO: Campo local de publicação obrigatorio não foi preenchido");
                return;
            } 

            mult.setAnopublicacao(Integer.parseInt(anoPublicTxt.getText())); 
            
            temp = aquisicaoTxt.getText();
            if(!(temp.equals(""))){
                mult.setFormadeaquisicao(temp);
            }else{
                alertaErro("FALHA AO CADASTRAR LIVRO", "ATENÇÃO: Campo forma de arquisição obrigatorio não foi preenchido");
                return;
            }
            
            mult.setObservacao(obsTxt.getText());
            mult.setStatus("Disponivel");
            
            temp = produtorTxt.getText();
            if(!(temp.equals(""))){
                mult.setProdutor(temp);
            }else{
                alertaErro("FALHA AO CADASTRAR LIVRO", "ATENÇÃO: Campo produtor obrigatorio não foi preenchido");
                return;
            }
            
            temp = estudioTxt.getText();
            if(!(temp.equals(""))){
                mult.setEstudio(temp);
            }else{
                alertaErro("FALHA AO CADASTRAR LIVRO", "ATENÇÃO: Campo estudio obrigatorio não foi preenchido");
                return;
            }

            if(mult.cadastrarMaterial()){
                alertaComf("MULTIMÍDIA CADASTRADA", "Cadastro da multimídia realizado com sucesso");
            }else{
                alertaErro("MULTIMÍDIA NÃO CADASTRADO", "Falha ao cadastrar nova multimídia");          
            }
            
        }catch (NumberFormatException e){
            alertaErro("FALHA AO CADASTRAR MULTIMÍDIA","ATENÇÃO: Campos númericos não peenchido ou se adicionou simbolos como: [,./ -] e outro. Adicione apenas inteiros");
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
