package vtc.oldcookie.paymark.frag_record;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vtc.oldcookie.paymark.R;
import vtc.oldcookie.paymark.db.TypeBean;

/**
 * Adapter for the type grid view in the record fragment.
 */
public class TypeBaseAdapter extends BaseAdapter {
    private final Context context;
    private final List<TypeBean> mDatas;
    int selectPos = 0;

    /**
     * Constructor for the TypeBaseAdapter.
     *
     * @param context The context in which the adapter is being used.
     * @param mDatas  The list of TypeBean objects to be displayed.
     */
    public TypeBaseAdapter(Context context, List<TypeBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    /**
     * Gets the count of TypeBean objects.
     *
     * @return The size of the list of TypeBean objects.
     */
    @Override
    public int getCount() {
        return mDatas.size();
    }

    /**
     * Gets the TypeBean object at the specified position.
     *
     * @param position The position of the TypeBean object to get.
     * @return The TypeBean object at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    /**
     * Gets the ID of the item at the specified position.
     *
     * @param position The position of the item to get the ID of.
     * @return The ID of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Gets a view that displays the data at the specified position in the data set.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view we want.
     * @param convertView The old view to reuse, if possible.
     * @param parent      The parent that this view will eventually be attached to.
     * @return A View corresponding to the data at the specified position.
     */

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.item_recordfrag_gv, null);
        ImageView iv = convertView.findViewById(R.id.item_recordfrag_iv);
        TextView tv = convertView.findViewById(R.id.item_recordfrag_tv);
        TypeBean typeBean = mDatas.get(position);
        tv.setText(typeBean.getTypename());
        int imageResId = selectPos == position ? typeBean.getSimageId() : typeBean.getImageId();
        try {
            iv.setImageResource(imageResId);
        } catch (Resources.NotFoundException e) {
            Log.e("TypeBaseAdapter", "Invalid resource ID: " + imageResId);
        }
        return convertView;
    }
}