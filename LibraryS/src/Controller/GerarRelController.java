/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.EmtDoc;
import LibraryScreens.GerarRel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import Documentos.PDF;
import Materiais.Livro;
import Materiais.Multimidia;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author death
 */
public class GerarRelController {

    @FXML
    private Button relLivroBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button relMultBtn;
    
    @FXML
    private Label caminhoLabel;

    @FXML
    void cancelBtnAction(ActionEvent event) {
        EmtDoc p = new EmtDoc();
        try {
            p.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(GerarCertController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void relLivroBtnAction(ActionEvent event) {
        caminhoLabel.setText("Gerando arquivo.");
        List<Livro> listLivro = new Livro().listarMaterial();
        if(!listLivro.isEmpty()){
            File select = buscarDiretorio();
            try {
                String diretorio = PDF.gerarLivroPDF(listLivro, select);
                caminhoLabel.setText("Arquivo salvo em: "+ diretorio);
            } catch (DocumentException | IOException ex) {
                caminhoLabel.setText("Erro ao gerar documento.");
            }
        }else caminhoLabel.setText("Acervo de livros vazio.");   
    }

    @FXML
    void relMultBtnAction(ActionEvent event) {
        caminhoLabel.setText("Gerando arquivo.");
        List<Multimidia> listmult = new Multimidia().listarMaterial();
        if(!listmult.isEmpty()){
            File select = buscarDiretorio();
            try {
                String diretorio = PDF.gerarMultimidiaPDF(listmult, select);
                caminhoLabel.setText("Arquivo salvo em: "+ diretorio);
            } catch (DocumentException | IOException ex) {
                caminhoLabel.setText("Erro ao gerar documento.");
            }
        }else caminhoLabel.setText("Acervo de multimidias vazio."); 
    }

    public void fecha() {
        GerarRel.getStage().close();
    }
    
    private static File buscarDiretorio(){
        JFileChooser file = new JFileChooser();
        file.setDialogTitle("Selecione o diretorio");
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF", "pdf");
        file.setFileFilter(filter);
        file.showOpenDialog(file);
        File selected = file.getSelectedFile();
        return selected;
    }
}
