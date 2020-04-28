package com.example.cs125_uiucgpa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AverageGpaActivity extends AppCompatActivity {
    private Button addNewCourse;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.average_gpa);

        LinearLayout addedClassesLayout = findViewById(R.id.addedClassesGroup);

        addNewCourse = (Button) findViewById(R.id.addNewCourse);

        addNewCourse.setOnClickListener(unused -> {
            View chunk;
            // Use chunk_ongoing_game.xml for ongoing game entries
            chunk = getLayoutInflater().inflate(R.layout.chunk_course, addedClassesLayout, false);
            TextView courseName = chunk.findViewById(R.id.textView);
            // Add it to the ongoing games list
            addedClassesLayout.addView(chunk);
        });
    }
}
