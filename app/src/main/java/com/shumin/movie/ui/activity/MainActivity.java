package com.shumin.movie.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.shumin.movie.R;
import com.shumin.movie.model.Result;
import com.shumin.movie.rest.RestClient;
import com.shumin.movie.ui.adapter.BaseStatePagerAdapter;
import com.shumin.movie.ui.adapter.ResultAdapter;

import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUi();
    }

    private void setupUi() {
        setSupportActionBar((Toolbar) findViewById(R.id.view_pager_toolbar));

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new BaseStatePagerAdapter(getFragmentManager(), this));

        tabLayout = (TabLayout) findViewById(R.id.view_pager_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
