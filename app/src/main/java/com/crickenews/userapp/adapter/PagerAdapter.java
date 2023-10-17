package com.crickenews.userapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.crickenews.userapp.view.MatchFragment;
import com.crickenews.userapp.view.StatesFragment;
import com.crickenews.userapp.view.TableFragment;


public class PagerAdapter extends FragmentPagerAdapter{

    int tabcount;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount = behavior;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:
                return new MatchFragment();
            case 1:
                return new TableFragment();
            case 2:
                return new StatesFragment();
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return tabcount;
    }

}
