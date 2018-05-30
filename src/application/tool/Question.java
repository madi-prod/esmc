package application.tool;

import java.util.ArrayList;

public class Question {

    private String content;
    private ArrayList<String> varAnswer;
    private String rightAnswer;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<String> getVarAnswer() {
        return varAnswer;
    }

    public void setVarAnswer(ArrayList<String> varAnswer) {
        this.varAnswer = varAnswer;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public int getRightAnswerPosition() {
        for(int i = 0; i < 4; i++) {
            if(rightAnswer.equals(varAnswer.get(i))) {
                return i;
            }
        }
        return -1;
    }
}
