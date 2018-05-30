package application.controller;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EndTestController implements Initializable {

    @FXML
    Button returnBtn;
    @FXML
    Label resultLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initScene(int questionAmount, int resultCount, double resultPercent) {
        resultLabel.setText("Результат: " + resultCount + " из " + questionAmount + " (" + resultPercent + ")");
    }

    public void returnToMainScene() throws IOException {
        Main.mainStage.hide();
        Parent root = FXMLLoader.load(getClass().getResource("/application/view/main.fxml"));
        Main.mainStage.setScene(new Scene(root));
        Main.mainStage.setTitle("Электронный учебно-методический комплекс");
        Main.mainStage.show();
    }

}
