package vtc.oldcookie.paymark.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vtc.oldcookie.paymark.R;

/**
 * Adapter for displaying calendar data in a GridView.
 */
public class CalendarAdapter extends BaseAdapter {
    public int year;
    public int selPos = -1;
    Context context;
    List<String> mDatas;

    public CalendarAdapter(Context context, int year) {
        this.context = context;
        this.year = year;
        mDatas = new ArrayList<>();
        loadDatas(year);
    }

    public void setYear(int year) {
        this.year = year;
        mDatas.clear();
        loadDatas(year);
        notifyDataSetChanged();
    }

    private void loadDatas(int year) {
        for (int i = 1; i < 13; i++) {
            String data = year + "/" + i;
            mDatas.add(data);
        }
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_dialogcal_gv, parent, false);
        TextView tv = convertView.findViewById(R.id.item_dialogcal_gv_tv);
        tv.setText(mDatas.get(position));
        tv.setBackgroundResource(R.color.grey_f3f3f3);
        tv.setTextColor(Color.BLACK);
        if (position == selPos) {
            tv.setBackgroundResource(R.color.green_006400);
            tv.setTextColor(Color.WHITE);
        }
        return convertView;
    }
}