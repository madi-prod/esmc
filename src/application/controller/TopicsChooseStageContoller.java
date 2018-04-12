package application.controller;

import application.Main;
import application.tool.XmlParser;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TopicsChooseStageContoller implements Initializable {

    private ObservableList<String> items;
    private static String caller;

    @FXML
    private ListView topicsList;

    public void closeStage(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void showTopic(ActionEvent actionEvent) throws IOException {
        String chooseTopic = (String) topicsList.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/textScene.fxml"));
        Parent page = loader.load();

        TopicSceneController controller = loader.getController();
        controller.setText(chooseTopic, caller);
        Scene scene = new Scene(page);
        Main.mainStage.hide();
        Main.mainStage.setTitle(chooseTopic);
        Main.mainStage.setScene(scene);
        Main.mainStage.show();
    }

    public void setItemsList(String caller){
        this.caller = caller;
        XmlParser parser = new XmlParser();
        items = parser.parsingTopics(caller);
        if (items != null) {
            topicsList.setItems(items);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
