package application.tool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

public class XmlParser {

    final String RES_PATH = "res/xml/";

    private int evenType;

    public ObservableList<String> parsingAll(String caller) {
        try {
            XMLStreamReader xmlr = XMLInputFactory.newInstance().createXMLStreamReader(caller, new FileInputStream(new File(RES_PATH + caller)));
            ObservableList<String> items = FXCollections.observableArrayList();
            while (xmlr.hasNext()) {
                xmlr.next();
                if (xmlr.hasText() && xmlr.getText().trim().length() > 0) {
                    items.add(xmlr.getText());
                }
            }
            return items;
        } catch (FileNotFoundException | XMLStreamException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String parsingTopic(String caller, String topic) {
        Boolean complete = false;
        try {
            XMLStreamReader xmlr = XMLInputFactory.newInstance().createXMLStreamReader(caller, new FileInputStream(new File(RES_PATH + caller)));
            String content = "";
            while (xmlr.hasNext()) {
                evenType = xmlr.next();
                if (xmlr.hasText() && xmlr.getText().equals(topic)) {
                    while (evenType != XMLEvent.END_ELEMENT) {
                        evenType = xmlr.next();
                        content += xmlr.getText();
                    }
                    System.out.println(content);
                    complete = true;
                }
                if (complete) {
                    break;
                }
            }
            return content;
        } catch (FileNotFoundException | XMLStreamException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}