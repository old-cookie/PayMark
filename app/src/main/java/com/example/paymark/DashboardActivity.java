package com.example.paymark;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    //private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        //pieChart = findViewById(R.id.pieChart);
        // Populate the pie chart with expenditure data

        Button addRecordButton = findViewById(R.id.addRecordButton);
        addRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, AddRecordActivity.class);
                startActivity(intent);
            }
        });
    }
}