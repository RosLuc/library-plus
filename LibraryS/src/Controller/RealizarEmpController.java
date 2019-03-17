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
import Emprestimo.PossuiEmprestimoDoMaterialException;
import LibraryScreens.GerEmprestimos;
import LibraryScreens.RealizarEmp;
import Pessoa.Pessoa;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.HibernateException;
import Notificar.Notificar;
import Usuario.Email;

/**
 * FXML Controller class
 *
 * @author luand
 */
public class RealizarEmpController implements Initializable {

    @FXML
    private Button cancelBtn;
    
    @FXML
    private Button excluiPessoa;
    
    @FXML
    private Button excluiMaterial1;
    
    @FXML
    private Button excluiMaterial2;

    @FXML
    private Button excluiMaterial3;

    @FXML
    private Button excluiMaterial4;

    @FXML
    private Button excluiMaterial5;
    
    @FXML
    private Button confMaterial1;
    
    @FXML
    private Button confMaterial2;
    
    @FXML
    private Button confMaterial3;

    @FXML
    private Button confMaterial4;

    @FXML
    private Button confMaterial5;
    
    @FXML
    private Button confPessoa;
    
    @FXML
    private Button addMateirial1;
   
    @FXML
    private Button addMateirial2;
    
    @FXML
    private Button addMateirial3;
    
    @FXML
    private Button addMateirial4;
    
    @FXML
    private TextField nInscTxt;
    
    @FXML
    private TextField material1Txt;

    @FXML
    private TextField material2Txt;
    
    @FXML
    private TextField material3Txt;
    
    @FXML
    private TextField material4Txt;
    
    @FXML
    private TextField material5Txt;
    
    @FXML
    private CheckBox selectLivro1;

    @FXML
    private CheckBox selectLivro2;
    
    @FXML
    private CheckBox selectLivro3;

    @FXML
    private CheckBox selectLivro4;
    
    @FXML
    private CheckBox selectLivro5;
    
    @FXML
    private CheckBox selectMult1;
    
    @FXML
    private CheckBox selectMult2;

    @FXML
    private CheckBox selectMult3;
    
    @FXML
    private CheckBox selectMult4;

    @FXML
    private CheckBox selectMult5;

    @FXML
    private Label erroPessoa;

    @FXML
    private Label erroMaterial1;

    @FXML
    private Label erroMaterial2;

    @FXML
    private Label erroMaterial3;

    @FXML
    private Label erroMaterial4;

    @FXML
    private Label erroMaterial5;
    
    @FXML
    private Label checkMaterial3;

    @FXML
    private Label checkMaterial4;

    @FXML
    private Label checkMaterial5;

    @FXML
    private Label checkMaterial1;

    @FXML
    private Label checkMaterial2;
    
    @FXML
    private Label checkPessoa;

    @FXML
    private Label nInsc;
    
    private Pessoa p;
    
    private Material material1 = null, material2= null, 
            material3=null, material4=null, material5=null;

    
    /**
     * Método responsável por fechar a tela atual.
     */
    public void fecha() {
        RealizarEmp.getStage().close();
    }
    
    /**
     * Método responssável por cancelar emprestimo e retornar para a tela
     * antetior.
     */
    @FXML
    void cancelBtnAction(ActionEvent event) {
        returnGerEmprestimo();
    }
    
