package vtc.oldcookie.paymark.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import vtc.oldcookie.paymark.AboutActivity;
import vtc.oldcookie.paymark.HistoryActivity;
import vtc.oldcookie.paymark.MonthChartActivity;
import vtc.oldcookie.paymark.R;
import vtc.oldcookie.paymark.SettingActivity;

/**
 * A dialog for displaying more options.
 */
public class MoreDialog extends Dialog implements View.OnClickListener {

    /**
     * Constructor for the MoreDialog.
     *
     * @param context The context in which the dialog is being used.
     */
    public MoreDialog(@NonNull Context context) {
        super(context);
    }

    /**
     * Called when the dialog is first created.
     *
     * @param savedInstanceState If the dialog is being re-created from a previous saved state, this is the state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_more);

        findViewById(R.id.dialog_more_btn_about).setOnClickListener(this);
        findViewById(R.id.dialog_more_btn_setting).setOnClickListener(this);
        findViewById(R.id.dialog_more_btn_record).setOnClickListener(this);
        findViewById(R.id.dialog_more_btn_info).setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        int id = v.getId();
        if (id == R.id.dialog_more_btn_about) {
            intent.setClass(getContext(), AboutActivity.class);
        } else if (id == R.id.dialog_more_btn_setting) {
            intent.setClass(getContext(), SettingActivity.class);
        } else if (id == R.id.dialog_more_btn_record) {
            intent.setClass(getContext(), HistoryActivity.class);
        } else if (id == R.id.dialog_more_btn_info) {
            intent.setClass(getContext(), MonthChartActivity.class);
        }
        getContext().startActivity(intent);
        cancel();
    }

    /**
     * Sets the size of the dialog.
     */
    public void setDialogSize() {
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        Display d = window.getWindowManager().getDefaultDisplay();
        wlp.width = d.getWidth();
        wlp.gravity = Gravity.BOTTOM;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes(wlp);
    }
}