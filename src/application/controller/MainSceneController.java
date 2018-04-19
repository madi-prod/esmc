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
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {
    @FXML
    Button theoryBtn, labWorkBtn, testBtn, exitBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void openTheoryDialog(ActionEvent actionEvent) throws IOException {
        initDialogStage(actionEvent);
    }

    public void openLabWorkDialog(ActionEvent actionEvent) throws IOException {
        initDialogStage(actionEvent);
    }

    public void openTestDialog(ActionEvent actionEvent) throws IOException {
        initDialogStage(actionEvent);
    }

    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }

    private String getTopics(ActionEvent actionEvent) {
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

    private void initDialogStage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/chooseDialog.fxml"));
        Parent page = loader.load();
        TopicsChooseStageContoller controller = loader.getController();
        controller.setItemsList(getTopics(actionEvent));
        Scene scene = new Scene(page);
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Список тем");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        dialogStage.setScene(scene);
        dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("/res/image/icon.png")));
        dialogStage.showAndWait();
    }

}