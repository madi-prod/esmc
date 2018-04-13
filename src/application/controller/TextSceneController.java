package application.controller;

import application.Main;
import application.tool.XmlParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TextSceneController implements Initializable {

    @FXML
    WebView browser;
    @FXML
    Button closeBtn;


    private WebEngine engine;

    public void initScene(String topic, String caller) {
        engine = browser.getEngine();
        URL url = getClass().getResource("/res/01.htm");
        engine.load(url.toExternalForm());

    }

    public void returnToMainScene(ActionEvent actionEvent) throws IOException {
        Main.mainStage.hide();
        Parent root = FXMLLoader.load(getClass().getResource("/application/view/main.fxml"));
        Main.mainStage.setScene(new Scene(root));
        Main.mainStage.setTitle("Электронный учебно-методический комплекс");
        Main.mainStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
