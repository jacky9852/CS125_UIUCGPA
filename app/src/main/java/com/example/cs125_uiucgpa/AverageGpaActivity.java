package com.example.cs125_uiucgpa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AverageGpaActivity extends AppCompatActivity implements AddClassDialogue.AddClassDialogListerner {
    private double total  = 0;
    private double totalGpaAdded = 0;
    private int totalClassesAdded = 0;
    private TextView gpa;
    private TextView totalGpa;
    private TextView chunkCourseId;
    private TextView chunkCourseGpa;
    private TextView chunkCourseLetterGrade;
    private View chunk;
    private LinearLayout addedClassesLayout;
    String stringTotalGpa;
    private Button addNewCourse;
    private List<String[]> allData;
    private List<String[]> all2019Data;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.average_gpa);

        //Loads in data from the database to allData
        try {
            // Create an object of file reader
            // class with CSV file as a parameter.
            //Reader filereader = new Reader( new InputStreamReader(c.getAssets().open("SOCIAL_POSTS.csv")));

            // create csvReader object and skip first Line
            CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(getAssets().open("uiuc-gpa-dataset.csv")))
                    .withSkipLines(1)
                    .build();
            allData = csvReader.readAll();
        }
        catch (Exception e) {
            e.printStackTrace();
            List<String[]> errorList = new ArrayList<>();
            String[] error = new String[1];
            error[0] = "error";
            errorList.add(error);
            allData = errorList;
        }

        for (int i = 0; i < allData.size(); i++) {
            String[] replacement = allData.get(i)[0].split(",");
            allData.set(i, replacement);
        }

        int end = 0;
        String dataSetYear = "2019";
        for (int i = 0; i < allData.size(); i++) {
            if (!(allData.get(i)[0].equals(dataSetYear))){
                end = i;
                break;
            }
        }

        allData = allData.subList(0, end);
        System.out.println(allData);

        addNewCourse = findViewById(R.id.addNewCourse);
        totalGpa = findViewById(R.id.total);

        addNewCourse.setOnClickListener(unused -> {
            openDialog();

            addedClassesLayout = findViewById(R.id.addedClassesGroup);


            // Use chunk_ongoing_game.xml for ongoing game entries
            chunk = getLayoutInflater().inflate(R.layout.chunk_course, addedClassesLayout, false);

            //Used for changing course name based on dialog
            chunkCourseId = chunk.findViewById(R.id.courseId);
            chunkCourseGpa = chunk.findViewById(R.id.courseGpa);
            chunkCourseLetterGrade = chunk.findViewById(R.id.courseGrade);

            //Tally GPA total
            gpa = chunk.findViewById(R.id.courseGpa);
            total += Double.valueOf( gpa.getText().toString());
            stringTotalGpa = Double.toString(total);

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
            String[] part = setCourseId.split("(?<=\\D)(?=\\d)");
            String courseSubject = part[0];
            int courseNumber = Integer.parseInt(part[1]);
            double courseAverageGpa = AvgGpaCalc.avgGpaCalc(allData, courseSubject, courseNumber);
            chunkCourseGpa.setText(Double.toString(courseAverageGpa).substring(0, 4));

            String courseLetterGrade;

            if (courseAverageGpa > 3.67) {
                courseLetterGrade = "A+/A";
            } else if (courseAverageGpa > 3.33) {
                courseLetterGrade = "A-";
            } else if (courseAverageGpa > 3.00) {
                courseLetterGrade = "B+";
            } else if (courseAverageGpa > 2.67) {
                courseLetterGrade = "B";
            } else if (courseAverageGpa > 2.33) {
                courseLetterGrade = "B-";
            } else if (courseAverageGpa > 2.00) {
                courseLetterGrade = "C+";
            } else if (courseAverageGpa > 1.67) {
                courseLetterGrade = "C";
            } else if (courseAverageGpa > 1.33) {
                courseLetterGrade = "C-";
            } else if (courseAverageGpa > 1.00) {
                courseLetterGrade = "D+";
            } else if (courseAverageGpa > 0.67) {
                courseLetterGrade = "D";
            } else if (courseAverageGpa > 0.00) {
                courseLetterGrade = "D-";
            } else {
                courseLetterGrade = "Run";
            }
            chunkCourseLetterGrade.setText(courseLetterGrade);

            totalGpaAdded += courseAverageGpa;
            totalClassesAdded++;

            totalGpa.setText(Double.toString(totalGpaAdded/totalClassesAdded).substring(0, 4));
        }
    }
}