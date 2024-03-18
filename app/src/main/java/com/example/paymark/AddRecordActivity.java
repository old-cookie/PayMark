package com.example.paymark;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddRecordActivity extends AppCompatActivity {

    private Spinner expenseTypeSpinner;
    private EditText priceEditText;
    // Declare other views

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_record);

        expenseTypeSpinner = findViewById(R.id.expenseTypeSpinner);
        // Populate the spinner with expense types

        priceEditText = findViewById(R.id.priceEditText);
        // Initialize other views

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRecord();
            }
        });
    }

    private void saveRecord() {
        // Get the selected expense type, price, and other input values
        // Save the record to the SQLite database
        // Navigate back to the Dashboard screen
    }
}