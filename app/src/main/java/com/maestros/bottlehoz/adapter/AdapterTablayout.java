package com.maestros.bottlehoz.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class AdapterTablayout extends FragmentStatePagerAdapter {
    int mNumOfTabs;



    public AdapterTablayout(FragmentManager fm, int NumOfTabs) {
        super( fm );
        this.mNumOfTabs = NumOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        //return DynamicallyFragment.addfrag(position);
        Fragment fragment = null;
        switch (position) {


            /*for (int i = 0; i < mNumOfTabs ; i++) {
                if (i == position) {
                    fragment = YourFragment.newInstance();
                    break;
                }
            }*/
            case 0:
                /*SignUpFragment signUpFragment = new SignUpFragment();
                return signUpFragment;*/
            case 1:
                /*SignInFragment signInFragment = new SignInFragment();
                return signInFragment;*/


            default:
                return null;
        }
    }
    @Override
    public int getCount() {
            return mNumOfTabs;

    }
}
