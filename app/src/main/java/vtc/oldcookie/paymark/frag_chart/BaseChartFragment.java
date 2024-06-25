package vtc.oldcookie.paymark.frag_chart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

import vtc.oldcookie.paymark.R;
import vtc.oldcookie.paymark.adapter.ChartItemAdapter;
import vtc.oldcookie.paymark.db.ChartItemBean;
import vtc.oldcookie.paymark.db.DBManager;

/**
 * Base class for chart fragments.
 */
abstract public class BaseChartFragment extends Fragment {
    ListView chartLv;
    int year;
    int month;
    List<ChartItemBean> mDatas;
    BarChart barChart;
    TextView chartTv;
    private ChartItemAdapter itemAdapter;

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container          If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_incom_chart, container, false);
        chartLv = view.findViewById(R.id.frag_chart_lv);
        Bundle bundle = getArguments();
        year = bundle.getInt("year");
        month = bundle.getInt("month");
        mDatas = new ArrayList<>();
        itemAdapter = new ChartItemAdapter(getContext(), mDatas);
        chartLv.setAdapter(itemAdapter);
        addLVHeaderView();
        return view;
    }

    /**
     * Adds a header view to the list view.
     */
    protected void addLVHeaderView() {
        View headerView = getLayoutInflater().inflate(R.layout.item_chartfrag_top, null);
        chartLv.addHeaderView(headerView);
        barChart = headerView.findViewById(R.id.item_chartfrag_chart);
        chartTv = headerView.findViewById(R.id.item_chartfrag_top_tv);
        barChart.getDescription().setEnabled(false);
        barChart.setExtraOffsets(20, 20, 20, 20);
        setAxis(year, month);
        setAxisData(year, month);
    }

    /**
     * Sets the data for the axis.
     *
     * @param year  The year to set.
     * @param month The month to set.
     */
    protected abstract void setAxisData(int year, int month);

    /**
     * Sets the axis for the chart.
     *
     * @param year  The year to set.
     * @param month The month to set.
     */
    protected void setAxis(int year, final int month) {
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setLabelCount(31);
        xAxis.setTextSize(12f);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int val = Math.round(value);
                if (val == 0) {
                    return month + "-1";
                }
                if (val == 14) {
                    return month + "-15";
                }
                if (month == 2) {
                    if (val == 27) {
                        return month + "-28";
                    }
                } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                    if (val == 30) {
                        return month + "-31";
                    }
                } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                    if (val == 29) {
                        return month + "-30";
                    }
                }
                return "";
            }
        });
        xAxis.setYOffset(10);
        setYAxis(year, month);
    }

    /**
     * Sets the Y axis for the chart.
     *
     * @param year  The year to set.
     * @param month The month to set.
     */
    protected abstract void setYAxis(int year, int month);

    /**
     * Sets the date for the chart.
     *
     * @param year  The year to set.
     * @param month The month to set.
     */
    public void setDate(int year, int month) {
        this.year = year;
        this.month = month;
        barChart.clear();
        barChart.invalidate();
        setAxis(year, month);
        setAxisData(year, month);
    }

    /**
     * Loads data for the chart.
     *
     * @param year  The year of the data.
     * @param month The month of the data.
     * @param kind  The kind of the data.
     */
    public void loadData(int year, int month, int kind) {
        List<ChartItemBean> list = DBManager.getChartListFromAccounttb(year, month, kind);
        mDatas.clear();
        mDatas.addAll(list);
        itemAdapter.notifyDataSetChanged();
    }
}