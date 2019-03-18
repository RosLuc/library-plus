/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryScreens.CadPessoa;
import LibraryScreens.GerPessoas;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Pessoa.Pessoa;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javax.swing.text.MaskFormatter;
import org.hibernate.HibernateException;

/**
 * FXML Controller class
 *
 * @author death
 */
public class CadPessoaController implements Initializable {

    @FXML
    private Button cancelBtn;
    @FXML
    private TextField telTxt;
    @FXML
    private TextField enderecoTxt;
    @FXML
    private TextField emailTxt;
    @FXML
    private Button addBtn;
    @FXML
    private TextField serieTxt;
    @FXML
    private TextField nomeTxt;
    @FXML
    private TextField cepTxt;
    @FXML
    private TextField turnoTxt;
    @FXML
    private TextField categoriaTxt;
    @FXML
    private TextField numeroTxt;
    @FXML
    private TextField cidadeTxt;
    @FXML
    private TextField turmaTxt;
    @FXML
    private TextField bairroTxt;
    @FXML
    private TextField estadoTxt;
    @FXML
    private ComboBox<String> categoriaBox;
    @FXML
    private Label serieLabel;
    @FXML
    private Label turmaLabel;
    @FXML
    private Label turnoLabel;
    
    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
    
    @FXML
    void contatoOnKeyReleased(KeyEvent event) {
        String mask = "(##)#####-####";
        String caracteres = "0123456789";
        formatter(telTxt, caracteres, mask);
    }
    
    @FXML
    void cepOnKeyReleased(KeyEvent event) {
        String mask = "#####-###";
        String caracteres = "0123456789";
        formatter(cepTxt, caracteres, mask);
    }
    
    /**
     * Método responssável por cancelar a ação atual e retornar para a tela
     * antetior
     *
     * @param event
     */
    @FXML
    void cancelBtnAction(ActionEvent event) {
        returnPes();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        String[] lista = {"Funcionario", "Professor", "Aluno"};
        categoriaBox.getItems().addAll(lista);
        categoriaBox.setOnAction((ActionEvent e) -> {
            if(categoriaBox.getValue().equals("Funcionario") || 
                    categoriaBox.getValue().equals("Professor")){
                turnoTxt.setEditable(false);
                turnoTxt.setText("-");
                turmaTxt.setEditable(false);
                turmaTxt.setText("-");
                serieTxt.setEditable(false);
                serieTxt.setText("-");
            } else if(categoriaBox.getValue().equals("Aluno")) {
                turnoTxt.setEditable(true);
                turnoTxt.setText("");
                turmaTxt.setEditable(true);
                turmaTxt.setText("");
                serieTxt.setEditable(true);
                serieTxt.setText("");
            }
        });
    }
    
    public void formatter(TextField tf, String CaracteresValidos, String mask) {
        MaskFormatter mf = new MaskFormatter();
        try {
            mf.setMask(mask);
        } catch (java.text.ParseException ex) {
            System.out.println(ex.getMessage());
        }

        mf.setValidCharacters(CaracteresValidos);
        mf.setValueContainsLiteralCharacters(false);
        String text = tf.getText().replaceAll("[\\W]", "");
        
        boolean repetir = true;
        while (repetir) {

            char ultimoCaractere;

            if (text.equals("")) {
                break;
            } else {
                ultimoCaractere = text.charAt(text.length() - 1);
            }

            try {
                text = mf.valueToString(text);
                repetir = false;
            } catch (java.text.ParseException ex) {
                text = text.replace(ultimoCaractere + "", "");
                repetir = true;
            }

        }

        tf.setText(text);

        if (!text.equals("")) {
            for (int i = 0; tf.getText().charAt(i) != ' ' && i < tf.getLength() - 1; i++) {
                tf.forward();
            }
        }
    }

    /**
     * Método responsável por cadastrar uma pessoa
     *
     * @param event
     */
    @FXML
    void addBtnAction(ActionEvent event) {
        try {
            Pessoa p = capturaPessoa();
            if(p != null){
                if(p.salvarPessoa()){
                    returnPes();
                    confirma();
                }else erro("Falha ao cadastrar pessoa.");
            }
        }catch (NumberFormatException e){
            erro("ATENÇÃO: Campos númericos não peenchido ou foi adicionado simbolos como: [,./ -] e outro. Adicione apenas inteiros positivos.");
        }catch (HibernateException e){
            erro(e.toString());
        }catch (Exception e){
            erro("Erro.");
        }
    }
    
