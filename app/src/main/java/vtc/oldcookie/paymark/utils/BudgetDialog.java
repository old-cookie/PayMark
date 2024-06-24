package vtc.oldcookie.paymark.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;

import vtc.oldcookie.paymark.R;


/**
 * A dialog for setting the budget.
 * LAU Cho Cheuk
 */
public class BudgetDialog extends Dialog implements View.OnClickListener {
    private OnEnsureListener onEnsureListener;

    /**
     * Constructor for the BudgetDialog.
     *
     * @param context The context in which the dialog is being used.
     */
    public BudgetDialog(@NonNull Context context) {
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
        setContentView(R.layout.dialog_budget);
        findViewById(R.id.dialog_budget_iv_error).setOnClickListener(this);
        findViewById(R.id.dialog_budget_btn_ensure).setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.dialog_budget_iv_error) {
            cancel();
        } else if (v.getId() == R.id.dialog_budget_btn_ensure) {
            String data = ((android.widget.EditText) findViewById(R.id.dialog_budget_et)).getText().toString();
            if (TextUtils.isEmpty(data)) {
                Toast.makeText(getContext(), R.string.cannot_empty, Toast.LENGTH_SHORT).show();
                return;
            }
            float money = Float.parseFloat(data);
            if (money <= 0) {
                Toast.makeText(getContext(), R.string.budget_more_than_0, Toast.LENGTH_SHORT).show();
                return;
            }
            if (onEnsureListener != null) {
                onEnsureListener.onEnsure(money);
            }
            cancel();
        }
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
     * Sets the size of the dialog.
     */
    public void setDialogSize() {
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.width = window.getWindowManager().getDefaultDisplay().getWidth();
        wlp.gravity = android.view.Gravity.BOTTOM;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes(wlp);
        new android.os.Handler().postDelayed(() -> {
            ((android.view.inputmethod.InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                    .toggleSoftInput(0, android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS);
        }, 100);
    }

    /**
     * Interface for the OnEnsureListener.
     */
    public interface OnEnsureListener {
        /**
         * Called when the ensure button is clicked.
         *
         * @param money The budget that was set.
         */
        void onEnsure(float money);
    }
}