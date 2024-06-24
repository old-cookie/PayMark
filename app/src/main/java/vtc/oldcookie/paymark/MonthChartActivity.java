package vtc.oldcookie.paymark;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import vtc.oldcookie.paymark.adapter.ChartVPAdapter;
import vtc.oldcookie.paymark.db.DBManager;
import vtc.oldcookie.paymark.frag_chart.IncomChartFragment;
import vtc.oldcookie.paymark.frag_chart.OutcomeChartFragment;
import vtc.oldcookie.paymark.utils.CalendarDialog;

/**
 * Activity class for the Month Chart page.
 * LEE Ho Fung
 */
public class MonthChartActivity extends AppCompatActivity implements View.OnClickListener {
    Button inBtn, outBtn;
    TextView dateTv, inTv, outTv;
    ViewPager chartVp;
    int year, month;
    List<Fragment> chartFragList;
    IncomChartFragment incomChartFragment;
    OutcomeChartFragment outcomChartFragment;

    /**
     * Called when the activity is starting.
     * This is where most initialization should go.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_chart);
        initView();
        initTime();
        initStatistics(year, month);
        initFrag();
        chartVp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                setButtonStyle(position);
            }
        });
    }

    /**
     * Initializes the fragments for the ViewPager.
     */
    private void initFrag() {
        chartFragList = new ArrayList<>();
        incomChartFragment = new IncomChartFragment();
        outcomChartFragment = new OutcomeChartFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("year", year);
        bundle.putInt("month", month);
        incomChartFragment.setArguments(bundle);
        outcomChartFragment.setArguments(bundle);
        chartFragList.add(outcomChartFragment);
        chartFragList.add(incomChartFragment);
        ChartVPAdapter chartVPAdapter = new ChartVPAdapter(getSupportFragmentManager(), chartFragList);
        chartVp.setAdapter(chartVPAdapter);
    }

    /**
     * Initializes the statistics for a specific year and month.
     *
     * @param year  The year to initialize statistics for.
     * @param month The month to initialize statistics for.
     */
    private void initStatistics(int year, int month) {
        float inMoneyOneMonth = DBManager.getSumMoneyOneMonth(year, month, 1);
        float outMoneyOneMonth = DBManager.getSumMoneyOneMonth(year, month, 0);
        int incountItemOneMonth = DBManager.getCountItemOneMonth(year, month, 1);
        int outcountItemOneMonth = DBManager.getCountItemOneMonth(year, month, 0);
        dateTv.setText(getString(R.string.year) + year + getString(R.string.month) + month + getString(R.string.bill));
        inTv.setText(getString(R.string.total) + incountItemOneMonth + getString(R.string.total2) + inMoneyOneMonth);
        outTv.setText(getString(R.string.total) + outcountItemOneMonth + getString(R.string.total3) + outMoneyOneMonth);
    }

    /**
     * Initializes the time to the current year and month.
     */
    private void initTime() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * Initializes the view components.
     */
    private void initView() {
        inBtn = findViewById(R.id.chart_btn_in);
        outBtn = findViewById(R.id.chart_btn_out);
        dateTv = findViewById(R.id.chart_tv_date);
        inTv = findViewById(R.id.chart_tv_in);
        outTv = findViewById(R.id.chart_tv_out);
        chartVp = findViewById(R.id.chart_vp);
        inBtn.setOnClickListener(this);
        outBtn.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param view The view that was clicked.
     */
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.chart_iv_back) {
            finish();
        } else if (viewId == R.id.chart_iv_rili) {
            showCalendarDialog();
        } else if (viewId == R.id.chart_btn_in) {
            setButtonStyle(1);
            chartVp.setCurrentItem(1);
        } else if (viewId == R.id.chart_btn_out) {
            setButtonStyle(0);
            chartVp.setCurrentItem(0);
        }
    }

    /**
     * Shows a dialog to select a date.
     */
    private void showCalendarDialog() {
        CalendarDialog dialog = new CalendarDialog(this, year, month);
        dialog.show();
        dialog.setDialogSize();
        dialog.setOnRefreshListener((selPos, year, month) -> {
            initStatistics(year, month);
            incomChartFragment.setDate(year, month);
            outcomChartFragment.setDate(year, month);
        });
    }

    /**
     * Sets the style of the buttons based on the selected kind.
     *
     * @param kind The kind of button (0 for out, 1 for in).
     */
    private void setButtonStyle(int kind) {
        if (kind == 0) {
            outBtn.setBackgroundResource(R.drawable.main_recordbtn_bg);
            outBtn.setTextColor(Color.WHITE);
            inBtn.setBackgroundResource(R.drawable.dialog_btn_bg);
            inBtn.setTextColor(Color.BLACK);
        } else if (kind == 1) {
            inBtn.setBackgroundResource(R.drawable.main_recordbtn_bg);
            inBtn.setTextColor(Color.WHITE);
            outBtn.setBackgroundResource(R.drawable.dialog_btn_bg);
            outBtn.setTextColor(Color.BLACK);
        }
    }
}