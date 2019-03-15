/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryScreens;

import com.sun.javafx.scene.SceneHelper;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author death
 */
public class AtualizarPessoa extends Application {

    private static Stage stage; //janela1

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/AtualizarPessoa.fxml")); //carrega o FXML
        Scene scene = new Scene(root); //Coloca o FXML em uma cena
        stage.setResizable(false);
        stage.setTitle("Library+");
        SceneHelper.getSceneAccessor();
        stage.setScene(scene); //Coloca cena em uma janela
        stage.show(); //mostra janela2
        Image image = new Image("/images/LPScreenIcon.png");
        stage.getIcons().add(image);
        setStage(stage);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        AtualizarPessoa.stage = stage;
    }
}
