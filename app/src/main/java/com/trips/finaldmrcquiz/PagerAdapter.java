package com.trips.finaldmrcquiz;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public PagerAdapter(FragmentManager fm, int NumberofTabs) {
        super(fm);
        this.mNoOfTabs = NumberofTabs;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Login login = new Login();
                return login;
            case 1:
                SignUp signup = new SignUp();
                return signup;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
