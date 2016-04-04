package com.shumin.movie.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.shumin.movie.R;
import com.shumin.movie.database.MovieDbHelper;
import com.shumin.movie.event.Events;
import com.shumin.movie.ui.adapter.BaseStatePagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    public MovieDbHelper movieDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUi();
    }

    @Override
    protected void onResume() {
        super.onResume();
        movieDbHelper.open();
    }

    @Override
    protected void onDestroy() {
        movieDbHelper.close();
        super.onDestroy();
    }

    private void setupUi() {
        setSupportActionBar((Toolbar) findViewById(R.id.view_pager_toolbar));

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new BaseStatePagerAdapter(getFragmentManager(), this));

        tabLayout = (TabLayout) findViewById(R.id.view_pager_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                // according to the open bug from Google
                // https://code.google.com/p/android/issues/detail?id=180462
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        movieDbHelper = new MovieDbHelper(this);
    }

    public void showToast(int stringId) {
        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show();
    }

}
