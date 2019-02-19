/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classes.Livro;
import LibraryScreens.CadLivro;
import LibraryScreens.CadMaterial;
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
public class CadLivroController {
    
 @FXML
    private TextField editorTxt;

    @FXML
    private Button cancelBtn;

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
    private TextField authorTxt;

    @FXML
    private TextField volTxt;

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
     * @param event
     */
    @FXML
    void cancelBtnAction(ActionEvent event) {
        returnCad();
    }

    /**
     * Método responsável pelo cadastro do livro
     * @param event
     */
    @FXML
    void addBtnAction(ActionEvent event) {
        returnCad();
        addLivro();
    }

    /**
     * Método responsável por fechar a tela atual
     */
    public void fecha() {
        CadLivro.getStage().close();
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
            Logger.getLogger(CadLivroController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }

    /**
     * Método responsável por capturar informações dos campos, inicializar o objeto para ser instanciado para o método que adicionar no banco de dados 
     */
    private void addLivro(){
        try{
            Livro livro = new Livro();
            //Verificar usuario 
            livro.setUsercode(11111);
                        
            String temp = estanteTxt.getText();
            if(!(temp.equals(""))){
                livro.setCorestante(temp);
            }
            else{
                alertaErro("FALHA AO CADASTRAR LIVRO", "ATENÇÃO: Campo de obrigatorio Cor da estante não preenchido.");
                return;
            }
            livro.setCodprateleira(Integer.parseUnsignedInt(prateleiraTxt.getText()));

            livro.setCdu(Integer.parseUnsignedInt(cduTxt.getText()));
            if((livro.buscarMaterialCDU())!= null){
                alertaErro("FALHA AO CADASTRAR LIVRO","ATENÇÃO: Já existir um livro com número de CDU digitado.");
                return;
            }
            livro.setCdd(Integer.parseUnsignedInt(cddTxt.getText()));
            if((livro.buscarMaterialCDD())!= null){
                alertaErro("FALHA AO CADASTRAR LIVRO","ATENÇÃO: Já existir um livro com número de CDD digitado.");
                return;
            }
            
            livro.setData(new Date());

            String titulo = tituleTxt.getText();
            if(!(titulo.equals(""))) livro.setTitulo(titulo);
            else{
                alertaErro("FALHA AO CADASTRAR LIVRO","ATENÇÃO: Campo de obrigatorio título não preenchido.");
                return;            
            }

            int exemplar = 0;
            temp = exemplarTxt.getText();
            if(!(temp.equals(""))){
               exemplar = Integer.parseUnsignedInt(temp);
            }
            livro.setExemplar(exemplar);

            int volume = 0;
            temp = volTxt.getText();
            if(!(temp.equals(""))){
               volume = Integer.parseUnsignedInt(temp);
            }
            livro.setVolume(volume);

            temp = localPublicTxt.getText();
            if(!(temp.equals(""))){
                livro.setLocal(temp);
            }else{
                alertaErro("FALHA AO CADASTRAR LIVRO", "ATENÇÃO: Campo local de publicação obrigatorio não foi preenchido.");
                return;
            } 

            livro.setAnopublicacao(Integer.parseUnsignedInt(anoPublicTxt.getText()));
            
            temp = aquisicaoTxt.getText();
            if(!(temp.equals(""))){
                livro.setFormadeaquisicao(temp);
            }else{
                alertaErro("FALHA AO CADASTRAR LIVRO", "ATENÇÃO: Campo forma de arquisição obrigatorio não foi preenchido.");
                return;
            }
            
            livro.setObservacao(obsTxt.getText());
            livro.setStatus("Disponivel");
            
            temp = authorTxt.getText();
            if(!(temp.equals(""))){
                livro.setAutor(temp);
            }else{
                alertaErro("FALHA AO CADASTRAR LIVRO", "ATENÇÃO: Campo autor obrigatorio não foi preenchido.");
                return;
            }
            temp = editorTxt.getText();
            if(!(temp.equals(""))){
                livro.setEditora(temp);
            }else{
                alertaErro("FALHA AO CADASTRAR LIVRO", "ATENÇÃO: Campo editora obrigatorio não foi preenchido.");
                return;
            }


            if(livro.cadastrarMaterial()){
                alertaComf("LIVRO CADASTRADO", "Cadastro do livro realizado com sucesso.");
            }else{
                alertaErro("LIVRO NÃO CADASTRADO", "Falha ao cadastrar novo Livro.");          
            }
            
        }catch (NumberFormatException e){
            alertaErro("FALHA AO CADASTRAR LIVRO","ATENÇÃO: Campos númericos não peenchido ou se adicionou simbolos como: [,./ -] ou outro. Adicione apenas inteiros.");
        }catch (HibernateException er){
            alertaErro("FALHA AO CADASTRAR LIVRO", er.toString());
        }catch (Exception erro){
            alertaErro("FALHA AO CADASTRAR LIVRO", "ERRO");
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
