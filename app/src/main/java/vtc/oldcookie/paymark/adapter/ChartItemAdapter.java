package vtc.oldcookie.paymark.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vtc.oldcookie.paymark.R;
import vtc.oldcookie.paymark.db.ChartItemBean;

/**
 * Adapter for displaying chart items in a ListView.
 */
public class ChartItemAdapter extends BaseAdapter {
    private final List<ChartItemBean> mDatas;

    /**
     * Constructor for the ChartItemAdapter.
     *
     * @param context the context in which the ListView is being displayed
     * @param mDatas  the data to be displayed in the ListView
     */
    public ChartItemAdapter(Context context, List<ChartItemBean> mDatas) {
        this.mDatas = mDatas;
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
     * Method for getting the view for an item in the ListView.
     *
     * @param position    the position of the item within the adapter's data set
     * @param convertView the old view to reuse, if possible
     * @param parent      the parent that this view will eventually be attached to
     * @return a View corresponding to the data at the specified position
     */
    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_chartfrag_lv, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ChartItemBean bean = mDatas.get(position);
        holder.iv.setImageResource(bean.getsImageId());
        holder.typeTv.setText(bean.getType());
        holder.ratioTv.setText(String.format("%.2f%%", bean.getRatio() * 100));
        holder.totalTv.setText(String.format("$ %.2f", bean.getTotalMoney()));
        return convertView;
    }

    /**
     * Class for holding the views within a ListView item.
     */
    static class ViewHolder {
        final TextView typeTv, ratioTv, totalTv;
        final ImageView iv;

        /**
         * Constructor for the ViewHolder.
         *
         * @param view the view containing the ListView item
         */
        ViewHolder(View view) {
            typeTv = view.findViewById(R.id.item_chartfrag_tv_type);
            ratioTv = view.findViewById(R.id.item_chartfrag_tv_pert);
            totalTv = view.findViewById(R.id.item_chartfrag_tv_sum);
            iv = view.findViewById(R.id.item_chartfrag_iv);
        }
    }
}