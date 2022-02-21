package com.example.my_first_match_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.my_first_match_app.my_fragment.FifthFragment;
import com.example.my_first_match_app.my_fragment.FirstFragment;
import com.example.my_first_match_app.my_fragment.FourthFragment;
import com.example.my_first_match_app.my_fragment.SecondFragment;
import com.example.my_first_match_app.my_fragment.ThirdFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    private Fragment currentFragment = new Fragment();
    private final FirstFragment first = new FirstFragment();
    private final SecondFragment second = new SecondFragment();
    private final ThirdFragment third = new ThirdFragment();
    private final FourthFragment fourth = new FourthFragment();
    private final FifthFragment fifth = new FifthFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bnv = findViewById(R.id.my_bnv);
        replaceFragment(first).commit();
        bnv.setSelectedItemId(R.id.my_home);
        bnv.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.my_home) {
                replaceFragment(first).commit();
            } else if (item.getItemId() == R.id.my_service) {
                replaceFragment(second).commit();
            } else if (item.getItemId() == R.id.my_news) {
                replaceFragment(third).commit();
            } else if (item.getItemId() == R.id.my_activity) {
                replaceFragment(fourth).commit();
            } else if (item.getItemId() == R.id.my_me) {
                replaceFragment(fifth).commit();
            }
            return true;
        });



    }

    private FragmentTransaction replaceFragment(Fragment targetFragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(R.id.my_fragment, targetFragment);
        /* 如果该Fragment没有添加过,则添加, 如果已经添加过,则隐藏当前的,直接显示添加过的。大大增加切换效率 */
        if (!targetFragment.isAdded()) {
            if (currentFragment != null) {
                ft.hide(currentFragment);
            }
            ft.add(R.id.my_fragment, targetFragment);

        } else {
            ft.hide(currentFragment).show(targetFragment);
        }
        currentFragment = targetFragment;
        return ft;
    }
}