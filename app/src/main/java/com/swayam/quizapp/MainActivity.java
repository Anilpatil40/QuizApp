package com.swayam.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.random.successunsuccess.SuccessUnSuccess;

public class MainActivity extends AppCompatActivity{
    public final String INDEX = "INDEX";
    public final String CORRECT = "CORRECT";

    FrameLayout frameLayout;
    int queNumber;
    ProgressBar progress;
    int correctAnss;
    TextView correctAns;

    QuizModel[] questions ;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questions = new QuizModel[]{
                new QuizModel(getResources().getString(R.string.q1),true),
                new QuizModel(getResources().getString(R.string.q2),true),
                new QuizModel(getResources().getString(R.string.q3),true),
                new QuizModel(getResources().getString(R.string.q4),true),
                new QuizModel(getResources().getString(R.string.q5),true),
                new QuizModel(getResources().getString(R.string.q6),true),
                new QuizModel(getResources().getString(R.string.q7),true),
                new QuizModel(getResources().getString(R.string.q8),true),
                new QuizModel(getResources().getString(R.string.q9),true),
                new QuizModel(getResources().getString(R.string.q10),true)
        };

        frameLayout = findViewById(R.id.frameLayout);
        progress = findViewById(R.id.progress);
        correctAns = findViewById(R.id.correctAns);

        if(savedInstanceState != null){
            queNumber = savedInstanceState.getInt(INDEX)-1;
            correctAnss = savedInstanceState.getInt(CORRECT);
            correctAns.setText(""+correctAnss);
        }

        moveToNextQuetion();
    }


    public void truePressed(View view){
        checkAnswer(true);

    }

    public void falsePressed(View view){
        checkAnswer(false);
    }

    public void checkAnswer(boolean value){
        if(questions[queNumber-1].getAnswer() == value){
            correctAnss++;
            ((TextView)findViewById(R.id.correctAns)).setText("Correct Answers : "+correctAnss);
            SuccessUnSuccess.showSuccessDialog(this, new SuccessUnSuccess.Listener() {
                @Override
                public void onDismiss() {
                    moveToNextQuetion();
                }
            });
        }else {
            SuccessUnSuccess.showErrorDialog(this, new SuccessUnSuccess.Listener() {
                @Override
                public void onDismiss() {
                    moveToNextQuetion();
                }
            });
        }

    }

    public void moveToNextQuetion(){
        queNumber = queNumber + 1;

        if(queNumber > questions.length){
            new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setTitle("Result")
                    .setMessage("Correct Answers "+correctAnss+" out of "+questions.length)
                    .setPositiveButton("FINISH", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){ finish();}
                    })
                    .show();
            return;
        }

        progress.setProgress(queNumber*10);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.start_from_left,R.anim.exit_to_right)
                .replace(frameLayout.getId(), new MFragment(queNumber,questions[queNumber-1].getQuestion()))
                .commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(INDEX,queNumber);
        outState.putInt(CORRECT,correctAnss);
    }
}