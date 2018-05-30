package application.tool;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.util.ArrayList;

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

    public ArrayList<Question> parsingTest(int caller) {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            ClassLoader cl = this.getClass().getClassLoader();
            String path = RES_PATH + "test/" + (caller + 1) + ".xml";
            XMLStreamReader reader = factory.createXMLStreamReader(cl.getResourceAsStream(path));

            ArrayList<Question> questions = new ArrayList<>();

            while(reader.hasNext()) {
                if(reader.getEventType() == XMLEvent.START_ELEMENT && "question".equals(reader.getLocalName())) {
                    Question question = new Question();
                    ArrayList<String> tempVarAnswers = new ArrayList<>();
                    reader.next();
                    while(!(reader.getEventType() == XMLEvent.END_ELEMENT && "question".equals(reader.getLocalName()))) {
                        if(reader.getEventType() == XMLEvent.START_ELEMENT && "content".equals(reader.getLocalName())) {
                            reader.next();
                            question.setContent(reader.getText());
                        } else {
                            if(reader.getEventType() == XMLEvent.START_ELEMENT && "answer".equals(reader.getLocalName())) {
                                if("true".equals(reader.getAttributeValue(null, "isRight"))) {
                                    reader.next();
                                    question.setRightAnswer(reader.getText());
                                    tempVarAnswers.add(reader.getText());
                                } else {
                                    reader.next();
                                    tempVarAnswers.add(reader.getText());
                                }
                            }
                        }
                        reader.next();
                    }
                    question.setVarAnswer(tempVarAnswers);
                    questions.add(question);
                }
                reader.next();
            }
            return questions;
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}