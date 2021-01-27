package com.swayam.quizapp;

public class QuizModel{
    private String question;
    private boolean answer;

    public QuizModel(String question, boolean answer){
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion(){
        return question;
    }

    public boolean getAnswer(){
        return answer;
    }
}
