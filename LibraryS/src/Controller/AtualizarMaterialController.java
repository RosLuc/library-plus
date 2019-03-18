/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classes.Livro;
import Classes.Multimidia;
import LibraryScreens.AtualizarMaterial;
import LibraryScreens.GerAcervo;
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
public class AtualizarMaterialController {
    
    private Livro livro;
    
    private Multimidia mult;
    
    @FXML
    private Button atPesBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button confMaterial;

    @FXML
    private TextField tituleTxt;

    @FXML
    private TextField volTxt;

    @FXML
    private TextField authorTxt;

    @FXML
    private TextField editorTxt;

    @FXML
    private TextField cduTxt;

    @FXML
    private TextField aquisicaoTxt;

    @FXML
    private TextField anoPublicTxt;

    @FXML
    private TextField cddTxt;

    @FXML
    private TextField localPublicTxt;

    @FXML
    private TextField obsTxt;

    @FXML
    private TextField estanteTxt;

    @FXML
    private TextField prateleiraTxt;

    @FXML
    private TextField chamadaTxt;

    @FXML
    private CheckBox multBox;

    @FXML
    private CheckBox livroBox;

    @FXML
    private TextField Txt;

    @FXML
    void atPesBtnAction(ActionEvent event) {
        try {
            if(livroBox.isSelected()) {
                String temp = chamadaTxt.getText();
                if(!(temp.trim().equals(""))){
                    if(livro != null) {
                        livro = livro.buscarLivroNomePrim();
                        int nchamada = livro.getNchamada();
                        livro = livro.buscarLivroNomeUlt();
                        int exemplar = livro.getExemplar();
                        livro = capturarLivro();
                        for(int i = 1; i <= exemplar; i++){
                            livro.setNchamada(nchamada);
                            livro.setExemplar(i);
                            if(livro.buscarMaterialNC() != null) livro.updateMaterial();
                            nchamada++;
                        }
                        retornar();
                        confirma();
                    }else erro("Erro, verifique o livro.");                
                }else erro("Nº de chamada não preenchido");
            } else if(multBox.isSelected()) {
                String temp = chamadaTxt.getText();
                if(!(temp.trim().equals(""))){
                    if(mult != null) {
                        mult = mult.buscarMultNomePrim();
                        int nchamada = mult.getNchamada();
                        mult = mult.buscarMultNomeUlt();
                        int exemplar = mult.getExemplar();
                        mult = capturarMultimidia();
                        for(int i = 1; i <= exemplar; i++){
                            mult.setNchamada(nchamada);
                            mult.setExemplar(i);
                            if(mult.buscarMaterialNC() != null) mult.updateMaterial();
                            nchamada++;
                        }
                        retornar();
                        confirma();
                    }else erro("Erro, verifique o livro.");                
                }else erro("Nº de chamada não preenchido");
            }else erro("Marque o tipo do material");
        }catch (NumberFormatException e){
            erro("ATENÇÃO: Campos númericos não peenchido ou foi adicionado simbolos como: [,./ -] e outro. Adicione apenas inteiros positivos.");
        }catch (HibernateException e){
            erro(e.toString());
        }catch (Exception e){
            erro("Erro.");
        }
    }

