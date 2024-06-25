package vtc.oldcookie.paymark.frag_record;

import android.annotation.SuppressLint;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import vtc.oldcookie.paymark.R;
import vtc.oldcookie.paymark.db.AccountBean;
import vtc.oldcookie.paymark.db.TypeBean;
import vtc.oldcookie.paymark.utils.CommentDialog;
import vtc.oldcookie.paymark.utils.KeyBoardUtils;
import vtc.oldcookie.paymark.utils.SelectTimeDialog;

/**
 * Base class for record fragments.
 */
public abstract class BaseRecordFragment extends Fragment implements View.OnClickListener {

    KeyboardView keyboardView;
    EditText moneyEt;
    ImageView typeIv;
    TextView typeTv, commentTv, timeTv;
    GridView typeGv;
    List<TypeBean> typeList;
    TypeBaseAdapter adapter;
    AccountBean accountBean;

    /**
     * Called when the fragment is first created.
     *
     * @param savedInstanceState If the fragment is being re-created from a previous saved state, this is the state.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountBean = new AccountBean();
        accountBean.setTypename(requireContext().getString(R.string.type_other));
        accountBean.setsImageId(R.mipmap.ic_other_fs);
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container          If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_outcome, container, false);
        initView(view);
        setInitTime();
        loadDataToGV();
        setGVListener();
        return view;
    }

    /**
     * Sets the initial time for the account.
     */
    private void setInitTime() {
        Date date = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String time = sdf.format(date);
        timeTv.setText(time);
        accountBean.setTime(time);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        accountBean.setYear(year);
        accountBean.setMonth(month);
        accountBean.setDay(day);
    }

    /**
     * Sets the listener for the grid view.
     */
    private void setGVListener() {
        typeGv.setOnItemClickListener((parent, view, position, id) -> {
            adapter.selectPos = position;
            adapter.notifyDataSetInvalidated();
            TypeBean typeBean = typeList.get(position);
            String typename = typeBean.getTypename();
            typeTv.setText(typename);
            accountBean.setTypename(typename);
            int simageId = typeBean.getSimageId();
            typeIv.setImageResource(simageId);
            accountBean.setsImageId(simageId);
        });
    }

    /**
     * Loads data to the grid view.
     */
    public void loadDataToGV() {
        typeList = new ArrayList<>();
        adapter = new TypeBaseAdapter(getContext(), typeList);
        typeGv.setAdapter(adapter);
    }

    /**
     * Initializes the view.
     *
     * @param view The view to initialize.
     */
    private void initView(View view) {
        keyboardView = view.findViewById(R.id.frag_record_keyboard);
        moneyEt = view.findViewById(R.id.frag_record_et_money);
        typeIv = view.findViewById(R.id.frag_record_iv);
        typeGv = view.findViewById(R.id.frag_record_gv);
        typeTv = view.findViewById(R.id.frag_record_tv_type);
        commentTv = view.findViewById(R.id.frag_record_tv_comment);
        timeTv = view.findViewById(R.id.frag_record_tv_time);
        commentTv.setOnClickListener(this);
        timeTv.setOnClickListener(this);
        KeyBoardUtils boardUtils = new KeyBoardUtils(keyboardView, moneyEt);
        boardUtils.showKeyboard();
        boardUtils.setOnEnsureListener(() -> {
            String moneyStr = moneyEt.getText().toString();
            if (TextUtils.isEmpty(moneyStr) || moneyStr.equals("0")) {
                getActivity().finish();
                return;
            }
            float money = Float.parseFloat(moneyStr);
            accountBean.setMoney(money);
            saveAccountToDB();
            getActivity().finish();
        });
    }

    /**
     * Saves the account to the database.
     */
    public abstract void saveAccountToDB();

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.frag_record_tv_time) {
            showTimeDialog();
        } else if (viewId == R.id.frag_record_tv_comment) {
            showBZDialog();
        }
    }

    /**
     * Shows the time dialog.
     */
    private void showTimeDialog() {
        SelectTimeDialog dialog = new SelectTimeDialog(getContext());
        dialog.show();
        dialog.setOnEnsureListener((time, year, month, day) -> {
            timeTv.setText(time);
            accountBean.setTime(time);
            accountBean.setYear(year);
            accountBean.setMonth(month);
            accountBean.setDay(day);
        });
    }

    /**
     * Shows the comment dialog.
     */
    public void showBZDialog() {
        final CommentDialog dialog = new CommentDialog(getContext());
        dialog.show();
        dialog.setDialogSize();
        dialog.setOnEnsureListener(() -> {
            String msg = dialog.getEditText();
            if (!TextUtils.isEmpty(msg)) {
                commentTv.setText(msg);
                accountBean.setComment(msg);
            }
            dialog.cancel();
        });
    }
}