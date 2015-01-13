package com.livingsocial.challenge.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ale on 1/12/15.
 */

public class FragmentPageAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> list = new ArrayList<Fragment>();

    public FragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void setData(List<Fragment> list) {
        if (list != null) {
            this.list = list;
        }
    }
}
