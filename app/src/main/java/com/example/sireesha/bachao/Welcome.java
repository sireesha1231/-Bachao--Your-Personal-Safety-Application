package com.example.sireesha.bachao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Welcome extends AppCompatActivity {

    TextView error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();
        final EditText loginText = (EditText) findViewById(R.id.textEditInputLayoutEmail);
        final EditText loginPassword = (EditText) findViewById(R.id.textEditInputLayoutPassword);
        final Button button = (Button) findViewById(R.id.appCompatButtonLogin);
        error = (TextView) findViewById(R.id.tv_error);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = null;
                if(loginText.getText().toString().equals("admin") &&
                        loginPassword.getText().toString().equals("admin")){
                    System.out.println("Entering");
                    myIntent = new Intent(view.getContext(), MainActivity.class);
                } else {
                    myIntent = new Intent(view.getContext(), Welcome.class);
                    error.setText("Login was unsuccessful!");
                }
                startActivity(myIntent);
            }
        });
    }

    public void goQuestion(View v)
    {
        Intent myIntent = new Intent(this, Question.class);
        startActivity(myIntent);
    }

    public void goRegister(View v)
    {
        Intent myIntent = new Intent(this, Register.class);
        startActivity(myIntent);
    }
}
