package vtc.oldcookie.paymark;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import vtc.oldcookie.paymark.adapter.AccountAdapter;
import vtc.oldcookie.paymark.db.AccountBean;
import vtc.oldcookie.paymark.db.DBManager;
import vtc.oldcookie.paymark.utils.CalendarDialog;

/**
 * Activity class for the History page.
 * LEE Ho Fung
 */
public class HistoryActivity extends AppCompatActivity {
    ListView historyLv;
    TextView timeTv;
    List<AccountBean> mDatas;
    AccountAdapter adapter;
    int year, month;
    int dialogSelPos = -1;
    int dialogSelMonth = -1;

    /**
     * Called when the activity is starting.
     * This is where most initialization should go.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        historyLv = findViewById(R.id.history_lv);
        timeTv = findViewById(R.id.history_tv_time);
        mDatas = new ArrayList<>();
        adapter = new AccountAdapter(this, mDatas);
        historyLv.setAdapter(adapter);
        initTime();
        timeTv.setText(getString(R.string.year) + year + getString(R.string.month) + month);
        loadData(year, month);
        setLVClickListener();
    }

    /**
     * Sets the ListView click listener.
     */
    private void setLVClickListener() {
        historyLv.setOnItemLongClickListener((parent, view, position, id) -> {
            deleteItem(mDatas.get(position));
            return false;
        });
    }

    /**
     * Deletes an item from the list.
     *
     * @param accountBean The item to delete.
     */
    private void deleteItem(final AccountBean accountBean) {
        DBManager.deleteItemFromAccounttbById(accountBean.getId());
        mDatas.remove(accountBean);
        adapter.notifyDataSetChanged();
    }

    /**
     * Loads data for a specific year and month.
     *
     * @param year  The year to load data for.
     * @param month The month to load data for.
     */
    private void loadData(int year, int month) {
        mDatas.clear();
        mDatas.addAll(DBManager.getAccountListOneMonthFromAccounttb(year, month));
        adapter.notifyDataSetChanged();
    }

    /**
     * Initializes the time to the current year and month.
     */
    private void initTime() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param view The view that was clicked.
     */
    public void onClick(View view) {
        if (view.getId() == R.id.history_iv_back) {
            finish();
        } else if (view.getId() == R.id.history_iv_rili) {
            CalendarDialog dialog = new CalendarDialog(this, dialogSelPos, dialogSelMonth);
            dialog.show();
            dialog.setDialogSize();
            dialog.setOnRefreshListener((int selPos, int year, int month) -> {
                timeTv.setText(getString(R.string.year) + year + getString(R.string.month) + month);
                loadData(year, month);
                dialogSelPos = selPos;
                dialogSelMonth = month;
            });
        }
    }
}