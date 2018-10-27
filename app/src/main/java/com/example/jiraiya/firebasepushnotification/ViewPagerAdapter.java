package com.example.jiraiya.firebasepushnotification;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

            switch (i){
                case 0:
                    return new Send_Noti();
                case 1:
                    return new ReceivedNoti();
                default:
                    return null;
            }


    }

    @Override
    public int getCount() {
        return 2;
    }
}
