package com.example.gameailatrieuphu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.gameailatrieuphu.Model.Answer;
import com.example.gameailatrieuphu.Model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = "VR#" + MainActivity.class.getName();
    private TextView questionIdTv;
    private TextView questionContentTv;
    private TextView answerTv1;
    private TextView answerTv2;
    private TextView answerTv3;
    private TextView answerTv4;
    private List<TextView> listAnswerTv;

    private List<Question> mQuestionList;
    int mCurrentQuestion;

    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("list_question");

        //addQuestionToFirebase();
        getListQuestionFromFirebase();
        mCurrentQuestion = 0;

    }

    private void addQuestionToFirebase() {
        myRef.setValue(getListQuestion());
    }

    private void getListQuestionFromFirebase() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mQuestionList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Question question = dataSnapshot.getValue(Question.class);
                    mQuestionList.add(question);

                }
                Log.i(TAG, "mQuestionList size " + mQuestionList.size());
                setDataQuestion(mQuestionList.get(mCurrentQuestion));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void setDataQuestion(Question question) {
        questionIdTv.setText("Question " + question.getIdQuestion());
        questionContentTv.setText(question.getContentQuestion());
        answerTv1.setText(question.getListAnser().get(0).getContentAswer());
        answerTv2.setText(question.getListAnser().get(1).getContentAswer());
        answerTv3.setText(question.getListAnser().get(2).getContentAswer());
        answerTv4.setText(question.getListAnser().get(3).getContentAswer());

        answerTv1.setBackgroundResource(R.drawable.bg_blue_corner30);
        answerTv2.setBackgroundResource(R.drawable.bg_blue_corner30);
        answerTv3.setBackgroundResource(R.drawable.bg_blue_corner30);
        answerTv4.setBackgroundResource(R.drawable.bg_blue_corner30);

        setClickAble(true);
    }


    private void initViews() {
        questionIdTv = findViewById(R.id.question_id_tv);
        questionContentTv = findViewById(R.id.question_content_tv);
        answerTv1 = findViewById(R.id.answer_1);
        answerTv2 = findViewById(R.id.answer_2);
        answerTv3 = findViewById(R.id.answer_3);
        answerTv4 = findViewById(R.id.answer_4);

        answerTv1.setOnClickListener(this);
        answerTv2.setOnClickListener(this);
        answerTv3.setOnClickListener(this);
        answerTv4.setOnClickListener(this);

        listAnswerTv = new ArrayList<>();
        listAnswerTv.add(answerTv1);
        listAnswerTv.add(answerTv2);
        listAnswerTv.add(answerTv3);
        listAnswerTv.add(answerTv4);

    }

    private List<Question> getListQuestion() {
        List<Question> questionList = new ArrayList<>();

        List<Answer> answerList1 = new ArrayList<>();
        answerList1.add(new Answer(true, "Cho"));
        answerList1.add(new Answer(false, "Ga"));
        answerList1.add(new Answer(false, "Vit"));
        answerList1.add(new Answer(false, "Ran"));
        questionList.add(new Question("Con nao co 4 chan ?", 1, answerList1));

        List<Answer> answerList2 = new ArrayList<>();
        answerList2.add(new Answer(false, "Cho"));
        answerList2.add(new Answer(false, "Ga"));
        answerList2.add(new Answer(false, "Vit"));
        answerList2.add(new Answer(true, "Ran"));
        questionList.add(new Question("Con nao khong co chan ?", 2, answerList2));

        List<Answer> answerList3 = new ArrayList<>();
        answerList3.add(new Answer(false, "Cho"));
        answerList3.add(new Answer(false, "Ga"));
        answerList3.add(new Answer(true, "Lon"));
        answerList3.add(new Answer(false, "Ran"));
        questionList.add(new Question("Con nao de con ?", 3, answerList3));

        return questionList;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        setClickAble(false);

        switch (id) {
            case R.id.answer_1:
                answerTv1.setBackgroundResource(R.drawable.bg_yellow_corner30);
                checkAnswer(answerTv1, 0);
                break;
            case R.id.answer_2:
                answerTv2.setBackgroundResource(R.drawable.bg_yellow_corner30);
                checkAnswer(answerTv2, 1);
                break;
            case R.id.answer_3:
                answerTv3.setBackgroundResource(R.drawable.bg_yellow_corner30);
                checkAnswer(answerTv3, 2);
                break;
            case R.id.answer_4:
                answerTv4.setBackgroundResource(R.drawable.bg_yellow_corner30);
                checkAnswer(answerTv4, 3);
                break;
        }
    }

    private void setClickAble(boolean clickAble) {
        answerTv1.setClickable(clickAble);
        answerTv2.setClickable(clickAble);
        answerTv3.setClickable(clickAble);
        answerTv4.setClickable(clickAble);

    }

    private void checkAnswer(TextView answerTv, int i) {
        Question currentQuestion = mQuestionList.get(mCurrentQuestion);
        boolean isCorrect = currentQuestion.getListAnser().get(i).isCorrect();
        int correctAnswer = 0;
        for (Answer answer : currentQuestion.getListAnser()) {
            if (answer.isCorrect()) {
                break;
            }
            correctAnswer++;
        }
        int finalCorrectAnswer = correctAnswer;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isCorrect) {
                    answerTv.setBackgroundResource(R.drawable.bg_green_corner30);
                    nextQuestion();
                } else {
                    answerTv.setBackgroundResource(R.drawable.bg_red_corner30);
                    listAnswerTv.get(finalCorrectAnswer).setBackgroundResource(R.drawable.bg_green_corner30);
                    gameOver();

                }
            }
        }, 1500);

    }

    private void gameOver() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showDialog("Game Over");
            }
        }, 1000);
    }

    private void nextQuestion() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mCurrentQuestion == mQuestionList.size() - 1) {
                    victory();
                } else {
                    mCurrentQuestion++;
                    setDataQuestion(mQuestionList.get(mCurrentQuestion));
                }

            }
        }, 1500);

    }

    private void victory() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showDialog("You win !!!");
            }
        }, 1000);
    }

    private void showDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mCurrentQuestion = 0;
                setDataQuestion(mQuestionList.get(mCurrentQuestion));
                dialogInterface.dismiss();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}