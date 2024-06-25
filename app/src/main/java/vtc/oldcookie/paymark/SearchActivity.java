package vtc.oldcookie.paymark;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


import vtc.oldcookie.paymark.adapter.AccountAdapter;
import vtc.oldcookie.paymark.db.AccountBean;
import vtc.oldcookie.paymark.db.DBManager;

/**
 * Activity class for the Search page.
 */
public class SearchActivity extends AppCompatActivity {
    ListView searchLv;
    EditText searchEt;
    TextView emptyTv;
    List<AccountBean> mDatas;
    AccountAdapter adapter;

    /**
     * Called when the activity is starting.
     * This is where most initialization should go.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchEt = findViewById(R.id.search_et);
        searchLv = findViewById(R.id.search_lv);
        emptyTv = findViewById(R.id.search_tv_empty);
        mDatas = new ArrayList<>();
        adapter = new AccountAdapter(this, mDatas);
        searchLv.setAdapter(adapter);
        searchLv.setEmptyView(emptyTv);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param view The view that was clicked.
     */
    public void onClick(View view) {
        if (view.getId() == R.id.search_iv_back) {
            finish();
        } else if (view.getId() == R.id.search_iv_sh) {
            String msg = searchEt.getText().toString().trim();
            if (TextUtils.isEmpty(msg)) {
                Toast.makeText(this, getString(R.string.cannot_empty), Toast.LENGTH_SHORT).show();
                return;
            }
            mDatas.clear();
            mDatas.addAll(DBManager.getAccountListByRemarkFromAccounttb(msg));
            adapter.notifyDataSetChanged();
        }
    }
}