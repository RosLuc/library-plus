/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryScreens;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author death
 */
public class CadPessoa extends Application {

    private static Stage stage; //janela1

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/CadPessoa.fxml")); //carrega o FXML
        Scene scene = new Scene(root); //Coloca o FXML em uma cena
        stage.setTitle("Library+");
        stage.setScene(scene); //Coloca cena em uma janela
        stage.show(); //mostra janela2
        setStage(stage);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        CadPessoa.stage = stage;
    }
}
