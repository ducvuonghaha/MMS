package com.example.mms.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.mms.moviefragment.ActionFragment;
import com.example.mms.moviefragment.ComedyFragment;
import com.example.mms.moviefragment.DocumentFragment;
import com.example.mms.moviefragment.HorrorFragment;
import com.example.mms.moviefragment.RomanticFragment;
import com.example.mms.moviefragment.ScienceFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag = new HorrorFragment();
                break;
            case 1:
                frag = new ComedyFragment();
                break;
            case 2:
                frag = new ActionFragment();
                break;
            case 3:
                frag = new ScienceFragment();
                break;
            case 4:
                frag = new RomanticFragment();
                break;
            case 5:
                frag = new DocumentFragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Kinh Dị";
                break;
            case 1:
                title = "Hài Hước";
                break;
            case 2:
                title = "Hành Động";
                break;
            case 3:
                title = "Khoa Học";
                break;
            case 4:
                title = "Lãng Mạn";
                break;
            case 5:
                title = "Tài Liệu";
                break;

        }
        return title;
    }
}
