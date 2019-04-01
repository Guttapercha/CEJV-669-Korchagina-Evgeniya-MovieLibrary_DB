package com.example.movielibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class AddRecord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        Button btnAdd = findViewById(R.id.button_save);
        Button btnGoToMain = findViewById(R.id.button_back);

        btnGoToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBClass dbc = new DBClass(getApplicationContext());

                EditText editName = findViewById(R.id.name);
                EditText editDescription = findViewById(R.id.description);
                RatingBar editRating = findViewById(R.id.rating);

                Movies movie = new Movies(
                        editName.getText().toString(),
                        editDescription.getText().toString(),
                        (int) editRating.getRating(),
                        true);

                dbc.addMovie(movie);

                Toast.makeText(getApplicationContext(), "Record added!", Toast.LENGTH_LONG).show();

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }
}
