package com.trips.finaldmrcquiz;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FinishQuiz extends AppCompatActivity {

    DatabaseReference mDetails;
    TextView name_text;
    TextView email_text;
    TextView score_text;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_quiz);

        name_text = (TextView)findViewById(R.id.name_text);
        email_text = (TextView)findViewById(R.id.email_text);
        score_text = (TextView)findViewById(R.id.score_text);

        mDetails = FirebaseDatabase.getInstance().getReference().child("User Details and Score");
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(FinishQuiz.this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            Log.i("Names",personName + personEmail);
            String finalScore = String.valueOf(Questions.score);
            HashMap<String,String> finalDetails = new HashMap<String, String>();
            finalDetails.put("Name",personName);
            finalDetails.put("Email",personEmail);
            finalDetails.put("Score",finalScore);
            mDetails.push().setValue(finalDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Stored", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            name_text.setText("Name: "+personName);
            email_text.setText("Email: "+personEmail);
            score_text.setText("Score: "+finalScore);
        }

        if (!SignUp.name_field_up.getText().toString().equals("") && !SignUp.email_field_up.getText().toString().equals("") &&
                !SignUp.password_field_up.getText().toString().equals("")){
            String finalScore = String.valueOf(Questions.score);
            HashMap<String,String> finalDetails = new HashMap<String, String>();
            finalDetails.put("Name",SignUp.name_field_up.getText().toString());
            finalDetails.put("Email",SignUp.email_field_up.getText().toString());
            finalDetails.put("Score",finalScore);
            mDetails.push().setValue(finalDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Stored", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            name_text.setText("Name: "+SignUp.name_field_up.getText().toString());
            email_text.setText("Email: "+SignUp.email_field_up.getText().toString());
            score_text.setText("Score: "+finalScore);
        }

        if(!Login.email_field_in.getText().toString().equals("") && !Login.password_field_in.getText().toString().equals("")){

            String finalScore = String.valueOf(Questions.score);
            HashMap<String,String> finalDetails = new HashMap<String, String>();
            finalDetails.put("Name",MainActivity.sharedPreferences.getString("name",null));
            finalDetails.put("Email",MainActivity.sharedPreferences.getString("email",null));
            finalDetails.put("Score",finalScore);
            mDetails.push().setValue(finalDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Stored", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            name_text.setText("Name: "+MainActivity.sharedPreferences.getString("name",null));
            email_text.setText("Email: "+MainActivity.sharedPreferences.getString("email",null));
            score_text.setText("Score: "+finalScore);
        }
    }
}
