package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Stage mainStage;

    @Override
    public void start(Stage primaryStage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("view/main.fxml"));
            primaryStage.setTitle("Электронный учебно-методический комплекс");
            primaryStage.setScene(new Scene(root));
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/res/image/icon.png")));
            runStage(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runStage(Stage primaryStage) {
        mainStage = primaryStage;
        mainStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
