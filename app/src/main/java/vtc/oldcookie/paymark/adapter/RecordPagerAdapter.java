package vtc.oldcookie.paymark.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

import vtc.oldcookie.paymark.R;


/**
 * Adapter for managing fragments in a ViewPager with titles.
 * CHANG Wing Sze
 */
public class RecordPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> fragmentList;
    private final String[] titles;

    /**
     * Constructor for the RecordPagerAdapter.
     *
     * @param fm           the FragmentManager that will interact with this adapter
     * @param fragmentList the list of Fragments to be displayed in the ViewPager
     * @param context      the context in which the ViewPager is being displayed
     */
    public RecordPagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList, Context context) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragmentList = fragmentList;
        this.titles = new String[]{context.getString(R.string.out), context.getString(R.string.in)};
    }

    /**
     * Method to get the Fragment at a given position.
     *
     * @param position the position of the Fragment in the list
     * @return the Fragment at the given position
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    /**
     * Method to get the count of the Fragments.
     *
     * @return the size of the list of Fragments
     */
    @Override
    public int getCount() {
        return fragmentList.size();
    }

    /**
     * Method to get the title for the page at a given position.
     *
     * @param position the position of the page
     * @return the title of the page at the given position
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}