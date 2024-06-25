package vtc.oldcookie.paymark.utils;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import vtc.oldcookie.paymark.R;

/**
 * Utility class for handling keyboard inputs.
 * LAU Cho Cheuk
 */
public class KeyBoardUtils {
    private final KeyboardView keyboardView;
    private final EditText editText;
    private OnEnsureListener onEnsureListener;

    /**
     * Constructor for the KeyBoardUtils.
     *
     * @param keyboardView The KeyboardView to use.
     * @param editText     The EditText to use.
     */
    public KeyBoardUtils(KeyboardView keyboardView, EditText editText) {
        this.keyboardView = keyboardView;
        this.editText = editText;
        this.editText.setInputType(InputType.TYPE_NULL);
        Keyboard k1 = new Keyboard(this.editText.getContext(), R.xml.key);

        this.keyboardView.setKeyboard(k1);
        this.keyboardView.setEnabled(true);
        this.keyboardView.setPreviewEnabled(false);
        /**
         * Listener for keyboard actions.
         */
        /**
         * Handles key press events.
         *
         * @param primaryCode The unicode of the key pressed.
         * @param keyCodes The key codes of the keys pressed.
         */
        KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
            @Override
            public void onPress(int primaryCode) {
            }

            @Override
            public void onRelease(int primaryCode) {
            }

            /**
             * Handles key press events.
             *
             * @param primaryCode The unicode of the key pressed.
             * @param keyCodes The key codes of the keys pressed.
             */
            @Override
            public void onKey(int primaryCode, int[] keyCodes) {
                Editable editable = editText.getText();
                int start = editText.getSelectionStart();
                switch (primaryCode) {
                    case Keyboard.KEYCODE_DELETE:
                        if (editable != null && editable.length() > 0) {
                            if (start > 0) {
                                editable.delete(start - 1, start);
                            }
                        }
                        break;
                    case Keyboard.KEYCODE_CANCEL:
                        editable.clear();
                        break;
                    case Keyboard.KEYCODE_DONE:
                        onEnsureListener.onEnsure();
                        break;
                    default:
                        editable.insert(start, Character.toString((char) primaryCode));
                        break;
                }
            }

            @Override
            public void onText(CharSequence text) {
            }

            @Override
            public void swipeLeft() {
            }

            @Override
            public void swipeRight() {
            }

            @Override
            public void swipeDown() {
            }

            @Override
            public void swipeUp() {
            }
        };
        this.keyboardView.setOnKeyboardActionListener(listener);
    }

    /**
     * Sets the OnEnsureListener for the keyboard.
     *
     * @param onEnsureListener The OnEnsureListener to set.
     */
    public void setOnEnsureListener(OnEnsureListener onEnsureListener) {
        this.onEnsureListener = onEnsureListener;
    }

    /**
     * Shows the keyboard.
     */
    public void showKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.INVISIBLE || visibility == View.GONE) {
            keyboardView.setVisibility(View.VISIBLE);
        }
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