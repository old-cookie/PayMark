package vtc.oldcookie.paymark;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import vtc.oldcookie.paymark.forum.login;

/**
 * This class represents the AboutActivity in the application.
 * It extends AppCompatActivity, which is a base class for activities that use the support library action bar features.
 */
public class AboutActivity extends AppCompatActivity {
    /**
     * This method is called when the activity is starting.
     * It is where most initialization should go.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the user interface layout for this activity
        setContentView(R.layout.activity_about);

        // Find the button by its ID
        Button goWebButton = findViewById(R.id.go_web);
        Button goforumButton = findViewById(R.id.go_forum);
        // Set an OnClickListener for the button
        goWebButton.setOnClickListener(v -> {
            // Create an Intent to start the webActivity
            Intent intent = new Intent(AboutActivity.this, webActivity.class);
            // Start the activity
            startActivity(intent);
        });
        goforumButton.setOnClickListener(v -> {
            // Create an Intent to start the webActivity
            Intent intent = new Intent(AboutActivity.this, login.class);
            // Start the activity
            startActivity(intent);
        });
    }

    /**
     * This method is called when a view has been clicked.
     *
     * @param view The view that was clicked.
     */
    public void onClick(View view) {
        // Finish the activity
        finish();
    }
}