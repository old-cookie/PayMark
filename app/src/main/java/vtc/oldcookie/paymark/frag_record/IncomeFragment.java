package vtc.oldcookie.paymark.frag_record;


import vtc.oldcookie.paymark.R;
import vtc.oldcookie.paymark.db.DBManager;

/**
 * Fragment for recording income.
 * LAU Cho Cheuk
 */
public class IncomeFragment extends BaseRecordFragment {

    /**
     * Loads data to the grid view.
     * This method is called to populate the grid view with the types of income.
     */
    @Override
    public void loadDataToGV() {
        super.loadDataToGV();
        // Add all income types to the list
        typeList.addAll(DBManager.getTypeList(1));
        // Notify the adapter that the data set has changed
        adapter.notifyDataSetChanged();
        // Set the default type to "Other"
        typeTv.setText(R.string.type_other);
        // Set the default image for the type
        typeIv.setImageResource(R.mipmap.in_other_chose);
    }

    /**
     * Saves the account to the database.
     * This method is called to save the current account details as an income record in the database.
     */
    @Override
    public void saveAccountToDB() {
        // Set the kind of the account to income (1)
        accountBean.setKind(1);
        // Insert the account into the database
        DBManager.insertItemToAccounttb(accountBean);
    }
}