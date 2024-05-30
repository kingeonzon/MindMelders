package com.example.mindmelders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText userName, passWord;
    Button btn, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Find views
        userName = findViewById(R.id.uName);
        passWord = findViewById(R.id.Pass);
        Button btn3 = findViewById(R.id.btnloginTutor);
        Button btn = findViewById(R.id.btnLogIn2);
        Button btn2 = findViewById(R.id.btnReg);

        // Set onClickListener for the login button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userName.getText().toString();
                String password = passWord.getText().toString();

                // Check login credentials
                boolean checkLogin = databaseHelper.CheckLogin(username, password);

                if (checkLogin) {
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, Dashboard.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userName.getText().toString();
                String password = passWord.getText().toString();

                // Check login credentials
                boolean checkLogin = databaseHelper.CheckLogin(username, password);

                if (checkLogin) {
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, Dashboard2.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Login.this, Signup.class);
                    startActivity(intent);
                }
        });
    }
}