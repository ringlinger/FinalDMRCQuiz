package com.trips.finaldmrcquiz;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Questions extends AppCompatActivity {

    DatabaseReference mQuestion;
    DatabaseReference mAnsLocs;
    ArrayList<Integer> ansLocs = new ArrayList<Integer>();
    DatabaseReference mQuestions;
    DatabaseReference mAnswer1opt1;
    DatabaseReference mAnswer1opt2;
    DatabaseReference mAnswer1opt3;
    DatabaseReference mAnswer1opt4;
    DatabaseReference mAnswersopt1;
    DatabaseReference mAnswersopt2;
    DatabaseReference mAnswersopt3;
    DatabaseReference mAnswersopt4;
    TextView question_text;
    Button next_button;
    RadioButton option1;
    RadioButton option2;
    RadioButton option3;
    RadioButton option4;
    int i = 1;
    int j = 0;
    static int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        question_text = (TextView)findViewById(R.id.question_text);

        mAnsLocs = FirebaseDatabase.getInstance().getReference().child("Answer Tags");
        mAnsLocs.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Integer value = dataSnapshot.getValue(Integer.class);
                ansLocs.add(value);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        option1 = (RadioButton)findViewById(R.id.option1);
        option2 = (RadioButton)findViewById(R.id.option2);
        option3 = (RadioButton)findViewById(R.id.option3);
        option4 = (RadioButton)findViewById(R.id.option4);
        next_button = (Button)findViewById(R.id.next_button);
        mQuestion = FirebaseDatabase.getInstance().getReference().child("Questions").child("Question 1");
        mQuestion.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String question = dataSnapshot.getValue().toString();
                question_text.setText("Q) "+question);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mAnswer1opt1 = FirebaseDatabase.getInstance().getReference().child("Answers").child("Answer 1").child("Option 1");
        mAnswer1opt1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String opt1 = dataSnapshot.getValue().toString();
                option1.setText(opt1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        mAnswer1opt2 = FirebaseDatabase.getInstance().getReference().child("Answers").child("Answer 1").child("Option 2");
        mAnswer1opt2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String opt1 = dataSnapshot.getValue().toString();
                option2.setText(opt1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        mAnswer1opt3 = FirebaseDatabase.getInstance().getReference().child("Answers").child("Answer 1").child("Option 3");
        mAnswer1opt3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String opt1 = dataSnapshot.getValue().toString();
                option3.setText(opt1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        mAnswer1opt4 = FirebaseDatabase.getInstance().getReference().child("Answers").child("Answer 1").child("Option 4");
        mAnswer1opt4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String opt1 = dataSnapshot.getValue().toString();
                option4.setText(opt1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void nextQuestion(View view) {
        i++;
        if (i == 10) {
            next_button.setText("Finish");
        }
        if (i == 11) {
            startActivity(new Intent(Questions.this, FinishQuiz.class));
            checkScore();
        }
        if (i < 11) {
            mQuestions = FirebaseDatabase.getInstance().getReference().child("Questions").child("Question " + i);
            mQuestions.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String question = dataSnapshot.getValue().toString();
                    question_text.setText("Q) " + question);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            mAnswersopt1 = FirebaseDatabase.getInstance().getReference().child("Answers").child("Answer " + i).child("Option 1");
            mAnswersopt1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String opt1 = dataSnapshot.getValue().toString();
                    option1.setText(opt1);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
            mAnswersopt2 = FirebaseDatabase.getInstance().getReference().child("Answers").child("Answer " + i).child("Option 2");
            mAnswersopt2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String opt1 = dataSnapshot.getValue().toString();
                    option2.setText(opt1);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
            mAnswersopt3 = FirebaseDatabase.getInstance().getReference().child("Answers").child("Answer " + i).child("Option 3");
            mAnswersopt3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String opt1 = dataSnapshot.getValue().toString();
                    option3.setText(opt1);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
            mAnswersopt4 = FirebaseDatabase.getInstance().getReference().child("Answers").child("Answer " + i).child("Option 4");
            mAnswersopt4.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String opt1 = dataSnapshot.getValue().toString();
                    option4.setText(opt1);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
            checkScore();
        }
    }

    public void checkScore(){
        if (option1.isChecked()){
            if (ansLocs.get(j).toString().equals(option1.getTag().toString())){
                score++;
            }
                Log.i("Score", String.valueOf(score));
        }
        else if (option2.isChecked()){
            if (ansLocs.get(j).toString().equals(option2.getTag().toString())){
                score++;
            }
                Log.i("Score", String.valueOf(score));
        }
        else if (option3.isChecked()){
            if (ansLocs.get(j).toString().equals(option3.getTag().toString())){
                score++;
            }
                Log.i("Score", String.valueOf(score));
        }
        else if (option4.isChecked()){
            if (ansLocs.get(j).toString().equals(option4.getTag().toString())){
                score++;
            }
                Log.i("Score", String.valueOf(score));
        }
        j++;
        option1.setChecked(false);
        option2.setChecked(false);
        option3.setChecked(false);
        option4.setChecked(false);
    }
}
