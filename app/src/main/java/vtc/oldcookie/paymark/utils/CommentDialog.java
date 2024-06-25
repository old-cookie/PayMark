package vtc.oldcookie.paymark.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;

import vtc.oldcookie.paymark.R;

/**
 * A dialog for entering comments.
 */
public class CommentDialog extends Dialog implements View.OnClickListener {
    private EditText et;
    private OnEnsureListener onEnsureListener;

    /**
     * Constructor for the CommentDialog.
     *
     * @param context The context in which the dialog is being used.
     */
    public CommentDialog(@NonNull Context context) {
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
        setContentView(R.layout.dialog_comment);
        et = findViewById(R.id.dialog_comment_et);
        findViewById(R.id.dialog_comment_btn_cancel).setOnClickListener(this);
        findViewById(R.id.dialog_comment_btn_ensure).setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.dialog_comment_btn_cancel) {
            cancel();
        } else if (v.getId() == R.id.dialog_comment_btn_ensure && onEnsureListener != null) {
            onEnsureListener.onEnsure();
        }
    }

    /**
     * Gets the text from the EditText field.
     *
     * @return The text from the EditText field.
     */
    public String getEditText() {
        return et.getText().toString().trim();
    }

    /**
     * Sets the size of the dialog.
     */
    public void setDialogSize() {
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.width = window.getWindowManager().getDefaultDisplay().getWidth();
        wlp.gravity = Gravity.BOTTOM;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes(wlp);
        ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
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
     * Interface for the OnEnsureListener.
     */
    public interface OnEnsureListener {
        /**
         * Called when the ensure button is clicked.
         */
        void onEnsure();
    }
}