package vtc.oldcookie.paymark.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vtc.oldcookie.paymark.R;


/**
 * Adapter for displaying calendar data in a GridView.
 * CHANG Wing Sze
 */
public class CalendarAdapter extends BaseAdapter {
    private final List<String> mDatas = new ArrayList<>();
    public int selPos = -1;
    public int year;

    /**
     * Constructor for the CalendarAdapter.
     *
     * @param context the context in which the GridView is being displayed
     * @param year    the year for which the calendar data is to be displayed
     */
    public CalendarAdapter(Context context, int year) {
        loadDatas(year);
    }

    /**
     * Method to set the year for which the calendar data is to be displayed.
     *
     * @param year the year for which the calendar data is to be displayed
     */
    public void setYear(int year) {
        mDatas.clear();
        loadDatas(year);
        notifyDataSetChanged();
    }

    /**
     * Method to load the calendar data for a specific year.
     *
     * @param year the year for which the calendar data is to be loaded
     */
    private void loadDatas(int year) {
        for (int i = 1; i < 13; i++) {
            mDatas.add(year + "/" + i);
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

    /**
     * Method for getting the view for an item in the GridView.
     *
     * @param position    the position of the item within the adapter's data set
     * @param convertView the old view to reuse, if possible
     * @param parent      the parent that this view will eventually be attached to
     * @return a View corresponding to the data at the specified position
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_dialogcal_gv, null);
        }
        TextView tv = convertView.findViewById(R.id.item_dialogcal_gv_tv);
        tv.setText(mDatas.get(position));
        tv.setBackgroundResource(R.color.grey_f3f3f3);
        tv.setTextColor(android.graphics.Color.BLACK);
        if (position == selPos) {
            tv.setBackgroundResource(R.color.green_006400);
            tv.setTextColor(android.graphics.Color.WHITE);
        }
        return convertView;
    }
}