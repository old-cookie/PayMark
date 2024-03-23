package com.example.paymark;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class DashboardActivity extends AppCompatActivity {
    private TextView sumTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        sumTextView = findViewById(R.id.sum_text_view);
        findViewById(R.id.add_record_button).setOnClickListener(v -> startActivity(new Intent(DashboardActivity.this, RecordActivity.class)));
    }
    protected void onResume() {
        super.onResume();
        updateSum();
    }
    private void updateSum() {
        // TODO: Calculate the sum of records and update the sumTextView
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_dashboard) {
            return true;
        } else if (itemId == R.id.action_record) {
            startActivity(new Intent(DashboardActivity.this, RecordActivity.class));
            return true;
        } else if (itemId == R.id.action_detail) {
            startActivity(new Intent(DashboardActivity.this, DetailActivity.class));
            return true;
        } else if (itemId == R.id.action_content) {
            startActivity(new Intent(DashboardActivity.this, ContentActivity.class));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}