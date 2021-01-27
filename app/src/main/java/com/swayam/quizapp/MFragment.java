package com.swayam.quizapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MFragment extends Fragment{
    private int queNumber;
    private String que;

    public MFragment(){

    }

    public MFragment(int queNumber,String que){
        this.queNumber = queNumber;
        this.que = que;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = getLayoutInflater().inflate(R.layout.que_layout,null);
        TextView queHead = view.findViewById(R.id.queHead);
        TextView question = view.findViewById(R.id.que);
        queHead.setText("Question "+queNumber);
        question.setText(que);
        return view;
    }
}