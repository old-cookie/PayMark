package vtc.oldcookie.paymark.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;

import vtc.oldcookie.paymark.R;

/**
 * A dialog for selecting time in the record page.
 */
public class SelectTimeDialog extends Dialog implements View.OnClickListener {
    EditText hourEt, minuteEt;
    DatePicker datePicker;
    Button ensureBtn, cancelBtn;
    OnEnsureListener onEnsureListener;

    /**
     * Constructor for the SelectTimeDialog.
     *
     * @param context The context in which the dialog is being used.
     */
    public SelectTimeDialog(@NonNull Context context) {
        super(context);
    }

    /**
     * Sets the OnEnsureListener for the dialog.
     *
     * @param onEnsureListener The OnEnsureListener to set.
     */
    public void setOnEnsureListener(OnEnsureListener onEnsureListener) {
        this.onEnsureListener = onEnsureListener;
    }

    /**
     * Called when the dialog is first created.
     *
     * @param savedInstanceState If the dialog is being re-created from a previous saved state, this is the state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_time);
        hourEt = findViewById(R.id.dialog_time_et_hour);
        minuteEt = findViewById(R.id.dialog_time_et_minute);
        datePicker = findViewById(R.id.dialog_time_dp);
        ensureBtn = findViewById(R.id.dialog_time_btn_ensure);
        cancelBtn = findViewById(R.id.dialog_time_btn_cancel);
        ensureBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        hideDatePickerHeader();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.dialog_time_btn_cancel) {
            cancel();
        } else if (viewId == R.id.dialog_time_btn_ensure) {
            int year = datePicker.getYear();
            int month = datePicker.getMonth() + 1;
            int dayOfMonth = datePicker.getDayOfMonth();
            String monthStr = String.valueOf(month);
            if (month < 10) {
                monthStr = "0" + month;
            }
            String dayStr = String.valueOf(dayOfMonth);
            if (dayOfMonth < 10) {
                dayStr = "0" + dayOfMonth;
            }
            String hourStr = hourEt.getText().toString();
            String minuteStr = minuteEt.getText().toString();
            int hour = 0;
            if (!TextUtils.isEmpty(hourStr)) {
                hour = Integer.parseInt(hourStr);
                hour = hour % 24;
            }
            int minute = 0;
            if (!TextUtils.isEmpty(minuteStr)) {
                minute = Integer.parseInt(minuteStr);
                minute = minute % 60;
            }

            hourStr = String.valueOf(hour);
            minuteStr = String.valueOf(minute);
            if (hour < 10) {
                hourStr = "0" + hour;
            }
            if (minute < 10) {
                minuteStr = "0" + minute;
            }
            String timeFormat = year + "年" + monthStr + "月" + dayStr + "日 " + hourStr + ":" + minuteStr;
            if (onEnsureListener != null) {
                onEnsureListener.onEnsure(timeFormat, year, month, dayOfMonth);
            }
            cancel();
        }
    }

    /**
     * Hides the header of the DatePicker.
     */
    private void hideDatePickerHeader() {
        ViewGroup rootView = (ViewGroup) datePicker.getChildAt(0);
        if (rootView == null) {
            return;
        }
        View headerView = rootView.getChildAt(0);
        if (headerView == null) {
            return;
        }

        int headerId = getContext().getResources().getIdentifier("day_picker_selector_layout", "id", "android");
        if (headerId == headerView.getId()) {
            headerView.setVisibility(View.GONE);
            ViewGroup.LayoutParams layoutParamsRoot = rootView.getLayoutParams();
            layoutParamsRoot.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            rootView.setLayoutParams(layoutParamsRoot);

            ViewGroup animator = (ViewGroup) rootView.getChildAt(1);
            ViewGroup.LayoutParams layoutParamsAnimator = animator.getLayoutParams();
            layoutParamsAnimator.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            animator.setLayoutParams(layoutParamsAnimator);

            View child = animator.getChildAt(0);
            ViewGroup.LayoutParams layoutParamsChild = child.getLayoutParams();
            layoutParamsChild.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            child.setLayoutParams(layoutParamsChild);
            return;
        }


        headerId = getContext().getResources().getIdentifier("date_picker_header", "id", "android");
        if (headerId == headerView.getId()) {
            headerView.setVisibility(View.GONE);
        }
    }

    /**
     * Interface for the OnEnsureListener.
     */
    public interface OnEnsureListener {
        /**
         * Called when the ensure button is clicked.
         *
         * @param time  The selected time.
         * @param year  The selected year.
         * @param month The selected month.
         * @param day   The selected day.
         */
        void onEnsure(String time, int year, int month, int day);
    }
}