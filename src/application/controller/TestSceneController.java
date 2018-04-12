package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class TestSceneController implements Initializable {

    @FXML
    TextArea questionArea;
    @FXML
    CheckBox check1, check2, check3, check4;
    @FXML
    Button endTest, nextQuestionBtn, prevQuestionBtn;
    @FXML
    Label questionCount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void init(String chooseTopic, String caller) {

    }

    public void endTest(ActionEvent actionEvent) {

    }

    public void prevQuestion(ActionEvent actionEvent) {

    }

    public void nextQuestion(ActionEvent actionEvent) {

    }
}
