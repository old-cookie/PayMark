package vtc.oldcookie.paymark.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import vtc.oldcookie.paymark.R;
import vtc.oldcookie.paymark.adapter.CalendarAdapter;
import vtc.oldcookie.paymark.db.DBManager;

/**
 * A dialog for selecting a date from a calendar.
 * LEE Ho Fung
 */
public class CalendarDialog extends Dialog implements View.OnClickListener {
    private List<TextView> hsvViewList;
    private List<Integer> yearList;
    private int selectPos = -1;
    private CalendarAdapter adapter;
    private int selectMonth = -1;
    private OnRefreshListener onRefreshListener;

    /**
     * Constructor for the CalendarDialog.
     *
     * @param context     The context in which the dialog is being used.
     * @param selectPos   The selected position.
     * @param selectMonth The selected month.
     */
    public CalendarDialog(@NonNull Context context, int selectPos, int selectMonth) {
        super(context);
        this.selectPos = selectPos;
        this.selectMonth = selectMonth;
    }

    /**
     * Called when the dialog is first created.
     *
     * @param savedInstanceState If the dialog is being re-created from a previous saved state, this is the state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_calendar);
        findViewById(R.id.dialog_calendar_iv).setOnClickListener(this);
        addViewToLayout();
        initGridView();
        setGVListener();
    }

    /**
     * Sets the listener for the grid view.
     */
    private void setGVListener() {
        ((GridView) findViewById(R.id.dialog_calendar_gv)).setOnItemClickListener((parent, view, position, id) -> {
            adapter.selPos = position;
            adapter.notifyDataSetInvalidated();
            onRefreshListener.onRefresh(selectPos, adapter.year, position + 1);
            cancel();
        });
    }

    /**
     * Initializes the grid view.
     */
    private void initGridView() {
        int selYear = yearList.get(selectPos);
        adapter = new CalendarAdapter(getContext(), selYear);
        adapter.selPos = selectMonth == -1 ? Calendar.getInstance().get(Calendar.MONTH) : selectMonth - 1;
        ((GridView) findViewById(R.id.dialog_calendar_gv)).setAdapter(adapter);
    }

    /**
     * Adds views to the layout.
     */
    private void addViewToLayout() {
        hsvViewList = new ArrayList<>();
        yearList = DBManager.getYearListFromAccounttb();
        if (yearList.isEmpty()) {
            yearList.add(Calendar.getInstance().get(Calendar.YEAR));
        }
        for (int i = 0; i < yearList.size(); i++) {
            View view = getLayoutInflater().inflate(R.layout.item_dialogcal_hsv, null);
            ((LinearLayout) findViewById(R.id.dialog_calendar_layout)).addView(view);
            TextView hsvTv = view.findViewById(R.id.item_dialogcal_hsv_tv);
            hsvTv.setText(String.valueOf(yearList.get(i)));
            hsvViewList.add(hsvTv);
        }
        if (selectPos == -1) {
            selectPos = hsvViewList.size() - 1;
        }
        changeTvbg(selectPos);
        setHSVClickListener();
    }

    /**
     * Sets the click listener for the horizontal scroll view.
     */
    private void setHSVClickListener() {
        for (int i = 0; i < hsvViewList.size(); i++) {
            int finalI = i;
            hsvViewList.get(i).setOnClickListener(v -> {
                changeTvbg(finalI);
                selectPos = finalI;
                adapter.setYear(yearList.get(selectPos));
            });
        }
    }

    /**
     * Changes the background of the text view.
     *
     * @param selectPos The selected position.
     */
    private void changeTvbg(int selectPos) {
        for (TextView tv : hsvViewList) {
            tv.setBackgroundResource(R.drawable.dialog_btn_bg);
            tv.setTextColor(Color.BLACK);
        }
        TextView selView = hsvViewList.get(selectPos);
        selView.setBackgroundResource(R.drawable.main_recordbtn_bg);
        selView.setTextColor(Color.WHITE);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.dialog_calendar_iv) {
            cancel();
        }
    }

    /**
     * Sets the size of the dialog.
     */
    public void setDialogSize() {
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.width = window.getWindowManager().getDefaultDisplay().getWidth();
        wlp.gravity = Gravity.TOP;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes(wlp);
    }

    /**
     * Sets the OnRefreshListener for the dialog.
     *
     * @param onRefreshListener The OnRefreshListener to set.
     */
    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    /**
     * Interface for the OnRefreshListener.
     */
    public interface OnRefreshListener {
        /**
         * Called when the refresh button is clicked.
         *
         * @param selPos The selected position.
         * @param year   The selected year.
         * @param month  The selected month.
         */
        void onRefresh(int selPos, int year, int month);
    }
}