package vtc.oldcookie.paymark;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import vtc.oldcookie.paymark.db.DBManager;

/**
 * Activity class for the Setting page.
 */
public class SettingActivity extends AppCompatActivity {
    /**
     * Called when the activity is starting.
     * This is where most initialization should go.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param view The view that was clicked.
     */
    public void onClick(View view) {
        if (view.getId() == R.id.setting_iv_back) {
            finish();
        } else if (view.getId() == R.id.setting_tv_clear) {
            showDeleteDialog();
        }
    }

    /**
     * Shows a dialog to confirm the deletion of all accounts.
     */
    private void showDeleteDialog() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.tips))
                .setMessage(R.string.ask_sure)
                .setPositiveButton(R.string.cancel, null)
                .setNegativeButton(R.string.ensure, (dialog, which) -> {
                    DBManager.deleteAllAccount();
                    Toast.makeText(this, getString(R.string.deleted), Toast.LENGTH_SHORT).show();
                })
                .create()
                .show();
    }
}