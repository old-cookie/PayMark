package vtc.oldcookie.paymark.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Adapter for managing fragments in a ViewPager.
 * CHANG Wing Sze
 */
public class ChartVPAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList;

    /**
     * Constructor for the ChartVPAdapter.
     *
     * @param fm           the FragmentManager that will interact with this adapter
     * @param fragmentList the list of Fragments to be displayed in the ViewPager
     */
    public ChartVPAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragmentList = fragmentList;
    }

    /**
     * Method to get the Fragment at a given position.
     *
     * @param position the position of the Fragment in the list
     * @return the Fragment at the given position
     */
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
}