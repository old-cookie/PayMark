package vtc.oldcookie.paymark.frag_record;

import java.util.List;

import vtc.oldcookie.paymark.R;
import vtc.oldcookie.paymark.db.DBManager;
import vtc.oldcookie.paymark.db.TypeBean;

/**
 * Fragment for recording outcome.
 */
public class OutcomeFragment extends BaseRecordFragment {

    /**
     * Loads data to the grid view.
     * This method is called to populate the grid view with the types of outcome.
     */
    @Override
    public void loadDataToGV() {
        super.loadDataToGV();
        // Get the list of outcome types from the database
        List<TypeBean> outlist = DBManager.getTypeList(0);
        // Add all outcome types to the list
        typeList.addAll(outlist);
        // Notify the adapter that the data set has changed
        adapter.notifyDataSetChanged();
        // Set the default type to "Other"
        typeTv.setText(R.string.type_other);
        // Set the default image for the type
        typeIv.setImageResource(R.mipmap.ic_other_fs);
    }

    /**
     * Saves the account to the database.
     * This method is called to save the current account details as an outcome record in the database.
     */
    @Override
    public void saveAccountToDB() {
        // Set the kind of the account to outcome (0)
        accountBean.setKind(0);
        // Insert the account into the database
        DBManager.insertItemToAccounttb(accountBean);
    }
}