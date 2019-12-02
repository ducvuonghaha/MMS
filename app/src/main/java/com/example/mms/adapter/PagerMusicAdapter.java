package com.example.mms.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.mms.musicfragment.BoleroFragment;
import com.example.mms.musicfragment.ChildrenMusicFragment;
import com.example.mms.musicfragment.EDMFragment;
import com.example.mms.musicfragment.IndieFragment;
import com.example.mms.musicfragment.PopFragment;
import com.example.mms.musicfragment.RockFragment;

public class PagerMusicAdapter extends FragmentStatePagerAdapter {
    public PagerMusicAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag = new PopFragment();
                break;
            case 1:
                frag = new BoleroFragment();
                break;
            case 2:
                frag = new RockFragment();
                break;
            case 3:
                frag = new ChildrenMusicFragment();
                break;
            case 4:
                frag = new EDMFragment();
                break;
            case 5:
                frag = new IndieFragment();
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
                title = "Nhạc Trẻ";
                break;
            case 1:
                title = "Trữ Tình";
                break;
            case 2:
                title = "Rock";
                break;
            case 3:
                title = "Thiếu Nhi";
                break;
            case 4:
                title = "EDM";
                break;
            case 5:
                title = "Indie";
                break;

        }
        return title;
    }

}
