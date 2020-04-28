package com.example.cs125_uiucgpa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.averageGpaButton).setOnClickListener(unused -> {
            Intent intent = new Intent(this, AverageGpaActivity.class);
            startActivity(intent);
            finish();
        });
    }


}
