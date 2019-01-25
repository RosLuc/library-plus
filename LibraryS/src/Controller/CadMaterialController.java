/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.CadLivro;
import LibraryScreens.CadMaterial;
import LibraryScreens.CadMult;
import LibraryScreens.GerAcervo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author death
 */
public class CadMaterialController {

    @FXML
    private Button cadLivroBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Button cadMultBtn;

    /**
     * Método responsável por abrir a tela de Cadastro de Multimídia
     *
     * @param event
     */
    @FXML
    void cadMultBtnAction(ActionEvent event) {
        CadMult l = new CadMult();
        try {
            l.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CadMaterialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }

    /**
     * Método responsável por abrir a tela de Cadastro de Livro
     *
     * @param event
     */
    @FXML
    void cadLivroBtnAction(ActionEvent event) {
        CadLivro l = new CadLivro();
        try {
            l.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CadMaterialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }

    /**
     * Método responssável por cancelar a ação atual e retornar para a tela
     * antetior
     *
     * @param event
     */
    @FXML
    void backBtnAction(ActionEvent event) {
        returnGerAcervo();
    }

    /**
     * Método responsável por retornar para a tela anterior e fechar a tela
     * atual
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

}
