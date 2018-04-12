package application.tool;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

public class XmlParser {

    final String RES_PATH = "res/";

    public ObservableList<String> parsingTopics(String caller) {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            ClassLoader cl = this.getClass().getClassLoader();
            XMLStreamReader reader = factory.createXMLStreamReader(cl.getResourceAsStream(RES_PATH + caller));
            ObservableList<String> items = FXCollections.observableArrayList();

            while(reader.hasNext()) {
                if(reader.getEventType() == XMLEvent.START_ELEMENT && "name".equals(reader.getLocalName())) {
                    reader.next();
                    items.add(reader.getText());
                }
                reader.next();
            }
            return items;
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String parsingTopic(String caller, String topic) {
        Boolean found = false;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            ClassLoader cl = this.getClass().getClassLoader();
            XMLStreamReader reader = factory.createXMLStreamReader(cl.getResourceAsStream(RES_PATH + caller));

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
                                    if(!"".equals(reader.getText().trim()))
                                    content += reader.getText();
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
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}