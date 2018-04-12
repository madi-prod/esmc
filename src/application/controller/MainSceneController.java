package application.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {
    @FXML
    Button theoryBtn, labWorkBtn, testBtn, exitBtn;

    public void handleButtonAction(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == exitBtn) {
            Platform.exit();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/chooseDialog.fxml"));
            Parent page = loader.load();

            TopicsChooseStageContoller controller = loader.getController();
            controller.setItemsList(getCaller(actionEvent));

            Scene scene = new Scene(page);
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Список тем");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        }
    }
    private String getCaller(ActionEvent actionEvent) {
        if (actionEvent.getSource() == theoryBtn) {
            return "theoryTopics.xml";
        } else if (actionEvent.getSource() == labWorkBtn) {
            return "labWorkTopics.xml";
        } else if (actionEvent.getSource() == testBtn) {
            return "testTopics.xml";
        } else {
            return null;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
