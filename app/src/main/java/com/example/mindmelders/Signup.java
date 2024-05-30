package com.example.mindmelders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    EditText uName, email, pass, cPass;
    Button b1, b2;
    Handler handler;
    ProgressBar progressBar;
    TextView loadingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        databaseHelper = new DatabaseHelper(this);
        uName = findViewById(R.id.uName);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.passWord);
        cPass = findViewById(R.id.cPassword);

        handler = new Handler();
        progressBar = findViewById(R.id.progressBar4);
        loadingText = findViewById(R.id.loadingText4);

        b1 = findViewById(R.id.btnBack2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, MainActivity.class);
                startActivity(intent);
            }
        });

        b2 = findViewById(R.id.btnDone);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = uName.getText().toString();
                String userEmail = email.getText().toString();
                String password = pass.getText().toString();
                String confirmPassword = cPass.getText().toString();

                progressBar.setVisibility(View.VISIBLE);
                loadingText.setVisibility(View.VISIBLE);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        loadingText.setVisibility(View.GONE);

                        if (username.isEmpty() || userEmail.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
                        } else {
                            if (password.equals(confirmPassword)) {
                                boolean checkUsername = databaseHelper.CheckUsername(username);
                                if (checkUsername) {
                                    boolean insert = databaseHelper.Insert(username, userEmail, password);
                                    if (insert) {
                                        Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
                                        uName.setText("");
                                        email.setText("");
                                        pass.setText("");
                                        cPass.setText("");

                                        Intent intent = new Intent(Signup.this, Login.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, 3000);
            }
        });
    }
}