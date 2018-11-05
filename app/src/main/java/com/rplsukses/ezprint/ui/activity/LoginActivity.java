package com.rplsukses.ezprint.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.ui.adapter.TabAdapter;
import com.rplsukses.ezprint.ui.fragment.SigninFragment;
import com.rplsukses.ezprint.ui.fragment.SignupFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    private TabAdapter adapter;

    @BindView(R.id.activity_login_viewPager) ViewPager viewPager;
    @BindView(R.id.activity_login_tabLayout) TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new SigninFragment(), getString(R.string.tab_sign_in));
        adapter.addFragment(new SignupFragment(), getString(R.string.tab_sign_up));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
