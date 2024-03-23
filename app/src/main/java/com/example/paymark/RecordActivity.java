package com.example.paymark;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
public class RecordActivity extends AppCompatActivity {
    private Spinner typeSpinner;
    private EditText priceEditText;
    private EditText infoEditText;
    private Button saveButton;

    private RecordAdapter adapter;
    private List<Record> records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        typeSpinner = findViewById(R.id.type_spinner);
        priceEditText = findViewById(R.id.price_edit_text);
        infoEditText = findViewById(R.id.info_edit_text);
        saveButton = findViewById(R.id.save_button);

        records = new ArrayList<>();
        adapter = new RecordAdapter(records);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.type_options,
                android.R.layout.simple_spinner_item
        );

        // Specify the layout to use when the list of choices appears
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        typeSpinner.setAdapter(spinnerAdapter);

        saveButton.setOnClickListener(v -> saveRecord());
    }

    private void saveRecord() {
        String type = typeSpinner.getSelectedItem().toString();
        double price = Double.parseDouble(priceEditText.getText().toString());
        String info = infoEditText.getText().toString();
        long timestamp = System.currentTimeMillis();

        Record record = new Record(type, price, info, timestamp);
        adapter.addRecord(record);

        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_dashboard) {
            startActivity(new Intent(RecordActivity.this, DashboardActivity.class));
            return true;
        } else if (itemId == R.id.action_record) {
            return true;
        } else if (itemId == R.id.action_detail) {
            startActivity(new Intent(RecordActivity.this, DetailActivity.class));
            return true;
        } else if (itemId == R.id.action_content) {
            startActivity(new Intent(RecordActivity.this, ContentActivity.class));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}