/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classes.Livro;
import Classes.Multimidia;
import LibraryScreens.CadMaterial;
import LibraryScreens.GerAcervo;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.HibernateException;

/**
 * FXML Controller class
 *
 * @author death
 */
public class CadMaterialController {
    
     @FXML
    private TextField editorTxt;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField aquisicaoTxt;

    @FXML
    private Button addBtn;

    @FXML
    private CheckBox multBox;

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
    private TextField authorTxt;

    @FXML
    private CheckBox livroBox;

    @FXML
    private TextField volTxt;

    @FXML
    private TextField tituleTxt;

    @FXML
    private TextField anoPublicTxt;

    @FXML
    private TextField cduTxt;

    @FXML
    private TextField localPublicTxt;

    @FXML
    void multSelectBox(ActionEvent event) {
        livroBox.setIndeterminate(true);
    }

    @FXML
    void livroSelectBox(ActionEvent event) {
        multBox.setIndeterminate(true);
    }

    /**
     * Método responssável por cancelar a ação atual e retornar para a tela anterior.
     * @param event
     */
    @FXML
    void cancelBtnAction(ActionEvent event) {
        returnGerAcervo();
    }

    /**
     * Método responsável pelo cadastro do livro.
     * @param event
     */
    @FXML
    void addBtnAction(ActionEvent event) {
        returnGerAcervo();
        try{
            if(livroBox.isSelected()){
                Livro livro = capturarLivro();
                if(livro != null){
                    if(livro.cadastrarMaterial()){
                        alertaComf("LIVRO CADASTRADO.", "Cadastro do livro realizado com sucesso.");
                    }else{
                        alertaErro("LIVRO NÃO CADASTRADO.", "Falha ao cadastrar novo Livro.");          
                    }
                }
            }else if(multBox.isSelected()){
                Multimidia mult = capturarMultimidia();
                if(mult != null){
                    if(mult.cadastrarMaterial()){
                        alertaComf("MULTIMÍDIA CADASTRADA.", "Cadastro da multimídia realizado com sucesso.");
                    }else{
                        alertaErro("MULTIMÍDIA NÃO CADASTRADO.", "Falha ao cadastrar nova multimídia.");          
                    }   
                }
            }else{
                alertaErro("FALAHA AO REALIZAR CADASTRO.", "Selecione o tipo de material a ser cadastrado.");
            }
        }catch (NumberFormatException e){
            alertaErro("FALHA AO CADASTRAR.","ATENÇÃO: Campos númericos não peenchido ou foi adicionado simbolos como: [,./ -] e outro. Adicione apenas inteiros positivos.");
        }catch (HibernateException e){
            alertaErro("FALHA AO CADASTRAR.", e.toString());
        }catch (Exception e){
            alertaErro("FALHA AO CADASTRA.", "Erro.");
        }
    }

