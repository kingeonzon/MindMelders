package com.example.mindmelders;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);


        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btn1 = (Button) findViewById(R.id.logout);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btn2 = (Button) findViewById(R.id.custom2);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Account.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Account.this, Feedback.class);
                startActivity(intent);
            }
        });
    }
}
