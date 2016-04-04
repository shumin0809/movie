package com.shumin.movie.ui.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.shumin.movie.R;
import com.shumin.movie.ui.fragment.FavoriteFragment;
import com.shumin.movie.ui.fragment.SearchFragment;

import java.util.Arrays;
import java.util.List;

/**
 * Created by shumin on 4/3/16.
 */
public class BaseStatePagerAdapter extends FragmentStatePagerAdapter {

    private List<String> titles;

    public BaseStatePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        titles = Arrays.asList(context.getString(R.string.browse),
                context.getString(R.string.favorite));
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new SearchFragment();
        } else {
            return new FavoriteFragment();
        }
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