    /**
     * Método responsável por retornar para a tela anterior e fechar a tela atual
     */
    public void returnGerAcervo() {
        GerAcervo g = new GerAcervo();
        try {
            g.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CadMaterialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }

    /**
     * Método responsável por fechar a tela atual
     */
    public void fecha() {
        CadMaterial.getStage().close();
    }
    
    /**
     * Método responsável por capturar informações dos campos, inicializar o objeto para ser instanciado para o método que adicionar no banco de dados.
     * Multimidia
     */
    private Livro capturarLivro() throws NumberFormatException, HibernateException, Exception{
            Livro livro = new Livro();
            //Verificar usuario 
            livro.setUsercode(11111);
                        
            String temp = estanteTxt.getText().trim();
            if(!(temp.equals(""))){
                livro.setCorestante(temp);
            }
            else{
                alertaErro("FALHA AO CADASTRAR LIVRO.", "ATENÇÃO: Campo de obrigatorio Cor da estante não preenchido.");
                return null;
            }
            
            temp = prateleiraTxt.getText().trim();
            if(!(temp.equals(""))){
                livro.setCodprateleira(Integer.parseUnsignedInt(temp));
            }else{
                alertaErro("FALHA AO CADASTRAR LIVRO.", "ATENÇÃO: Campo de obrigatorio número da prateleira não preenchido.");
                return null;
            }
            
            temp = cduTxt.getText().trim();
            if(!(temp.equals(""))){
                livro.setCdu(Integer.parseUnsignedInt(temp));
            }

            temp = cddTxt.getText().trim();
            if(!(temp.equals(""))){
                livro.setCdd(Integer.parseUnsignedInt(temp));
            }
            
            livro.setData(new Date());

            String titulo = tituleTxt.getText().trim();
            if(!(titulo.equals(""))) livro.setTitulo(titulo);
            else{
                alertaErro("FALHA AO CADASTRAR LIVRO.","ATENÇÃO: Campo de obrigatorio título não preenchido.");
                return null;            
            }

            int exemplar = 0;
            temp = exemplarTxt.getText().trim();
            if(!(temp.equals(""))){
               exemplar = Integer.parseUnsignedInt(temp);
            }
            livro.setExemplar(exemplar);

            int volume = 0;
            temp = volTxt.getText().trim();
            if(!(temp.equals(""))){
               volume = Integer.parseUnsignedInt(temp);
            }
            livro.setVolume(volume);

            temp = localPublicTxt.getText().trim();
            if(!(temp.equals(""))){
                livro.setLocal(temp);
            }else{
                alertaErro("FALHA AO CADASTRAR LIVRO.", "ATENÇÃO: Campo local de publicação obrigatorio não foi preenchido.");
                return null;
            } 

            livro.setAnopublicacao(Integer.parseUnsignedInt(anoPublicTxt.getText()));
            
            temp = aquisicaoTxt.getText().trim();
            if(!(temp.equals(""))){
                livro.setFormadeaquisicao(temp);
            }else{
                alertaErro("FALHA AO CADASTRAR LIVRO.", "ATENÇÃO: Campo forma de arquisição obrigatorio não foi preenchido.");
                return null;
            }
            
            livro.setObservacao(obsTxt.getText().trim());
            livro.setStatus("Disponivel");
            
            temp = authorTxt.getText().trim();
            if(!(temp.equals(""))){
                livro.setAutor(temp);
            }else{
                alertaErro("FALHA AO CADASTRAR LIVRO.", "ATENÇÃO: Campo autor obrigatorio não foi preenchido.");
                return null;
            }
            temp = editorTxt.getText().trim();
            if(!(temp.equals(""))){
                livro.setEditora(temp);
            }else{
                alertaErro("FALHA AO CADASTRAR LIVRO.", "ATENÇÃO: Campo editora obrigatorio não foi preenchido.");
                return null;
            }

            return livro;
    }
    /**
     * Método responsável por capturar informações dos campos, inicializar o objeto para ser instanciado para o método que adicionar no banco de dados.
     * @return Multimidia 
     */
    private Multimidia capturarMultimidia() throws NumberFormatException, HibernateException, Exception {
            
            Multimidia mult = new Multimidia();
            //Verificar usuario
            mult.setUsercode(11111);
            
            String temp = estanteTxt.getText().trim();
            if(!(temp.equals(""))){
                mult.setCorestante(temp);
            }
            else{
                alertaErro("FALHA AO CADASTRAR MULTIMÍDIA.", "ATENÇÃO: Campo de obrigatorio Cor da estante não preenchido.");
                return null;
            }
            temp = prateleiraTxt.getText().trim();
            if(!(temp.equals(""))){
                mult.setCodprateleira(Integer.parseUnsignedInt(temp));
            }else{
                alertaErro("FALHA AO CADASTRAR MULTIMÍDIA.", "ATENÇÃO: Campo de obrigatorio número da prateleira não preenchido.");
                return null;
            }
            
            temp = cduTxt.getText().trim();
            if(!(temp.equals(""))){
                mult.setCdu(Integer.parseUnsignedInt(temp));
            }
            if((mult.buscarMaterialCDU())!= null){
                alertaErro("FALHA AO CADASTRAR MULTIMÍDIA.","ATENÇÃO: Já existir um multimídia com número de CDU digitado.");
                return null;
            }
            temp = cddTxt.getText().trim();
            if(!(temp.equals(""))){
                mult.setCdd(Integer.parseUnsignedInt(temp));
            }
            if((mult.buscarMaterialCDD())!= null){
                alertaErro("FALHA AO CADASTRAR MULTIMÍDIA.","ATENÇÃO: Já existir um multimídia com número de CDD digitado.");
                return null;
            }

            mult.setData(new Date());

            String titulo = tituleTxt.getText().trim();
            if(!(titulo.equals(""))) mult.setTitulo(titulo);
            else{
                alertaErro("FALHA AO CADASTRAR MULTIMÍDIA.","ATENÇÃO: Campo título obrigatorio não preenchido.");
                return null;            
            }

            int exemplar = 0;
            temp = exemplarTxt.getText().trim();
            if(!(temp.equals(""))){
               exemplar = Integer.parseUnsignedInt(temp);
            }
            mult.setExemplar(exemplar);

            int volume = 0;
            temp = volTxt.getText().trim();
            if(!(temp.equals(""))){
               volume = Integer.parseUnsignedInt(temp);
            }
            mult.setVolume(volume);

            temp = localPublicTxt.getText().trim();
            if(!(temp.equals(""))){
                mult.setLocal(temp);
            }else{
                alertaErro("FALHA AO CADASTRAR MULTIMÍDIA.", "ATENÇÃO: Campo local de publicação obrigatorio não foi preenchido.");
                return null;
            } 

            mult.setAnopublicacao(Integer.parseUnsignedInt(anoPublicTxt.getText().trim())); 
            
            temp = aquisicaoTxt.getText().trim();
            if(!(temp.equals(""))){
                mult.setFormadeaquisicao(temp);
            }else{
                alertaErro("FALHA AO CADASTRAR MULTIMÍDIA.", "ATENÇÃO: Campo forma de arquisição obrigatorio não foi preenchido.");
                return null;
            }
            
            mult.setObservacao(obsTxt.getText().trim());
            mult.setStatus("Disponivel");
            
            temp = authorTxt.getText().trim();
            if(!(temp.equals(""))){
                mult.setProdutor(temp);
            }else{
                alertaErro("FALHA AO CADASTRAR MULTIMÍDIA.", "ATENÇÃO: Campo produtor obrigatorio não foi preenchido.");
                return null;
            }
            
            temp = editorTxt.getText().trim();
            if(!(temp.equals(""))){
                mult.setEstudio(temp);
            }else{
                alertaErro("FALHA AO CADASTRAR MULTIMÍDIA.", "ATENÇÃO: Campo estudio obrigatorio não foi preenchido.");
                return null;
            }

            return mult;
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
