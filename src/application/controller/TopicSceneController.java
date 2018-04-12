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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TopicSceneController implements Initializable {

    @FXML
    TextArea textArea;
    @FXML
    Button closeBtn;


    public void setText(String topic, String caller) {
        XmlParser parser = new XmlParser();
        String content = parser.parsingTopic(caller, topic);
        if (content != null) {
            textArea.setText(content);
        }
    }

    public void returnToMainScene(ActionEvent actionEvent) throws IOException {
        Main.mainStage.hide();
        Parent root = FXMLLoader.load(getClass().getResource("../view/main.fxml"));
        Main.mainStage.setScene(new Scene(root));
        Main.mainStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
