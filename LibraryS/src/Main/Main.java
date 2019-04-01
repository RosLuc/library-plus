/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Emprestimo.Emprestimo;
import Notificar.Email;
import Notificar.Notificar;
import Pessoa.Pessoa;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author lucas
 */
public class Main extends Application {
    
    private static Stage stage;
    
    private static Runnable t1 = new Runnable() {
        @Override
        public void run() {
            if(Emprestimo.verificaEmprestimosAtrasados()){
                List<Emprestimo> list = Emprestimo.ListaDeEmprestimoAtrasados();
                for(Emprestimo x: list){
                    try {
                        emprestimoNotificacao(x.getPessoa(), x);
                    } catch(RuntimeException e){
                        System.err.println("Erro:"+e);
                    }
                }
            }
        }
        
        private void emprestimoNotificacao(Pessoa p, Emprestimo emp) {
            String remetente = "libraryalory@gmail.com";
            String senh = "libraryalory12345";
            Email.enviarMensagem(remetente, p.getEmail(), "Notificação de atraso de empréstimo(s)", 
                Notificar.notificarAtraso(p.getNome(), emp), 
                Email.conectarConta(Email.conexaoSMTP(remetente), remetente, senh));
        }
    };
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        new Thread(t1).start();
        Parent loginFXML = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        Scene login = new Scene(loginFXML);
        primaryStage.setScene(login);
        primaryStage.setTitle("Library+");
        //primaryStage.setResizable(false);
        primaryStage.setMinHeight(540);
        primaryStage.setMinWidth(960);
        Image image = new Image("/images/LPScreenIcon.png");
        primaryStage.getIcons().add(image);
        stage = primaryStage;
        primaryStage.show();
    }
    
    public static void trocarTela(Parent FXML) {
        Scene cena = new Scene(FXML);
        stage.setScene(cena);
    }
    
    public static void main(String[] args){
        launch(args);
    }
    
    public static void fecharTela() {
        stage.close();
    }
    
}
