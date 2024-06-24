package vtc.oldcookie.paymark;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.Arrays;
import java.util.List;

import vtc.oldcookie.paymark.adapter.RecordPagerAdapter;
import vtc.oldcookie.paymark.frag_record.IncomeFragment;
import vtc.oldcookie.paymark.frag_record.OutcomeFragment;

/**
 * Activity class for the Record page.
 * CHANG Wing Sze
 */
public class RecordActivity extends AppCompatActivity {
    /**
     * Called when the activity is starting.
     * This is where most initialization should go.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        TabLayout tabLayout = findViewById(R.id.record_tabs);
        ViewPager viewPager = findViewById(R.id.record_vp);
        // Create a list of fragments for the ViewPager
        List<Fragment> fragmentList = Arrays.asList(new OutcomeFragment(), new IncomeFragment());
        // Set the adapter for the ViewPager
        viewPager.setAdapter(new RecordPagerAdapter(getSupportFragmentManager(), fragmentList, this));
        // Connect the TabLayout with the ViewPager
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param view The view that was clicked.
     */
    public void onClick(View view) {
        // If the back button is clicked, finish the activity
        if (view.getId() == R.id.record_iv_back) {
            finish();
        }
    }
}