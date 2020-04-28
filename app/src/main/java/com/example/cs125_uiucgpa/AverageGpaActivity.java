package com.example.cs125_uiucgpa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AverageGpaActivity extends AppCompatActivity implements AddClassDialogue.AddClassDialogListerner {
    private double total = 0;
    private TextView gpa;
    private TextView totalGpa;
    private TextView chunkCourseId;
    private View chunk;
    private LinearLayout addedClassesLayout;
    String stringTotalGpa;
    private Button addNewCourse;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.average_gpa);

        addNewCourse = findViewById(R.id.addNewCourse);
        totalGpa = findViewById(R.id.total);

        addNewCourse.setOnClickListener(unused -> {
            openDialog();

            addedClassesLayout = findViewById(R.id.addedClassesGroup);


            // Use chunk_ongoing_game.xml for ongoing game entries
            chunk = getLayoutInflater().inflate(R.layout.chunk_course, addedClassesLayout, false);

            //Used for changing course name based on dialog
            chunkCourseId = chunk.findViewById(R.id.courseId);

            //Tally GPA total
            gpa = chunk.findViewById(R.id.courseGpa);
            total += Double.valueOf( gpa.getText().toString());
            stringTotalGpa = Double.toString(total);
            totalGpa.setText(stringTotalGpa);

            // Add it to the ongoing games list
            addedClassesLayout.addView(chunk);
        });
    }

    public void openDialog() {
            AddClassDialogue addClassDialogue = new AddClassDialogue();
            addClassDialogue.show(getSupportFragmentManager(), "add class dialog");
    }

    @Override
    public void applyTexts(String setCourseId) {
        if (setCourseId.equals("cancelled")) {
            addedClassesLayout.removeView(chunk);
        } else {
            chunkCourseId.setText(setCourseId);
        }
    }
}