    @FXML
    void cancelBtnAction(ActionEvent event) {
        GerAcervo c = new GerAcervo();
        try {
            c.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(GerAcervoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void retornar() {
        GerAcervo c = new GerAcervo();
        try {
            c.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(GerAcervoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void confMaterialBtnAction(ActionEvent event) {
        try {
            if(livroBox.isSelected()) {
                String temp = chamadaTxt.getText();
                if(!(temp.trim().equals(""))){
                    livro = new Livro();
                    livro.setNchamada(Integer.parseUnsignedInt(temp));
                    livro = livro.buscarMaterialNC();
                    if(livro != null) {
                        tituleTxt.setPromptText(livro.getTitulo());
                        volTxt.setPromptText(String.valueOf(livro.getVolume()));
                        authorTxt.setPromptText(livro.getAutor());
                        editorTxt.setPromptText(livro.getEditora());
                        cduTxt.setPromptText(String.valueOf(livro.getCdu()));
                        cddTxt.setPromptText(String.valueOf(livro.getCdd()));
                        aquisicaoTxt.setPromptText(livro.getFormadeaquisicao());
                        anoPublicTxt.setPromptText(String.valueOf(livro.getAnopublicacao()));
                        localPublicTxt.setPromptText(livro.getLocal());
                        obsTxt.setPromptText(livro.getObservacao());
                        estanteTxt.setPromptText(livro.getCorestante());
                        prateleiraTxt.setPromptText(String.valueOf(livro.getCodprateleira()));    
                    }else erro("Livro não encontrada, verifique o Nº de chamada.");
                }else erro("Nº de chamada não preenchido");
            }else if(multBox.isSelected()) {
                String temp = chamadaTxt.getText();
                if(!(temp.trim().equals(""))){
                    mult = new Multimidia();
                    mult.setNchamada(Integer.parseUnsignedInt(temp));
                    mult = mult.buscarMaterialNC();
                    if(mult != null) {
                        tituleTxt.setPromptText(mult.getTitulo());
                        volTxt.setPromptText(String.valueOf(mult.getVolume()));
                        authorTxt.setPromptText(mult.getProdutor());
                        editorTxt.setPromptText(mult.getEstudio());
                        aquisicaoTxt.setPromptText(mult.getFormadeaquisicao());
                        anoPublicTxt.setPromptText(String.valueOf(mult.getAnopublicacao()));
                        localPublicTxt.setPromptText(mult.getLocal());
                        obsTxt.setPromptText(mult.getObservacao());
                        estanteTxt.setPromptText(mult.getCorestante());
                        prateleiraTxt.setPromptText(String.valueOf(mult.getCodprateleira()));    
                    }else erro("Multimidia não encontrada, verifique o Nº de chamada.");
                }else erro("Nº de chamada não preenchido");
            }else erro("Marque o tipo do material");
        }catch (NumberFormatException e){
            erro("ATENÇÃO: Campos númericos não peenchido ou foi adicionado simbolos como: [,./ -] e outro. Adicione apenas inteiros positivos.");
        }catch (HibernateException e){
            erro(e.toString());
        }catch (Exception e){
            erro("Erro.");
        }
    }

    /**
     * Método responssável por bloquear o livroSelectBox caso multSelectBox
     * @param event
     */
    @FXML
    void multSelectBox(ActionEvent event) {
        cduTxt.setEditable(false);
        cduTxt.setText("-");
        cddTxt.setEditable(false);
        cddTxt.setText("-");
        livroBox.setIndeterminate(true);
    }
    
    /**
     * Método responssável por bloquear o multSelectBox caso livroSelectBox
     * @param event
     */
    @FXML
    void livroSelectBox(ActionEvent event) {
        multBox.setIndeterminate(true);
        cduTxt.setEditable(true);
        cduTxt.setText("");
        cddTxt.setEditable(true);
        cddTxt.setText("");
    }

    /**
     * Método responsável por fechar a tela atual
     */
    public void fecha() {
        AtualizarMaterial.getStage().close();
    }
    
    /**
     * Método responsável por capturar informações dos campos, inicializar o objeto para ser instanciado para o método que adicionar no banco de dados.
     * Multimidia
     */
    private Livro capturarLivro() {
        if(livro != null) {
            String temp = estanteTxt.getText();
            if(!(temp.trim().equals(""))){
                livro.setCorestante(temp);
            }
            
            temp = prateleiraTxt.getText();
            if(!(temp.trim().equals(""))){
                livro.setCodprateleira(Integer.parseUnsignedInt(temp));
            }
            
            temp = cduTxt.getText();
            if(!(temp.trim().equals(""))){
                livro.setCdu(Integer.parseUnsignedInt(temp));
                if(livro.buscarLivroCDU()!=null){
                    erro("ATENÇÃO: Já existe um livro cadastraso com esse CDU.");
                    return null;
                }
            }

            temp = cddTxt.getText();
            if(!(temp.trim().equals(""))){
                livro.setCdd(Integer.parseUnsignedInt(temp));
                if(livro.buscarLivroCDD()!=null){
                    erro("ATENÇÃO: Já existe um livro cadastraso com esse CDD.");
                    return null;
                }
            }

            String titulo = tituleTxt.getText();
            if(!(titulo.trim().equals(""))){
                livro.setTitulo(titulo);
            }

            int volume = 0;
            temp = volTxt.getText().trim();
            if(!(temp.equals(""))){
               volume = Integer.parseUnsignedInt(temp);
               livro.setVolume(volume);
            }

            temp = localPublicTxt.getText();
            if(!(temp.trim().equals(""))){
                livro.setLocal(temp);
            } 
            temp = anoPublicTxt.getText();
            if(!(temp.trim().equals(""))) { 
                livro.setAnopublicacao(Integer.parseUnsignedInt(temp));
            }
            
            temp = aquisicaoTxt.getText();
            if(!(temp.trim().equals(""))){
                livro.setFormadeaquisicao(temp);
            }
            
            temp = obsTxt.getText();
            if(!(temp.trim().equals(""))) {
                livro.setObservacao(temp);
            }
            
            temp = authorTxt.getText();
            if(!(temp.trim().equals(""))){
                livro.setAutor(temp);
            }
            
            temp = editorTxt.getText();
            if(!(temp.trim().equals(""))){
                livro.setEditora(temp);
            }
        }           
        return livro;
    }
    
    /**
     * Método responsável por capturar informações dos campos, inicializar o objeto para ser instanciado para o método que adicionar no banco de dados.
     * @return Multimidia 
     */
    private Multimidia capturarMultimidia() {
            
            String temp = estanteTxt.getText();
            if(!(temp.trim().equals(""))){
                mult.setCorestante(temp);
            }
            temp = prateleiraTxt.getText();
            if(!(temp.trim().equals(""))){
                mult.setCodprateleira(Integer.parseUnsignedInt(temp));
            }

            String titulo = tituleTxt.getText();
            if(!(titulo.trim().equals(""))) mult.setTitulo(titulo);

            int volume = 0;
            temp = volTxt.getText();
            if(!(temp.trim().equals(""))){
               volume = Integer.parseUnsignedInt(temp);
               mult.setVolume(volume);
            }
            

            temp = localPublicTxt.getText();
            if(!(temp.trim().equals(""))){
                mult.setLocal(temp);
            }
            
            temp = anoPublicTxt.getText();
            if(!(temp.trim().equals(""))) {
                mult.setAnopublicacao(Integer.parseUnsignedInt(temp));
            } 
            
            temp = aquisicaoTxt.getText();
            if(!(temp.trim().equals(""))){
                mult.setFormadeaquisicao(temp);
            }
            
            temp = obsTxt.getText();
            if(!(temp.trim().equals(""))){
                mult.setObservacao(temp);
            }

            
            temp = authorTxt.getText();
            if(!(temp.trim().equals(""))){
                mult.setProdutor(temp);
            }
            
            temp = editorTxt.getText();
            if(!(temp.trim().equals(""))){
                mult.setEstudio(temp);
            }

            return mult;
    }
    
    /**
     * Método responsável por confirmar.
     */
    public void confirma(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("MATERIAL ATUALIZADO COM SUCESSO");
        alert.setTitle("Atualizar material");
        alert.setContentText("Material atualizada com sucesso.");
        alert.show();
    }
    
    /**
     * Método responsável por alertar erro.
     * @param mensagem Mensagem a ser exibida.
     */
    public void erro(String mensagem){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("FALHA AO ATUALIZAR MATERIAL.");
        alert.setTitle("Atualizar material");
        alert.setContentText(mensagem);
        alert.show();
    }
}
