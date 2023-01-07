package com.example.gameailatrieuphu.Model;

public class Answer {
    boolean isCorrect;
    String contentAswer;

    public Answer() {
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public Answer(boolean isCorrect, String contentAswer) {
        this.isCorrect = isCorrect;
        this.contentAswer = contentAswer;
    }

    public String getContentAswer() {
        return contentAswer;
    }

    public void setContentAswer(String contentAswer) {
        this.contentAswer = contentAswer;
    }
}