    private Pessoa capturaPessoa(){
        String temp;
        Pessoa p = new Pessoa();
        p.setUsercode(11111);
        p.setAtivos(0);
        p.setTotal_emprestimos(0);
        
        temp = categoriaBox.getValue();
        if(temp.equals("Aluno") || temp.equals("Professor") || temp.equals("Funcionario")) p.setCategoria(temp);
        else{
            erro("ATENÇÃO CAMPO OBRIGATORIO: Categoria não está preenchido.");
            return null;
        }
        
        temp = nomeTxt.getText();
        if(!(temp.trim().equals(""))) p.setNome(temp);
        else{
            erro("ATENÇÃO CAMPO OBRIGATORIO: Nome não está preenchido.");
            return null;
        }
        
        temp = emailTxt.getText();
        if(!(temp.trim().equals(""))){
            if(validarEmail(temp)) p.setEmail(temp);
            else erro("ATENÇÃO EMAIL INVÁLIDO: Verifique o email");
        }
        else{
            erro("ATENÇÃO CAMPO OBRIGATORIO: Email não está preenchido.");
            return null;
        }
        
        temp = categoriaBox.getValue();
        if(temp.equals("Aluno")){
            String tp;
            tp = turnoTxt.getText();
            if(!(tp.trim().equals(""))) p.setTurno(tp);
            else{
                erro("ATENÇÃO CAMPO OBRIGATORIO: Turno não está preenchido.");
                return null;
            }
            
            tp = turmaTxt.getText();
            if(!(tp.trim().equals(""))) p.setTurma(tp);
            else{
                erro("ATENÇÃO CAMPO OBRIGATORIO: Turma não está preenchido.");
                return null;
            }
            
            tp = serieTxt.getText();
            if(!(tp.trim().equals(""))) p.setSerie(tp);
            else{
                erro("ATENÇÃO CAMPO OBRIGATORIO: Série não está preenchido.");
                return null;
            }
        }else{
            p.setTurno(null);
            p.setTurma(null);
            p.setSerie(null);
        }
        
        temp = telTxt.getText();
        if(!(temp.trim().equals(""))) p.setContato(temp);
        else{
            erro("ATENÇÃO CAMPO OBRIGATORIO: Contato não está preenchido.");
            return null;
        }
        
        temp = cepTxt.getText();
        if(!(temp.trim().equals(""))) p.setCep(temp);
        else{
            erro("ATENÇÃO CAMPO OBRIGATORIO: CEP não está preenchido.");
            return null;
        }
        
        temp = estadoTxt.getText();
        if(!(temp.trim().equals(""))) p.setEstado(temp);
        else{
            erro("ATENÇÃO CAMPO OBRIGATORIO: Estado não está preenchido.");
            return null;
        }
        
        temp = cidadeTxt.getText();
        if(!(temp.trim().equals(""))) p.setCidade(temp);
        else{
            erro("ATENÇÃO CAMPO OBRIGATORIO: Cidade não está preenchido.");
            return null;
        }
        
        temp = bairroTxt.getText();
        if(!(temp.trim().equals(""))) p.setBairro(temp);
        else{
            erro("ATENÇÃO CAMPO OBRIGATORIO: Bairro não está preenchido.");
            return null;
        }
        
        temp = enderecoTxt.getText();
        if(!(temp.trim().equals(""))) p.setEndereco(temp);
        else{
            erro("ATENÇÃO CAMPO OBRIGATORIO: Endereço não está preenchido.");
            return null;
        }
        
        temp = numeroTxt.getText();
        if(!(temp.trim().equals(""))) p.setNumero(temp);
        else{
            erro("ATENÇÃO CAMPO OBRIGATORIO: Número não está preenchido.");
            return null;
        }
        
        return p;
    }

    /**
     * Método responsável por retornar para a tela anterior e fechar a tela
     * atual
     */
    public void returnPes() {
        GerPessoas c = new GerPessoas();
        try {
            c.start(new Stage());
            fecha();
        } catch (Exception ex) {
            Logger.getLogger(CadPessoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método responsável por fechar a tela atual
     */
    public void fecha() {
        CadPessoa.getStage().close();
    }
    
    /**
     * Método responsável por validar o email.
     *
     * @param email String email a ser verificado.
     * @return Boolean - true caso seja valido e false caso não seja.
     */
    public static boolean validarEmail(String email) {
        Matcher matcher = PATTERN.matcher(email);
        return matcher.matches();
    }
    
    /**
     * Método responsável por confirmar.
     */
    public void confirma(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("PESSOA CADASTRADA.");
        alert.setTitle("Cadastro de pessoa");
        alert.setContentText("Pessoa cadastrada com sucesso.");
        alert.show();
    }
    
    /**
     * Método responsável por alertar erro.
     */
    public void erro(String mensagem){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("FALHA AO CADASTRAR PESSOA.");
        alert.setTitle("Cadastro de pessoa");
        alert.setContentText(mensagem);
        alert.show();
    }
    
}
