package com.example.gameailatrieuphu.Model;

import java.util.List;

public class Question {
    String contentQuestion;
    int idQuestion;

    List<Answer> listAnser;
    public Question() {
    }


    public Question(String contentQuestion, int idQuestion, List<Answer> listAnser) {
        this.contentQuestion = contentQuestion;
        this.idQuestion = idQuestion;
        this.listAnser = listAnser;
    }

    public String getContentQuestion() {
        return contentQuestion;
    }

    public void setContentQuestion(String contentQuestion) {
        this.contentQuestion = contentQuestion;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public List<Answer> getListAnser() {
        return listAnser;
    }

    public void setListAnser(List<Answer> listAnser) {
        this.listAnser = listAnser;
    }
}
