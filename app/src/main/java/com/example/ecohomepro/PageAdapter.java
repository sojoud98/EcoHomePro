package com.example.ecohomepro;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {
    int counttab;
    public PageAdapter(FragmentManager fm,int counttab) {
        super(fm);
        this.counttab=counttab;
    }

    @Override
    public Fragment getItem(int position) {
switch (position){


    case 1:return  new TabOne();
    case 2 :return new TabTow();
    case 3:return  new TabThree();
    default: return null;
}
    }

    @Override
    public int getCount() {
        return counttab;
    }
}
