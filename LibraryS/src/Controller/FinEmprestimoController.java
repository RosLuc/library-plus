/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Classes.Livro;
import Classes.Material;
import Classes.Multimidia;
import Emprestimo.Emprestimo;
import LibraryScreens.FinEmprestimo;
import LibraryScreens.GerEmprestimos;
import Notificar.Notificar;
import Pessoa.Pessoa;
import Usuario.Email;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.HibernateException;

/**
 * FXML Controller class
 * @author death
 */
public class FinEmprestimoController implements Initializable{


@FXML
    private Button finEmpBtn;

    @FXML
    private TextField numChamTxt;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button confMaterial;

    @FXML
    private Label confirmacaoLabel;

    @FXML
    private Label erroLabel;

    @FXML
    private Button excluiMaterial;
        
    private Emprestimo Emp = null;
    
    private Material material = null;
    
    /**
     * Método responsavel por cancelar a seleção do material.
     * @param event Evento de botão.
     */
    @FXML
    void excluiMaterialBtnAction(ActionEvent event) {
        Emp = null;
        numChamTxt.setEditable(true);
        confMaterial.setVisible(true);
        excluiMaterial.setVisible(false);
    }

    /**
     * Método responsável por confimar material a ser retirado do empréstimo.
     * @param event Evento do Botão.
     */
    @FXML
    void confMaterialBtnAction(ActionEvent event) {
        try{
            String temp = numChamTxt.getText().trim();
            erroLabel.setVisible(false);
            if (!(temp.equals(""))) {
                int nchamada = Integer.parseUnsignedInt(temp);
                Emp = Emprestimo.buscarEmprestimoDeMaterial(nchamada);
                if (Emp != null) {
                    Set<Material> mat = Emp.getMateriais();
                    for (Material x : mat) {
                        if (x.getNchamada() == nchamada) {
                            mat.remove(x);
                            material = x;
                        }
                    }
                    numChamTxt.setEditable(false);
                    confMaterial.setVisible(false);
                    excluiMaterial.setVisible(true);
                    confirmacaoLabel.setText("Material: " + material.getTitulo() + ", exemplar: " + material.getExemplar()
                            + ". Emprestado a " + Emp.getPessoa().getNome() + " que possui número de inscrição: "
                            + Emp.getPessoa().getCodinsc() + ".");
                    confirmacaoLabel.setVisible(true);
                } else {
                    erroLabel.setText("Nenhum empréstimo realizado com esse material.");
                    erroLabel.setVisible(true);
                }
            } else {
                erroLabel.setText("Insira o número de chamada do material.");
                erroLabel.setVisible(true);
            }    
        } catch (NumberFormatException e) {
            erroLabel.setText("Erro, não é possivel inserir número negativos."
                    + " ou carateres.");
            erroLabel.setVisible(true);
        } catch (HibernateException e) {
            erroLabel.setText("Erro de acesso ao banco de dados.");
            erroLabel.setVisible(true);
        } catch (Exception e) {
            erroLabel.setText("Fatal erro: exception.");
            erroLabel.setVisible(true);
        }      
    }

    /**
     * Método responssável por cancelar a finalização de emprestimo e retornar para a tela
     * antetior.
     */
    @FXML
    void cancelBtnAction(ActionEvent event) {
        GerEmprestimos cm = new GerEmprestimos();
        try {
            cm.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(FinEmprestimoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    /**
     * Método de ação do botão finalizar responsavel por realizar as ultimas verificações e em seguida 
     * finalizar o emprestimo com todas as ações necessarias e atualizar o banco de dados.
     * @param event Evento de botão. 
     */
    @FXML
    void finEmpBtnAction(ActionEvent event) {
        try{
            if (Emp != null) {
                if (Emp.getMateriais().isEmpty()) {
                    Emp.excluirEmprestimo();
                    material.setStatus("Disponivel");
                    if(material instanceof Livro){
                        Livro l = (Livro) material;
                        l.updateMaterial();
                    }else if(material instanceof Multimidia){
                        Multimidia m = (Multimidia) material;
                        m.updateMaterial();
                    }
                    Emp.getPessoa().retirarAtivos();
                    Emp.getPessoa().atualizaPessoa();
                    devolucaoNotificacao(Emp.getPessoa(), material);
                } else {
                    Emp.atualizarEmprestimo();
                    material.setStatus("Disponivel");
                    if(material instanceof Livro){
                        Livro l = (Livro) material;
                        l.updateMaterial();
                    }else if(material instanceof Multimidia){
                        Multimidia m = (Multimidia) material;
                        m.updateMaterial();
                    }
                    Emp.getPessoa().retirarAtivos();
                    Emp.getPessoa().atualizaPessoa();
                    devolucaoNotificacao(Emp.getPessoa(), material);
                }
                retornar();
                alertaComf("EMPRÉSTIMO DO MATERIAL INFORMADO FINALIZADO COM SUCESSO.", "...");
            } else {
                alertaErro("NÃO FOI POSSÍVEL FINALIZAR EMPRÉSTIMO.", "Informe e confirme o número de chamada de um material.");
            }    
        }catch (HibernateException e) {
            alertaErro("NÃO FOI POSSÍVEL FINALIZAR EMPRÉSTIMO.", "Erro de acesso ao banco de dados.");
        }    
    }
    
    /**
     * Método responsável por enviar notificação para pessoa que realizou
     * devolução do material de empréstimo com sucesso.
     * @param p Pessoa que realizou o empréstimo.
     * @param m Material devolvido.
     */
    private void devolucaoNotificacao(Pessoa p, Material m) {
        String remetente = "libraryalory@gmail.com";
        String senh = "libraryalory12345";
        Email.enviarMensagem(remetente, p.getEmail(), "Notificação de confirmação da devolução de material",
                Notificar.notificarDevolucao(p.getNome(), m), 
                Email.conectarConta( Email.conexaoSMTP(p.getEmail()),remetente , senh));
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
    
    public void retornar() {
        GerEmprestimos cm = new GerEmprestimos();
        try {
            cm.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(FinEmprestimoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Método responsável por fechar a tela atual
     */
    public void fecha() {
        FinEmprestimo.getStage().close();
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        erroLabel.setVisible(false);
        confirmacaoLabel.setVisible(false);
        
        excluiMaterial.setVisible(false);
    }

}
