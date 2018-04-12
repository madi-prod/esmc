package application.tool;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class XmlParser {

    final String RES_PATH = "res/xml/";

    public ObservableList<String> parsingTopics(String caller) {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(RES_PATH + caller));
            ObservableList<String> items = FXCollections.observableArrayList();

            while(reader.hasNext()) {
                if(reader.getEventType() == XMLEvent.START_ELEMENT && "name".equals(reader.getLocalName())) {
                    reader.next();
                    items.add(reader.getText());
                }
                reader.next();
            }
            return items;
        } catch (FileNotFoundException | XMLStreamException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String parsingTopic(String caller, String topic) {
        Boolean found = false;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(RES_PATH + caller));

            String content = "";
            while(reader.hasNext()) {
                if(reader.getEventType() == XMLEvent.START_ELEMENT && "name".equals(reader.getLocalName())) {
                    reader.next();
                    if(topic.equals(reader.getText())){
                        reader.next();
                        reader.next();
                        while(reader.getEventType() != XMLEvent.END_ELEMENT){
                            found = true;
                            switch (reader.getEventType()){
                                case XMLEvent.CHARACTERS:
                                    content += reader.getText().trim();
                            }
                            reader.next();
                        }
                    }
                }
                if(found) {
                    return content;
                }
                reader.next();
            }
        } catch (FileNotFoundException | XMLStreamException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

/*
        while(reader.hasNext()) {
        int evenType = reader.getEventType();
        switch (evenType) {
        case XMLEvent.START_DOCUMENT:
        System.out.println("Start Document");
        break;
        case XMLEvent.START_ELEMENT:
        System.out.println("Start Element: " + reader.getName());
        break;
        case XMLEvent.END_ELEMENT:
        System.out.println("End Element: " + reader.getName());
        break;
        case XMLEvent.CHARACTERS:
        System.out.println("Characters: " + reader.getText());
        break;
        }

        reader.next();
        }
        if(reader.getEventType() == XMLEvent.END_DOCUMENT) {
        System.out.println("End Document");
        }
        */
