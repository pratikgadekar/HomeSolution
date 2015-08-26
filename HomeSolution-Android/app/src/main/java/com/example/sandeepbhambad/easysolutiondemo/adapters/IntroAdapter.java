package com.example.sandeepbhambad.easysolutiondemo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sandeepbhambad.easysolutiondemo.fragments.FragmentIntroSlideFour;
import com.example.sandeepbhambad.easysolutiondemo.fragments.FragmentIntroSlideOne;
import com.example.sandeepbhambad.easysolutiondemo.fragments.FragmentIntroSlideThree;
import com.example.sandeepbhambad.easysolutiondemo.fragments.FragmentIntroSlideTwo;

/**
 * Created by pratikgadekar on 8/20/15.
 */
public class IntroAdapter extends FragmentPagerAdapter {
    public IntroAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new FragmentIntroSlideOne();
            case 1:
                return new FragmentIntroSlideTwo();
            case 2:
                return new FragmentIntroSlideThree();
            case 3:
                return new FragmentIntroSlideFour();

            default:
                break;

        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
