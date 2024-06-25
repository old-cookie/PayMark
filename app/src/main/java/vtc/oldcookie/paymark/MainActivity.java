package vtc.oldcookie.paymark;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vtc.oldcookie.paymark.adapter.AccountAdapter;
import vtc.oldcookie.paymark.db.AccountBean;
import vtc.oldcookie.paymark.db.DBManager;
import vtc.oldcookie.paymark.utils.BudgetDialog;
import vtc.oldcookie.paymark.utils.MoreDialog;

/**
 * Main activity class for the application.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ListView todayLv;
    TextView topOutTv, topInTv, topbudgetTv, topConTv;
    SharedPreferences preferences;
    List<AccountBean> mDatas;
    AccountAdapter adapter;
    int year, month, day;
    boolean isShow = true;

    /**
     * Called when the activity is starting.
     * This is where most initialization should go.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTime();
        initView();
        preferences = getSharedPreferences("budget", Context.MODE_PRIVATE);
        addLVHeaderView();
        mDatas = new ArrayList<>();
        adapter = new AccountAdapter(this, mDatas);
        todayLv.setAdapter(adapter);
    }

    /**
     * Initializes the view components.
     */
    private void initView() {
        todayLv = findViewById(R.id.main_lv);
        findViewById(R.id.main_btn_edit).setOnClickListener(this);
        findViewById(R.id.main_btn_more).setOnClickListener(this);
        findViewById(R.id.main_iv_search).setOnClickListener(this);
        setLVLongClickListener();
    }

    /**
     * Sets the ListView long click listener.
     */
    private void setLVLongClickListener() {
        todayLv.setOnItemLongClickListener((parent, view, position, id) -> {
            if (position != 0) {
                showDeleteItemDialog(mDatas.get(position - 1));
            }
            return false;
        });
    }

    /**
     * Shows a dialog to delete an item.
     *
     * @param clickBean The item to delete.
     */
    private void showDeleteItemDialog(final AccountBean clickBean) {
        int click_id = clickBean.getId();
        DBManager.deleteItemFromAccounttbById(click_id);
        mDatas.remove(clickBean);
        adapter.notifyDataSetChanged();
        setTopTvShow();
    }

    /**
     * Adds a header view to the ListView.
     */
    private void addLVHeaderView() {
        View headerView = getLayoutInflater().inflate(R.layout.item_mainlv_top, null);
        todayLv.addHeaderView(headerView);
        topOutTv = headerView.findViewById(R.id.item_mainlv_top_tv_out);
        topInTv = headerView.findViewById(R.id.item_mainlv_top_tv_in);
        topbudgetTv = headerView.findViewById(R.id.item_mainlv_top_tv_budget);
        topConTv = headerView.findViewById(R.id.item_mainlv_top_tv_day);
        topbudgetTv.setOnClickListener(this);
        headerView.setOnClickListener(this);
        findViewById(R.id.item_mainlv_top_iv_hide).setOnClickListener(this);
    }

    /**
     * Initializes the time to the current year and month.
     */
    private void initTime() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Called when the activity is resuming after being paused or stopped.
     */
    @Override
    protected void onResume() {
        super.onResume();
        loadDBData();
        setTopTvShow();
    }

    /**
     * Sets the top TextViews to show the correct data.
     */
    void setTopTvShow() {
        float incomeOneDay = DBManager.getSumMoneyOneDay(year, month, day, 1);
        float outcomeOneDay = DBManager.getSumMoneyOneDay(year, month, day, 0);
        String infoOneDay = getString(R.string.today_expenses) + outcomeOneDay + getString(R.string.income_main) + incomeOneDay;
        topConTv.setText(infoOneDay);
        float incomeOneMonth = DBManager.getSumMoneyOneMonth(year, month, 1);
        float outcomeOneMonth = DBManager.getSumMoneyOneMonth(year, month, 0);
        topInTv.setText("$" + incomeOneMonth);
        topOutTv.setText("$" + outcomeOneMonth);
        float bmoney = preferences.getFloat("bmoney", 0);
        if (bmoney != 0) {
            float syMoney = bmoney - outcomeOneMonth;
            topbudgetTv.setText("$" + syMoney);
        }
    }

    /**
     * Loads data from the database.
     */
    private void loadDBData() {
        List<AccountBean> list = DBManager.getAccountListOneDayFromAccounttb(year, month, day);
        mDatas.clear();
        mDatas.addAll(list);
        adapter.notifyDataSetChanged();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.main_iv_search) {
            startActivity(new Intent(this, SearchActivity.class));
        } else if (viewId == R.id.main_btn_edit) {
            startActivity(new Intent(this, RecordActivity.class));
        } else if (viewId == R.id.main_btn_more) {
            MoreDialog moreDialog = new MoreDialog(this);
            moreDialog.show();
            moreDialog.setDialogSize();
        } else if (viewId == R.id.item_mainlv_top_tv_budget) {
            showBudgetDialog();
        } else if (viewId == R.id.item_mainlv_top_iv_hide) {
            toggleShow();
        }

    }

    /**
     * Shows a dialog to set the budget.
     */
    private void showBudgetDialog() {
        BudgetDialog dialog = new BudgetDialog(this);
        dialog.show();
        dialog.setDialogSize();
        dialog.setOnEnsureListener(money -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putFloat("money", money);
            editor.commit();
            float outcomeOneMonth = DBManager.getSumMoneyOneMonth(year, month, 0);
            float syMoney = money - outcomeOneMonth;
            topbudgetTv.setText("$" + syMoney);
        });
    }

    /**
     * Toggles the visibility of the budget, income, and expenses.
     */
    void toggleShow() {
        if (isShow) {
            topInTv.setTransformationMethod(PasswordTransformationMethod.getInstance());
            topOutTv.setTransformationMethod(PasswordTransformationMethod.getInstance());
            topbudgetTv.setTransformationMethod(PasswordTransformationMethod.getInstance());
            ((ImageView) findViewById(R.id.item_mainlv_top_iv_hide)).setImageResource(R.mipmap.ih_hide);
            isShow = false;
        } else {
            topInTv.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            topOutTv.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            topbudgetTv.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            ((ImageView) findViewById(R.id.item_mainlv_top_iv_hide)).setImageResource(R.mipmap.ih_show);
            isShow = true;
        }
    }
}