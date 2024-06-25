package vtc.oldcookie.paymark.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vtc.oldcookie.paymark.R;
import vtc.oldcookie.paymark.db.AccountBean;

/**
 * Adapter for displaying account information in a ListView.
 */
public class AccountAdapter extends BaseAdapter {
    private final List<AccountBean> mDatas;
    private final LayoutInflater inflater;

    /**
     * Constructor for the AccountAdapter.
     *
     * @param context the context in which the ListView is being displayed
     * @param mDatas  the data to be displayed in the ListView
     */
    public AccountAdapter(Context context, List<AccountBean> mDatas) {
        this.mDatas = mDatas;
        inflater = LayoutInflater.from(context);
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
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_mainlv, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        AccountBean bean;
        bean = mDatas.get(position);
        holder.typeIv.setImageResource(bean.getsImageId());
        holder.typeTv.setText(bean.getTypename());
        holder.commentTv.setText(bean.getComment());
        holder.moneyTv.setText("$ " + bean.getMoney());
        holder.timeTv.setText(bean.getTime());
        return convertView;
    }

    /**
     * Class for holding the views within a ListView item.
     */
    class ViewHolder {
        ImageView typeIv;
        TextView typeTv, commentTv, timeTv, moneyTv;

        /**
         * Constructor for the ViewHolder.
         *
         * @param view the view containing the ListView item
         */
        public ViewHolder(View view) {
            typeIv = view.findViewById(R.id.item_mainlv_iv);
            typeTv = view.findViewById(R.id.item_mainlv_tv_title);
            timeTv = view.findViewById(R.id.item_mainlv_tv_time);
            commentTv = view.findViewById(R.id.item_mainlv_tv_comment);
            moneyTv = view.findViewById(R.id.item_mainlv_tv_money);
        }
    }
}