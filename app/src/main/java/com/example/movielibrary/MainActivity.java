package com.example.movielibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Movies> moviesList = new ArrayList<>();
    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycler = findViewById(R.id.recycle);
        movieAdapter = new MovieAdapter(moviesList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());

        recycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        DBClass db = new DBClass(this);
        moviesList.addAll(db.getMovies());

        recycler.setAdapter(movieAdapter);

        Button b1 = findViewById(R.id.btn_add_record);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddRecord.class);
                startActivityForResult(i, 0);
            }
        });

    }
}


