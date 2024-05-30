package com.example.mindmelders;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        RatingBar rate = findViewById(R.id.rate);
        EditText feedbackText = findViewById(R.id.editTextTextMultiLine);
        ImageButton submitButton = findViewById(R.id.imageButton8);

        rate.setRating(0f);
        rate.setStepSize(0.5f);

        rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(Feedback.this, "Rating: " + rating, Toast.LENGTH_SHORT).show();
            }
        });

        submitButton.setOnClickListener(v -> {

            feedbackText.setText("");
            rate.setRating(0f);

            Toast.makeText(Feedback.this, "Feedback Submitted!", Toast.LENGTH_SHORT).show();
        });
    }
}
