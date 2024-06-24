package vtc.oldcookie.paymark.frag_chart;

import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

import vtc.oldcookie.paymark.db.BarChartItemBean;
import vtc.oldcookie.paymark.db.DBManager;

/**
 * Fragment for displaying income chart.
 * LEE Ho Fung
 */
public class IncomChartFragment extends BaseChartFragment {
    int kind = 1;

    /**
     * Creates a BarDataSet from a list of BarChartItemBean.
     *
     * @param list the list of BarChartItemBean
     * @return the created BarDataSet
     */
    @NonNull
    private static BarDataSet getBarDataSet(List<BarChartItemBean> list) {
        List<BarEntry> barEntries1 = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
            BarEntry entry = new BarEntry(i, 0.0f);
            barEntries1.add(entry);
        }
        for (int i = 0; i < list.size(); i++) {
            BarChartItemBean itemBean = list.get(i);
            int day = itemBean.getDay();
            int xIndex = day - 1;
            BarEntry barEntry = barEntries1.get(xIndex);
            barEntry.setY(itemBean.getSummoney());
        }
        return new BarDataSet(barEntries1, "");
    }

    /**
     * Called when the fragment is visible to the user and actively running.
     */
    @Override
    public void onResume() {
        super.onResume();
        loadData(year, month, kind);
    }

    /**
     * Sets the data for the axis.
     *
     * @param year  The year to set.
     * @param month The month to set.
     */
    @Override
    protected void setAxisData(int year, int month) {
        List<IBarDataSet> sets = new ArrayList<>();
        List<BarChartItemBean> list = DBManager.getSumMoneyOneDayInMonth(year, month, kind);
        if (list.isEmpty()) {
            barChart.setVisibility(View.GONE);
            chartTv.setVisibility(View.VISIBLE);
        } else {
            barChart.setVisibility(View.VISIBLE);
            chartTv.setVisibility(View.GONE);
            BarDataSet barDataSet1 = getBarDataSet(list);
            barDataSet1.setValueTextColor(Color.BLACK);
            barDataSet1.setValueTextSize(8f);
            barDataSet1.setColor(Color.parseColor("#006400"));
            barDataSet1.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    if (Math.abs(value - 0f) < 0.000001f) {
                        return "";
                    }
                    return value + "";
                }
            });
            sets.add(barDataSet1);
            BarData barData = new BarData(sets);
            barData.setBarWidth(0.2f);
            barChart.setData(barData);
        }
    }

    /**
     * Sets the Y axis for the chart.
     *
     * @param year  The year to set.
     * @param month The month to set.
     */
    @Override
    protected void setYAxis(int year, int month) {
        float maxMoney = DBManager.getMaxMoneyOneDayInMonth(year, month, kind);
        float max = (float) Math.ceil(maxMoney);
        YAxis yAxis_right = barChart.getAxisRight();
        yAxis_right.setAxisMaximum(max);
        yAxis_right.setAxisMinimum(0f);
        yAxis_right.setEnabled(false);
        YAxis yAxis_left = barChart.getAxisLeft();
        yAxis_left.setAxisMaximum(max);
        yAxis_left.setAxisMinimum(0f);
        yAxis_left.setEnabled(false);
        Legend legend = barChart.getLegend();
        legend.setEnabled(false);
    }

    /**
     * Sets the date for the chart.
     *
     * @param year  The year to set.
     * @param month The month to set.
     */
    @Override
    public void setDate(int year, int month) {
        super.setDate(year, month);
        loadData(year, month, kind);
    }
}