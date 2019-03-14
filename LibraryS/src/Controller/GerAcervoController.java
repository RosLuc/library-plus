/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.CadMaterial;
import LibraryScreens.ConsultarMateriais;
import LibraryScreens.ExcluirMaterial;
import LibraryScreens.GerAcervo;
import LibraryScreens.Principal;
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
public class GerAcervoController {

    @FXML
    private Button deletMatBtn1;

    @FXML
    private Button backBtn;

    @FXML
    private Button cadMatBtn;

    @FXML
    private Button consMatBtn;

    @FXML
    void delMatBtnAction(ActionEvent event) {
        ExcluirMaterial c = new ExcluirMaterial();
        try {
            c.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(GerAcervoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método responsavel por Abrir a tela de Consultar Material
     *
     * @param event
     */
    @FXML
    void consMatBtnAction(ActionEvent event) {
        ConsultarMateriais c = new ConsultarMateriais();
        try {
            c.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(GerAcervoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método responsavel por Abrir a tela de Cadastrar Material
     *
     * @param event
     */
    @FXML
    void cadMatBtnAction(ActionEvent event) {
        CadMaterial cm = new CadMaterial();
        try {
            cm.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(GerAcervoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }

    /**
     * Método responsável por cancelar a ação atual e retornar para a tela
     * anterior
     *
     * @param event
     */
    @FXML
    void backBtn(ActionEvent event) {
        returnPrincipal();
    }

    /**
     * Método responsável por retornar para a tela anterior e fechar a tela
     * atual
     */
    public void returnPrincipal() {
        Principal p = new Principal();
        try {
            p.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(GerAcervoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }

    /**
     * Método responsável por fechar a tela atual
     */
    public void fecha() {
        GerAcervo.getStage().close();
    }

}
