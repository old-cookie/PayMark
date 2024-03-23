package com.example.paymark;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
public class DetailActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button exportButton;

    private RecordAdapter adapter;
    private List<Record> records;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        recyclerView = findViewById(R.id.recycler_view);
        exportButton = findViewById(R.id.export_button);
        records = new ArrayList<>();
        adapter = new RecordAdapter(records);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        exportButton.setOnClickListener(v -> exportRecords());
    }

    private void exportRecords() {
        // Export selected records to a CSV file
        // ...
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_dashboard) {
            startActivity(new Intent(DetailActivity.this, DashboardActivity.class));
            return true;
        } else if (itemId == R.id.action_record) {
            startActivity(new Intent(DetailActivity.this, RecordActivity.class));
            return true;
        } else if (itemId == R.id.action_detail) {

            return true;
        } else if (itemId == R.id.action_content) {
            startActivity(new Intent(DetailActivity.this, ContentActivity.class));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}