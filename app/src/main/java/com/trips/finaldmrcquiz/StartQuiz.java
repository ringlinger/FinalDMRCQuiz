package com.trips.finaldmrcquiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class StartQuiz extends AppCompatActivity {

    Button button;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);

        TextView greet = (TextView)findViewById(R.id.greet);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(StartQuiz.this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            greet.setText("Hi, "+personName);
        }
        if (!SignUp.name_field_up.getText().toString().equals("") && !SignUp.email_field_up.getText().toString().equals("") &&
                !SignUp.password_field_up.getText().toString().equals("")){
            greet.setText("Hi, "+SignUp.name_field_up.getText().toString());
        }
        if(!Login.email_field_in.getText().toString().equals("") && !Login.password_field_in.getText().toString().equals("")){
            greet.setText("Hi, "+MainActivity.sharedPreferences.getString("name",null));
        }
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartQuiz.this,Questions.class));
            }
        });
    }
}
