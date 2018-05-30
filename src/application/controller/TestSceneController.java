package application.controller;

import application.tool.Question;
import application.tool.XmlParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;

public class TestSceneController implements Initializable {

    private ArrayList<CheckBox> checkFlags = new ArrayList<>();
    private ArrayList<Question> questions;
    private int[][] answersMap;
    private int[][] rightAnswersMap;
    private int currentQuestion = 0;
    private int rightAnswer = 0;
    private int result = 0;
    private double resultPercent;

    @FXML
    Label questionArea;
    @FXML
    CheckBox check1, check2, check3, check4;
    @FXML
    Button endTest, nextQuestionBtn, prevQuestionBtn;
    @FXML
    Label questionCount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checkFlags.add(check1);
        checkFlags.add(check2);
        checkFlags.add(check3);
        checkFlags.add(check4);
    }

    public void initScene(int chooseTest) {
        XmlParser parser = new XmlParser();
        questions = parser.parsingTest(chooseTest);
        /*for(Question question: questions) {
            Collections.shuffle(question.getVarAnswer());
        }*/
        answersMap = new int[questions.size()][4];
        rightAnswersMap = new int[questions.size()][4];
        for(int i = 0; i < questions.size(); i++) {
            rightAnswersMap[i][0] = questions.get(i).getRightAnswerPosition();
        }
        Collections.shuffle(questions);
        setText();
    }

    public void endTest(ActionEvent actionEvent) throws IOException {
        for(int i = 0; i < questions.size(); i++) {
            for(int j = 0; j < 4; j++) {
                if(answersMap[i][j] == rightAnswersMap[i][j])
                    rightAnswer++;
            }
            if(rightAnswer == 4) {
                result++;
            }
        }
        resultPercent = (result / questions.size()) * 100;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/endTest.fxml"));
        Parent page = loader.load();
        EndTestController controller = loader.getController();
        controller.initScene(questions.size(), result, resultPercent);
        Scene scene = new Scene(page);
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Тест завершен");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(questionArea.getParent().getScene().getWindow());
        dialogStage.setScene(scene);
        dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("/res/image/icon.png")));
        dialogStage.showAndWait();
    }

    public void prevQuestion(ActionEvent actionEvent) {
        if(currentQuestion != 0) {
            currentQuestion--;
            for(int i = 0; i < 4; i++) {
                if(answersMap[currentQuestion][i] == 1) {
                    checkFlags.get(i).setSelected(true);
                } else {
                    checkFlags.get(i).setSelected(false);
                }
            }
            setText();
        }
    }

    public void nextQuestion(ActionEvent actionEvent) throws IOException {
        if(currentQuestion + 1 != questions.size()) {
            for(int i = 0; i < 4; i++) {
                if(checkFlags.get(i).isSelected()) {
                    answersMap[currentQuestion][i] = 1;
                }
            }
            currentQuestion++;
            for(int i = 0; i < 4; i++) {
                if(answersMap[currentQuestion][i] == 1) {
                    checkFlags.get(i).setSelected(true);
                } else {
                    checkFlags.get(i).setSelected(false);
                }
            }
            setText();
        } else {
            endTest(new ActionEvent());
        }
    }

    private void setText() {
        questionArea.setText(questions.get(currentQuestion).getContent());
        questionCount.setText((currentQuestion + 1) + " из " + questions.size());
        for(int i = 0; i < 4; i++) {
            String temp = questions.get(currentQuestion).getVarAnswer().get(i);
            checkFlags.get(i).setText(temp);
        }
    }
}