    /**
     * Método responsável por retornar para a tela anterior e fechar a tela
     * atual.
     */
    public void returnGerEmprestimo(){
        GerEmprestimos g = new GerEmprestimos();
        try {
            g.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(RealizarEmpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }
    
    /**
     * Método responsável por confimar pessoa para o empréstimo.
     * @param event Evento do Botão.
     */
    @FXML
    void confPessoaBtnAction(ActionEvent event) {
        try{
            String temp = nInscTxt.getText().trim();
            if (!(temp.equals(""))) {
                p = Pessoa.buscarPessoa(Integer.parseUnsignedInt(temp));
                if (p != null) {
                    if (p.verificarLimite() > 0) {
                        erroPessoa.setVisible(false);
                        nInscTxt.setVisible(false);
                        confPessoa.setVisible(false);
                        nInsc.setVisible(false);
                        checkPessoa.setText("Nº de Inscrição: " + p.getCodinsc()
                                + "\tNome: " + p.getNome() + "\tEmpréstimos ativos: "
                                + p.getAtivos() + ".");
                        checkPessoa.setVisible(true);
                        excluiPessoa.setVisible(true);
                    } else {
                        erroPessoa.setText("Essa pessoa já execedeu o seu limite "
                                + "de  (cinco) empréstimos .");
                        erroPessoa.setVisible(true);
                    }
                } else {
                    erroPessoa.setText("Pessoa com esse número de inscrição "
                            + "não existe.");
                    erroPessoa.setVisible(true);
                }
            } else {
                erroPessoa.setText("Informe o número de inscrição da pessoa.");
                erroPessoa.setVisible(true);
            }
        }catch(NumberFormatException e){
            erroPessoa.setText("Erro, não é possivel inserir número "
                    + "negativos ou carateres.");
            erroPessoa.setVisible(true);
        }catch (HibernateException e){
            erroPessoa.setText("Erro de acesso ao banco de dados.");
            erroPessoa.setVisible(true);
        }catch (Exception e){
            erroPessoa.setText("Fatal erro: exception.");
            erroPessoa.setVisible(true);
        }
    }

    /**
     * Método responsável por confimar o primeiro material para o empréstimo.
     * @param event Evento do Botão.
     */
    @FXML
    void confMaterial1BtnAction(ActionEvent event) {
        try{
            String temp = material1Txt.getText().trim();
            if (!(temp.equals(""))) {
                if (selectLivro1.isSelected()) {
                    Livro l = new Livro();
                    l.setNchamada(Integer.parseUnsignedInt(temp));
                    l = l.buscarMaterialNC();
                    if (l != null) {
                        if (!(material2 != null && (material2.getNchamada() == l.getNchamada()))
                                && !(material3 != null && (material3.getNchamada() == l.getNchamada()))
                                && !(material4 != null && (material4.getNchamada() == l.getNchamada()))
                                && !(material5 != null && (material5.getNchamada() == l.getNchamada()))) {
                            if (l.getStatus().equals("Disponivel")) {
                                erroMaterial1.setVisible(false);
                                material1Txt.setVisible(false);
                                confMaterial1.setVisible(false);
                                selectLivro1.setVisible(false);
                                selectMult1.setVisible(false);
                                checkMaterial1.setText("Nº de chamada: "
                                        + l.getNchamada() + "\tNome: "
                                        + l.getTitulo() + "\tExemplar: "
                                        + l.getExemplar() + ".");
                                checkMaterial1.setVisible(true);
                                excluiMaterial1.setVisible(true);
                                material1 = l;
                            } else {
                                erroMaterial1.setText("Livro não está disponível.");
                                erroMaterial1.setVisible(true);
                            }
                        }else{
                            erroMaterial1.setText("Esse livro já está selecionado, "
                                    + "não é possivel realizar emprestimo de materiais do mesmo tipo.");
                            erroMaterial1.setVisible(true);
                        }
                    } else {
                        erroMaterial1.setText("Livro com esse número de "
                                + "chamada não existe.");
                        erroMaterial1.setVisible(true);
                    }
                } else if (selectMult1.isSelected()) {
                    Multimidia m = new Multimidia();
                    m.setNchamada(Integer.parseUnsignedInt(temp));
                    m = m.buscarMaterialNC();
                    if (m != null) {
                        if (!(material2 != null && (material2.getNchamada() == m.getNchamada()))
                                && !(material3 != null && (material3.getNchamada() == m.getNchamada()))
                                && !(material4 != null && (material4.getNchamada() == m.getNchamada()))
                                && !(material5 != null && (material5.getNchamada() == m.getNchamada()))) {
                            if (m.getStatus().equals("Disponivel")) {
                                erroMaterial1.setVisible(false);
                                material1Txt.setVisible(false);
                                confMaterial1.setVisible(false);
                                selectLivro1.setVisible(false);
                                selectMult1.setVisible(false);
                                checkMaterial1.setText("Nº de chamada: "
                                        + m.getNchamada() + "\tNome: "
                                        + m.getTitulo() + "\tExemplar: "
                                        + m.getExemplar() + ".");
                                checkMaterial1.setVisible(true);
                                excluiMaterial1.setVisible(true);
                                material1 = m;
                            } else {
                                erroMaterial1.setText("Multimídia não está disponível.");
                                erroMaterial1.setVisible(true);
                            }
                        }else{
                            erroMaterial1.setText("Essa multimídia já está selecionada, "
                                    + "não é possivel realizar emprestimo de materiais do mesmo tipo.");
                            erroMaterial1.setVisible(true);
                        }
                    } else {
                        erroMaterial1.setText("Multimídia com esse número de "
                                + "chamada não existe.");
                        erroMaterial1.setVisible(true);
                    }
                } else {
                    erroMaterial1.setText("Selecione o tipo de material.");
                    erroMaterial1.setVisible(true);
                }
            } else {
                erroMaterial1.setText("Informe o número de chamada do material.");
                erroMaterial1.setVisible(true);
            }
        }catch(NumberFormatException e){
            erroMaterial5.setText("Erro, não é possivel inserir número "
                    + "negativos ou carateres.");
            erroMaterial5.setVisible(true);
        }catch (HibernateException e){
            erroMaterial5.setText("Erro de acesso ao banco de dados.");
            erroMaterial5.setVisible(true);
        }catch (Exception e){
            erroMaterial5.setText("Fatal erro: exception.");
            erroMaterial5.setVisible(true);
        }
    }

    /**
     * Método responsável por exibir os campos para inserir segundo material para o empréstimo.
     * @param event Evento do Botão.
     */
    @FXML
    void addMateirial1BtnAction(ActionEvent event) {
        if(p != null){
            if (p.verificarLimite() > 1) {
                addMateirial1.setVisible(false);
                material2Txt.setVisible(true);
                confMaterial2.setVisible(true);
                addMateirial2.setVisible(true);
                selectLivro2.setVisible(true);
                selectMult2.setVisible(true);
            } else {
                erroMaterial1.setText("O limite de empréstimo da pessoa é "
                        + "de apenas um material.");
                erroMaterial1.setVisible(true);
            } 
        }else{
            erroMaterial1.setText("Insira e confirme alguma pessoa para o "
                    + "empréstimo.");
            erroMaterial1.setVisible(true);
        }
    }

    /**
     * Método responsável por confimar o segundo material para o empréstimo.
     * @param event Evento do Botão.
     */
    @FXML
    void confMaterial2BtnAction(ActionEvent event) {
        try{
            String temp = material2Txt.getText().trim();
            if (!(temp.equals(""))) {
                if (selectLivro2.isSelected()) {
                    Livro l = new Livro();
                    l.setNchamada(Integer.parseUnsignedInt(temp));
                    l = l.buscarMaterialNC();
                    if (l != null) {
                        if (!(material1 != null && (material1.getNchamada() == l.getNchamada()))
                                && !(material3 != null && (material3.getNchamada() == l.getNchamada()))
                                && !(material4 != null && (material4.getNchamada() == l.getNchamada()))
                                && !(material5 != null && (material5.getNchamada() == l.getNchamada()))) {
                            if (l.getStatus().equals("Disponivel")) {
                                erroMaterial2.setVisible(false);
                                material2Txt.setVisible(false);
                                confMaterial2.setVisible(false);
                                selectLivro2.setVisible(false);
                                selectMult2.setVisible(false);
                                checkMaterial2.setText("Nº de chamada: "
                                        + l.getNchamada() + "\tNome: "
                                        + l.getTitulo() + "\tExemplar: "
                                        + l.getExemplar() + ".");
                                checkMaterial2.setVisible(true);
                                excluiMaterial2.setVisible(true);
                                material2 = l;
                            } else {
                                erroMaterial2.setText("Livro não está disponível.");
                                erroMaterial2.setVisible(true);
                            }
                        }else{
                            erroMaterial2.setText("Esse livro já está selecionado, "
                                    + "não é possivel realizar emprestimo de materiais do mesmo tipo.");
                            erroMaterial2.setVisible(true);
                        }
                    } else {
                        erroMaterial2.setText("Livro com esse número "
                                + "de chamada não existe.");
                        erroMaterial2.setVisible(true);
                    }
                } else if (selectMult2.isSelected()) {
                    Multimidia m = new Multimidia();
                    m.setNchamada(Integer.parseUnsignedInt(temp));
                    m = m.buscarMaterialNC();
                    if (m != null) {
                        if (!(material1 != null && (material1.getNchamada() == m.getNchamada()))
                                && !(material3 != null && (material3.getNchamada() == m.getNchamada()))
                                && !(material4 != null && (material4.getNchamada() == m.getNchamada()))
                                && !(material5 != null && (material5.getNchamada() == m.getNchamada()))) {
                            if (m.getStatus().equals("Disponivel")) {
                                erroMaterial2.setVisible(false);
                                material2Txt.setVisible(false);
                                confMaterial2.setVisible(false);
                                selectLivro2.setVisible(false);
                                selectMult2.setVisible(false);
                                checkMaterial2.setText("Nº de chamada: "
                                        + m.getNchamada() + "\tNome: "
                                        + m.getTitulo() + "\tExemplar: "
                                        + m.getExemplar() + ".");
                                checkMaterial2.setVisible(true);
                                excluiMaterial2.setVisible(true);
                                material2 = m;
                            } else {
                                erroMaterial2.setText("Multímidia não está disponível.");
                                erroMaterial2.setVisible(true);
                            }
                        }else{
                            erroMaterial2.setText("Essa Multimídia já está selecionada, "
                                    + "não é possivel realizar emprestimo de materiais do mesmo tipo.");
                            erroMaterial2.setVisible(true);
                        }
                    } else {
                        erroMaterial2.setText("Multimidia com esse número de "
                                + "chamada não existe.");
                        erroMaterial2.setVisible(true);
                    }
                } else {
                    erroMaterial2.setText("Selecione o tipo de material.");
                    erroMaterial2.setVisible(true);
                }
            } else {
                erroMaterial2.setText("Informe o número de chamada do "
                        + "material.");
                erroMaterial2.setVisible(true);
            }            
        }catch(NumberFormatException e){
            erroMaterial5.setText("Erro, não é possivel inserir número "
                    + "negativos ou carateres.");
            erroMaterial5.setVisible(true);
        }catch (HibernateException e){
            erroMaterial5.setText("Erro de acesso ao banco de dados.");
            erroMaterial5.setVisible(true);
        }catch (Exception e){
            erroMaterial5.setText("Fatal erro: exception.");
            erroMaterial5.setVisible(true);
        }
    }

    /**
     * Método responsável por exibir os campos para inserir terceiro material para o empréstimo.
     * @param event Evento do Botão.
     */
    @FXML
    void addMateirial2BtnAction(ActionEvent event) {
        if (p != null) {
            if (p.verificarLimite() > 2) {
                addMateirial2.setVisible(false);
                material3Txt.setVisible(true);
                confMaterial3.setVisible(true);
                addMateirial3.setVisible(true);
                selectLivro3.setVisible(true);
                selectMult3.setVisible(true);
            } else {
                erroMaterial2.setText("O limite de empréstimo da pessoa é de "
                        + "apenas dois materiais.");
                erroMaterial2.setVisible(true);
            }
        } else {
            erroMaterial2.setText("Insira e confirme alguma pessoa para "
                    + "o empréstimo.");
            erroMaterial2.setVisible(true);
        }
    }

    /**
     * Método responsável por confimar o terceiro material para o empréstimo.
     * @param event - Evento do Botão
     */
    @FXML
    void confMaterial3BtnAction(ActionEvent event) {
        try {
            String temp = material3Txt.getText().trim();
            if (!(temp.equals(""))) {
                if (selectLivro3.isSelected()) {
                    Livro l = new Livro();
                    l.setNchamada(Integer.parseUnsignedInt(temp));
                    l = l.buscarMaterialNC();
                    if (l != null) {
                        if (!(material1 != null && (material1.getNchamada() == l.getNchamada()))
                                && !(material2 != null && (material2.getNchamada() == l.getNchamada()))
                                && !(material4 != null && (material4.getNchamada() == l.getNchamada()))
                                && !(material5 != null && (material5.getNchamada() == l.getNchamada()))) {
                            if (l.getStatus().equals("Disponivel")) {
                                erroMaterial3.setVisible(false);
                                material3Txt.setVisible(false);
                                confMaterial3.setVisible(false);
                                selectLivro3.setVisible(false);
                                selectMult3.setVisible(false);
                                checkMaterial3.setText("Nº de chamada: "
                                        + l.getNchamada() + "\tNome: "
                                        + l.getTitulo() + "\tExemplar: "
                                        + l.getExemplar() + ".");
                                checkMaterial3.setVisible(true);
                                excluiMaterial3.setVisible(true);
                                material3 = l;
                            } else {
                                erroMaterial3.setText("Livro não está disponível.");
                                erroMaterial3.setVisible(true);
                            }
                        }else{
                            erroMaterial3.setText("Esse livro já está selecionado, "
                                    + "não é possivel realizar emprestimo de materiais do mesmo tipo.");
                            erroMaterial3.setVisible(true);
                        }
                    } else {
                        erroMaterial3.setText("Livro com esse número de "
                                + "chamada não existe.");
                        erroMaterial3.setVisible(true);
                    }
                } else if (selectMult3.isSelected()) {
                    Multimidia m = new Multimidia();
                    m.setNchamada(Integer.parseUnsignedInt(temp));
                    m = m.buscarMaterialNC();
                    if (m != null) {
                        if (!(material1 != null && (material1.getNchamada() == m.getNchamada()))
                                && !(material2 != null && (material2.getNchamada() == m.getNchamada()))
                                && !(material4 != null && (material4.getNchamada() == m.getNchamada()))
                                && !(material5 != null && (material5.getNchamada() == m.getNchamada()))) {
                            if (m.getStatus().equals("Disponivel")) {
                                erroMaterial3.setVisible(false);
                                material3Txt.setVisible(false);
                                confMaterial3.setVisible(false);
                                selectLivro3.setVisible(false);
                                selectMult3.setVisible(false);
                                checkMaterial3.setText("Nº de chamada: "
                                        + m.getNchamada() + "\tNome: "
                                        + m.getTitulo() + "\tExemplar: "
                                        + m.getExemplar() + ".");
                                checkMaterial3.setVisible(true);
                                excluiMaterial3.setVisible(true);
                                material3 = m;
                            } else {
                                erroMaterial3.setText("Multimídia não está disponível.");
                                erroMaterial3.setVisible(true);
                            }
                        }else{
                            erroMaterial3.setText("Essa multimídia já está selecionada, "
                                    + "não é possivel realizar emprestimo de materiais do mesmo tipo.");
                            erroMaterial3.setVisible(true);
                        }
                    } else {
                        erroMaterial3.setText("Multimidia com esse número de "
                                + "chamada não existe.");
                        erroMaterial3.setVisible(true);
                    }
                } else {
                    erroMaterial3.setText("Selecione o tipo de material.");
                    erroMaterial3.setVisible(true);
                }
            } else {
                erroMaterial3.setText("Informe o número de chamada do "
                        + "material.");
                erroMaterial3.setVisible(true);
            }
        }catch (NumberFormatException e) {
            erroMaterial5.setText("Erro, não é possivel inserir número "
                    + "negativos ou carateres.");
            erroMaterial5.setVisible(true);
        } catch (HibernateException e) {
            erroMaterial5.setText("Erro de acesso ao banco de dados.");
            erroMaterial5.setVisible(true);
        } catch (Exception e) {
            erroMaterial5.setText("Fatal erro: exception.");
            erroMaterial5.setVisible(true);
        }
    }
    
    /**
     * Método responsável por exibir os campos para inserir quarto material para o empréstimo.
     * @param event Evento do Botão.
     */
    @FXML
    void addMateirial3BtnAction(ActionEvent event) {
        if (p != null) {
            if(p.verificarLimite() > 3){
                addMateirial3.setVisible(false);
                material4Txt.setVisible(true);
                confMaterial4.setVisible(true);
                addMateirial4.setVisible(true);
                selectLivro4.setVisible(true);
                selectMult4.setVisible(true);
            }else{
                erroMaterial3.setText("O limite de empréstimo da pessoa é de "
                        + "apenas três materiais.");
                erroMaterial3.setVisible(true);
            }
        } else {
            erroMaterial3.setText("Insira e confirme alguma pessoa para o "
                    + "empréstimo.");
            erroMaterial3.setVisible(true);
        }
    }

    /**
     * Método responsável por confimar o quarto material para o empréstimo.
     * @param event Evento do Botão
     */
    @FXML
    void confMaterial4BtnAction(ActionEvent event) {
        try {
            String temp = material4Txt.getText().trim();
            if (!(temp.equals(""))) {
                if (selectLivro4.isSelected()) {
                    Livro l = new Livro();
                    l.setNchamada(Integer.parseUnsignedInt(temp));
                    l = l.buscarMaterialNC();
                    if (l != null) {
                        if (!(material1 != null && (material1.getNchamada() == l.getNchamada()))
                                && !(material2 != null && (material2.getNchamada() == l.getNchamada()))
                                && !(material3 != null && (material3.getNchamada() == l.getNchamada()))
                                && !(material5 != null && (material5.getNchamada() == l.getNchamada()))) {
                            if (l.getStatus().equals("Disponivel")) {
                                erroMaterial4.setVisible(false);
                                material4Txt.setVisible(false);
                                confMaterial4.setVisible(false);
                                selectLivro4.setVisible(false);
                                selectMult4.setVisible(false);
                                checkMaterial4.setText("Nº de chamada: "
                                        + l.getNchamada() + "\tNome: "
                                        + l.getTitulo() + "\tExemplar: "
                                        + l.getExemplar() + ".");
                                checkMaterial4.setVisible(true);
                                excluiMaterial4.setVisible(true);
                                material4 = l;
                            } else {
                                erroMaterial4.setText("Livro não está disponível.");
                                erroMaterial4.setVisible(true);
                            }
                        }else{
                            erroMaterial4.setText("Esse livro já está selecionado, "
                                    + "não é possivel realizar emprestimo de materiais do mesmo tipo.");
                            erroMaterial4.setVisible(true);
                        }
                    } else {
                        erroMaterial4.setText("Livro com esse número de "
                                + "chamada não existe.");
                        erroMaterial4.setVisible(true);
                    }
                } else if (selectMult4.isSelected()) {
                    Multimidia m = new Multimidia();
                    m.setNchamada(Integer.parseUnsignedInt(temp));
                    m = m.buscarMaterialNC();
                    if (m != null) {
                        if (!(material1 != null && (material1.getNchamada() == m.getNchamada()))
                                && !(material2 != null && (material2.getNchamada() == m.getNchamada()))
                                && !(material3 != null && (material3.getNchamada() == m.getNchamada()))
                                && !(material5 != null && (material5.getNchamada() == m.getNchamada()))) {
                            if (m.getStatus().equals("Disponivel")) {
                                erroMaterial4.setVisible(false);
                                material4Txt.setVisible(false);
                                confMaterial4.setVisible(false);
                                selectLivro4.setVisible(false);
                                selectMult4.setVisible(false);
                                checkMaterial4.setText("Nº de chamada: "
                                        + m.getNchamada() + "\tNome: "
                                        + m.getTitulo() + "\tExemplar: "
                                        + m.getExemplar() + ".");
                                checkMaterial4.setVisible(true);
                                excluiMaterial4.setVisible(true);
                                material4 = m;
                            } else {
                                erroMaterial4.setText("Multimídia não está disponível.");
                                erroMaterial4.setVisible(true);
                            }
                        }else{
                            erroMaterial4.setText("Essa multimídia já está selecionada, "
                                    + "não é possivel realizar emprestimo de materiais do mesmo tipo.");
                            erroMaterial4.setVisible(true);
                        }
                    } else {
                        erroMaterial4.setText("Multimidia com esse número "
                                + "de chamada não existe.");
                        erroMaterial4.setVisible(true);
                    }
                } else {
                    erroMaterial4.setText("Selecione o tipo de material.");
                    erroMaterial4.setVisible(true);
                }
            } else {
                erroMaterial4.setText("Informe o número de chamada do "
                        + "material.");
                erroMaterial4.setVisible(true);
            }
        }catch (NumberFormatException e) {
            erroMaterial5.setText("Erro, não é possivel inserir número "
                    + "negativos ou carateres.");
            erroMaterial5.setVisible(true);
        } catch (HibernateException e) {
            erroMaterial5.setText("Erro de acesso ao banco de dados.");
            erroMaterial5.setVisible(true);
        } catch (Exception e) {
            erroMaterial5.setText("Fatal erro: exception.");
            erroMaterial5.setVisible(true);
        }
    }

    /**
     * Método responsável por exibir os campos para inserir quinto material para o empréstimo.
     * @param event - Evento do Botão.
     */
    @FXML
    void addMateirial4BtnAction(ActionEvent event) {
        if (p != null) {
            if(p.verificarLimite() > 4){
                material5Txt.setVisible(true);
                confMaterial5.setVisible(true);
                selectLivro5.setVisible(true);
                selectMult5.setVisible(true);
                addMateirial4.setVisible(false);
            }else{
                erroMaterial4.setText("O limite de empréstimo da pessoa é de "
                        + "apenas quatro materiais.");
                erroMaterial4.setVisible(true);
            }
        } else {
            erroMaterial4.setText("Insira e confirme alguma pessoa para o "
                    + "empréstimo.");
            erroMaterial4.setVisible(true);
        }
    }
    
    /**
     * Método responsável por confimar o quinto material para o empréstimo.
     * @param event Evento do Botão
     */
    @FXML
    void confMaterial5BtnAction(ActionEvent event) {
        try {
            String temp = material5Txt.getText().trim();
            if (!(temp.equals(""))) {
                if (selectLivro2.isSelected()) {
                    Livro l = new Livro();
                    l.setNchamada(Integer.parseUnsignedInt(temp));
                    l = l.buscarMaterialNC();
                    if (l != null) {
                        if (!(material1 != null && (material1.getNchamada() == l.getNchamada()))
                                && !(material2 != null && (material2.getNchamada() == l.getNchamada()))
                                && !(material3 != null && (material3.getNchamada() == l.getNchamada()))
                                && !(material4 != null && (material4.getNchamada() == l.getNchamada()))) {
                            if (l.getStatus().equals("Disponivel")) {
                                erroMaterial5.setVisible(false);
                                material5Txt.setVisible(false);
                                confMaterial5.setVisible(false);
                                selectLivro5.setVisible(false);
                                selectMult5.setVisible(false);
                                checkMaterial5.setText("Nº de chamada: "
                                        + l.getNchamada() + "\tNome: "
                                        + l.getTitulo() + "\tExemplar: "
                                        + l.getExemplar() + ".");
                                checkMaterial5.setVisible(true);
                                excluiMaterial5.setVisible(true);
                                material5 = l;
                            } else {
                                erroMaterial5.setText("Livro não está disponível.");
                                erroMaterial5.setVisible(true);
                                excluiMaterial5BtnAction(new ActionEvent());
                            }
                        }else{
                            erroMaterial5.setText("Esse livro já está selecionado, "
                                    + "não é possivel realizar emprestimo de materiais do mesmo tipo.");
                            erroMaterial5.setVisible(true);
                        }
                    } else {
                        erroMaterial5.setText("Livro com esse número de "
                                + "chamada não existe.");
                        erroMaterial5.setVisible(true);
                    }
                } else if (selectMult5.isSelected()) {
                    Multimidia m = new Multimidia();
                    m.setNchamada(Integer.parseUnsignedInt(temp));
                    m = m.buscarMaterialNC();
                    if (m != null) {
                        if (!(material1 != null && (material1.getNchamada() == m.getNchamada()))
                                && !(material2 != null && (material2.getNchamada() == m.getNchamada()))
                                && !(material3 != null && (material3.getNchamada() == m.getNchamada()))
                                && !(material4 != null && (material4.getNchamada() == m.getNchamada()))) {
                            if (m.getStatus().equals("Disponivel")) {
                                erroMaterial5.setVisible(false);
                                material5Txt.setVisible(false);
                                confMaterial5.setVisible(false);
                                selectLivro5.setVisible(false);
                                selectMult5.setVisible(false);
                                checkMaterial5.setText("Nº de chamada: "
                                        + m.getNchamada() + "\tNome: "
                                        + m.getTitulo() + "\tExemplar: "
                                        + m.getExemplar() + ".");
                                checkMaterial5.setVisible(true);
                                excluiMaterial5.setVisible(true);
                                material5 = m;
                            } else {
                                erroMaterial5.setText("Multimídia não está disponível.");
                                erroMaterial5.setVisible(true);
                                excluiMaterial5BtnAction(new ActionEvent());
                            }
                        }else{
                            erroMaterial5.setText("Essa multimídia já está selecionada, "
                                    + "não é possivel realizar emprestimo de materiais do mesmo tipo.");
                            erroMaterial5.setVisible(true);
                        }
                    } else {
                        erroMaterial5.setText("Multimidia com esse número de "
                                + "chamada não existe.");
                        erroMaterial5.setVisible(true);
                    }
                } else {
                    erroMaterial5.setText("Selecione o tipo de material.");
                    erroMaterial5.setVisible(true);
                }
            } else {
                erroMaterial5.setText("Informe o número de chamada do material.");
                erroMaterial5.setVisible(true);
            }
        } catch (NumberFormatException e) {
            erroMaterial5.setText("Erro, não é possivel inserir número negativos."
                    + " ou carateres.");
            erroMaterial5.setVisible(true);
        } catch (HibernateException e) {
            erroMaterial5.setText("Erro de acesso ao banco de dados.");
            erroMaterial5.setVisible(true);
        } catch (Exception e) {
            erroMaterial5.setText("Fatal erro: exception.");
            erroMaterial5.setVisible(true);
        }
    }
    
    /**
     * Método responsavel por executar a ação de bloquear a checkBox contraria 
     * após selecionar chechBox de livro referente ao primeiro material. 
     * @param event Evento de seleção do checkBox.
     */
    @FXML
    void selectLivro1BoxAction(ActionEvent event) {
        selectMult1.setIndeterminate(true);
    }
    
    /**
     * Método responsavel por executar a ação de bloquear a checkBox contraria
     * após selecionar chechBox de multimídia referente ao primeiro material. 
     * @param event Evento de seleção do checkBox.
     */
    @FXML
    void selectMult1BoxAction(ActionEvent event) {
        selectLivro1.setIndeterminate(true);
    }
    
    /**
     * Método responsavel por executar a ação de bloquear a checkBox contraria
     * após selecionar chechBox de livro referente ao segundo material.
     * @param event - Evento de seleção do checkBox.
     */
    @FXML
    void selectLivro2BoxAction(ActionEvent event) {
        selectMult2.setIndeterminate(true);
    }
    
    /**
     * Método responsavel por executar a ação de bloquear a checkBox contraria
     * após selecionar chechBox de multimídia referente ao segundo material. 
     * @param event Evento de seleção do checkBox.
     */
    @FXML 
    void selectMult2BoxAction(ActionEvent event) {
        selectLivro2.setIndeterminate(true);
    }
    
    /**
     * Método responsavel por executar a ação de bloquear a checkBox contraria
     * após selecionar chechBox de livro referente ao terceiro material. 
     * @param event Evento de seleção do checkBox.
     */
    @FXML
    void selectLivro3BoxAction(ActionEvent event) {
        selectMult3.setIndeterminate(true);
    }
    
    /**
     * Método responsavel por executar a ação de bloquear a checkBox contraria
     * após selecionar chechBox de multimídia referente ao terceiro material. 
     * @param event Evento de seleção do checkBox.
     */
    @FXML
    void selectMult3BoxAction(ActionEvent event) {
        selectLivro3.setIndeterminate(true);
    }
    
    /**
     * Método responsavel por executar a ação de bloquear a checkBox contraria
     * após selecionar chechBox de livro referente ao quarto material. 
     * @param event Evento de seleção do checkBox.
     */
    @FXML
    void selectLivro4BoxAction(ActionEvent event) {
        selectMult4.setIndeterminate(true);
    }

    /**
     * Método responsavel por executar a ação de bloquear a checkBox contraria
     * após selecionar chechBox de multimídia referente ao quarto material. 
     * @param event Evento de seleção do checkBox.
     */
    @FXML
    void selectMult4BoxAction(ActionEvent event) {
        selectLivro4.setIndeterminate(true);
    }
    
    /**
     * Método responsavel por executar a ação de bloquear a checkBox contraria
     * após selecionar chechBox de livro referente ao quinto material. 
     * @param event Evento de seleção do checkBox.
     */
    @FXML
    void selectLivro5BoxAction(ActionEvent event) {
        selectMult5.setIndeterminate(true);
    }

    /**
     * Método responsavel por executar a ação de bloquear a checkBox contraria
     * após selecionar chechBox de multimídia referente ao quinto material. 
     * @param event Evento de seleção do checkBox.
     */
    @FXML
    void selectMult5BoxAction(ActionEvent event) {
        selectLivro5.setIndeterminate(true);
    }

    /**
     * Método responsavel por cancelar a seleção do primeiro Pessoa.
     * @param event Evento de botão.
     */
    @FXML
    void excluiPessoaBtnAction(ActionEvent event) {
        checkPessoa.setVisible(false);
        excluiPessoa.setVisible(false);
        nInscTxt.setVisible(true);
        confPessoa.setVisible(true);
        nInsc.setVisible(true);
        p=null;
    }
    
    /**
     * Método responsavel por cancelar a seleção do primeiro material.
     * @param event Evento de botão.
     */
    @FXML
    void excluiMaterial1BtnAction(ActionEvent event) {
        checkMaterial1.setVisible(false);
        excluiMaterial1.setVisible(false);
        material1Txt.setVisible(true);
        confMaterial1.setVisible(true);
        addMateirial1.setVisible(true);
        selectLivro1.setVisible(true);
        selectMult1.setVisible(true);
        material1 = null;
    }

    /**
     * Método responsavel por cancelar a seleção do segundo material.
     * @param event Evento de botão.
     */    
    @FXML
    void excluiMaterial2BtnAction(ActionEvent event) {
        checkMaterial2.setVisible(false);
        excluiMaterial2.setVisible(false);
        material2Txt.setVisible(true);
        confMaterial2.setVisible(true);
        addMateirial2.setVisible(true);
        selectLivro2.setVisible(true);
        selectMult2.setVisible(true);
        material2 = null;
    }

    /**
     * Método responsavel por cancelar a seleção do terceiro material.
     * @param event Evento de botão.
     */    
    @FXML
    void excluiMaterial3BtnAction(ActionEvent event) {
        checkMaterial3.setVisible(false);
        excluiMaterial3.setVisible(false);
        material3Txt.setVisible(true);
        confMaterial3.setVisible(true);
        addMateirial3.setVisible(true);
        selectLivro3.setVisible(true);
        selectMult3.setVisible(true);
        material3 = null;
    }
    
    /**
     * Método responsavel por cancelar a seleção do quarto material.
     * @param event Evento de botão.
     */
    @FXML
    void excluiMaterial4BtnAction(ActionEvent event) {
        checkMaterial4.setVisible(false);
        excluiMaterial4.setVisible(false);
        material4Txt.setVisible(true);
        confMaterial4.setVisible(true);
        addMateirial4.setVisible(true);
        selectLivro4.setVisible(true);
        selectMult4.setVisible(true);
        material4 = null;
    }

    /**
     * Método responsavel por cancelar a seleção do quinto material.
     * @param event Evento de botão.
     */
    @FXML
    void excluiMaterial5BtnAction(ActionEvent event) {
        checkMaterial5.setVisible(false);
        excluiMaterial5.setVisible(false);
        material5Txt.setVisible(true);
        confMaterial5.setVisible(true);
        selectLivro5.setVisible(true);
        selectMult5.setVisible(true);
        material5 = null;
    }
    
    /**
     * Método de ação do botão finalizar responsavel por realizar as ultimas verificações e em seguida 
     * realizar o emprestimo com todas as ações necessarias e atualizar o banco de dados.
     * @param event Evento de botão. 
     */
    @FXML
    void finalizarBtnAction(ActionEvent event) {
        Material verifica=null;
        try{
            if( p!=null){
                if( material1!=null || material2!=null || material3!=null ||
                        material4!=null ||material5!=null){
                    List<Emprestimo> listEmp = Emprestimo.buscarEmprestimoDePessoa(p.getCodinsc());
                    for (Emprestimo x : listEmp) {
                        Set<Material> setMat = x.getMateriais();
                        for (Material y : setMat) {
                            if (material1 != null && 
                                    (y.getTitulo().equalsIgnoreCase(material1.getTitulo()))){
                                verifica = material1;
                                throw new PossuiEmprestimoDoMaterialException();
                            } else if (material2 != null && 
                                    (y.getTitulo().equalsIgnoreCase(material2.getTitulo()))) {
                                verifica = material2;
                                throw new PossuiEmprestimoDoMaterialException();
                            } else if (material3 != null && 
                                    (y.getTitulo().equalsIgnoreCase(material3.getTitulo()))) {
                                verifica = material3;
                                throw new PossuiEmprestimoDoMaterialException();
                            } else if (material4 != null && 
                                    (y.getTitulo().equalsIgnoreCase(material4.getTitulo()))) {
                                verifica = material4;
                                throw new PossuiEmprestimoDoMaterialException();
                            } else if (material5 != null && 
                                    (y.getTitulo().equalsIgnoreCase(material5.getTitulo()))) {
                                verifica = material5;
                                throw new PossuiEmprestimoDoMaterialException();
                            }
                        }
                    }
                    Emprestimo emp = new Emprestimo();
                    emp.setPessoa(p);
                    emp.setUsercode(11111);
                    GregorianCalendar c = new GregorianCalendar();
                    emp.setDataemp(c.getTime());
                    c.add(GregorianCalendar.DAY_OF_WEEK, 7);
                    emp.setDatadev(c.getTime());
                    emp.setStatus("Ativo");
                    Set<Material> m = new HashSet<>();
                    int i = 0;
                    if (material1 != null) {
                        m.add(material1);
                        i += 1;
                    }
                    if (material2 != null) {
                        m.add(material2);
                        i += 1;
                    }
                    if (material3 != null) {
                        m.add(material3);
                        i += 1;
                    }
                    if (material4 != null) {
                        m.add(material4);
                        i += 1;
                    }
                    if (material5 != null) {
                        m.add(material5);
                        i += 1;
                    }
                    emp.setMateriais(m);
                    emp.salvarEmprestimo();
                    for(Material x : m){
                        x.setStatus("Emprestado");
                        x.updateMaterial();
                    }
                    p.acrescentarAtivos(i);
                    p.acrescentarTotal(i);
                    p.atualizaPessoa();
                    emprestimoNotificacao(p, emp);
                    returnGerEmprestimo();
                    alertaComf("EMPRESTIMO REALIZADO COM SUCESSO.", "");
                }else{
                    erroMaterial5.setText("Insira e confirme algum material "
                            + "para o empréstimo.");
                    erroMaterial5.setVisible(true);
                }            
            }else{
                    erroMaterial5.setText("Insira e confirme alguma pessoa "
                            + "para o empréstimo.");
                    erroMaterial5.setVisible(true);
            }
        }catch (PossuiEmprestimoDoMaterialException e){
            erroMaterial5.setText("Essa pessoa já possui emprestimo de um exemplar do " + "material com número de chamada: "
                    + verifica.getNchamada() + ".");
            erroMaterial5.setVisible(true);
        }catch (ArrayIndexOutOfBoundsException e) {
            erroMaterial5.setText("Erro IIV - 1.");
            erroMaterial5.setVisible(true);
        } catch (HibernateException e) {
            erroMaterial5.setText("Erro de acesso ao banco de dados.");
            erroMaterial5.setVisible(true);
        } catch (Exception e) {
            erroMaterial5.setText("Fatal erro: exception.");
            erroMaterial5.setVisible(true);
        }
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
    
    /**
     * Método responsável por enviar notificação para pessoa que realizou empréstimo com sucesso.
     * @param p Pessoa que realizou o empréstimo.
     * @param emp Empréstimo realizado.
     */
    private void emprestimoNotificacao(Pessoa p, Emprestimo emp) {
        String remetente = "libraryalory@gmail.com";
        String senh = "libraryalory12345";
        Email.enviarMensagem(remetente, p.getEmail(), "Email de confirmação da realização de emprestimo",
                Notificar.notificarEmprestimo(p.getNome(), emp), 
                Email.conectarConta( Email.conexaoSMTP(p.getEmail()),remetente , senh));
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        checkPessoa.setVisible(false);
        erroPessoa.setVisible(false);
        
        checkMaterial1.setVisible(false);
        checkMaterial2.setVisible(false);
        checkMaterial3.setVisible(false);
        checkMaterial4.setVisible(false);
        checkMaterial5.setVisible(false);
        erroMaterial1.setVisible(false);
        erroMaterial2.setVisible(false);
        erroMaterial3.setVisible(false);
        erroMaterial4.setVisible(false);
        erroMaterial5.setVisible(false);
        
        excluiPessoa.setVisible(false);
        excluiMaterial1.setVisible(false);
        excluiMaterial2.setVisible(false);
        excluiMaterial3.setVisible(false);
        excluiMaterial4.setVisible(false);
        excluiMaterial5.setVisible(false);
        
        material2Txt.setVisible(false);
        material3Txt.setVisible(false);
        material4Txt.setVisible(false);
        material5Txt.setVisible(false);
        confMaterial2.setVisible(false);
        confMaterial3.setVisible(false);
        confMaterial4.setVisible(false);
        confMaterial5.setVisible(false);
        addMateirial2.setVisible(false);
        addMateirial3.setVisible(false);
        addMateirial4.setVisible(false);
        
        selectLivro2.setVisible(false);
        selectLivro3.setVisible(false);
        selectLivro4.setVisible(false);
        selectLivro5.setVisible(false);
        selectMult2.setVisible(false);
        selectMult3.setVisible(false);
        selectMult4.setVisible(false);
        selectMult5.setVisible(false);
    }    
    
}
