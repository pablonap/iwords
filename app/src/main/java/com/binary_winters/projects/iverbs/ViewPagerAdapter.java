package com.binary_winters.projects.iverbs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.binary_winters.projects.iverbs.fragments.MenuIverbsFragment;
import com.binary_winters.projects.iverbs.fragments.MenuMisPalabrasFragment;
import com.binary_winters.projects.iverbs.fragments.MenuSituacionesFragment;

/**
 * Created by chipo on 19/03/18.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private int numeroDeTabs;

    public ViewPagerAdapter(FragmentManager fm, int numeroDeTabs) {
        super(fm);
        this.numeroDeTabs = numeroDeTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new MenuIverbsFragment();
            case 1:
                return new MenuMisPalabrasFragment();
            case 2:
                return new MenuSituacionesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numeroDeTabs;
    }
}
