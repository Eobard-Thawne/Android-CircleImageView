package com.example.my_first_match_app.welcome_pages;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.my_first_match_app.MainActivity;
import com.example.my_first_match_app.R;
import com.example.my_first_match_app.ToastUtil.ToastUtil;
import com.example.my_first_match_app.adapter.MyAdapter;
import com.example.my_first_match_app.dialog.MyDialog;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<View> imgViews;
    private ImageView[] dotViews;
    //    private final int[] images = {R.drawable.yy2, R.drawable.yy3, R.drawable.yy4};
    private final int[] activities = {R.layout.activity_first_page, R.layout.activity_second_page, R.layout.activity_third_page, R.layout.activity_fourth_page, R.layout.activity_fifth_page};

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initImages();
        initDots();
        MyAdapter adapter = new MyAdapter(imgViews);
        ViewPager pager = findViewById(R.id.myVP);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotViews.length; i++) {
                    //如果i==position则为true,反之false
                    dotViews[i].setSelected(i == position);
                }
                if (position == dotViews.length - 1) {
                    Button enterHome = findViewById(R.id.enter_home);
                    Button netWorkSetting = findViewById(R.id.setNetwork);
                    enterHome.setOnClickListener(WelcomeActivity.this);
                    netWorkSetting.setOnClickListener(WelcomeActivity.this);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initImages() {
        //初始化Activity的数据
        imgViews = new ArrayList<>();
        LayoutInflater li = LayoutInflater.from(this);
        for (int i : activities) {
            View view = li.inflate(i, null, false);
            imgViews.add(view);
        }
    }

    private void initDots() {
        LinearLayout layout = findViewById(R.id.myDots);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(20, 0, 20, 0);
        //给创建的用于存放小圆点的数组初始化,长度为Activity的个数
        dotViews = new ImageView[activities.length];
        for (int i = 0; i < activities.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setImageResource(R.drawable.set_select);
            imageView.setSelected(i == 0);
            //得到当前小圆点,用于设置选中和未选中状态
            dotViews[i] = imageView;
            //循环创建的小圆点添加到布局中
            layout.addView(imageView);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.enter_home) {
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            WelcomeActivity.this.finish();
        } else if (v.getId() == R.id.setNetwork) {
            showMyDialog();
        }
    }

    private void showMyDialog() {
        MyDialog dialog = new MyDialog(WelcomeActivity.this);
        dialog.setCancel(Dialog::dismiss).setConfirm(dialog12 -> {
            SharedPreferences preferences = getSharedPreferences("IPAndPort", MODE_PRIVATE);
            EditText ipEt = dialog12.findViewById(R.id.ip_et);
            EditText portEt = dialog12.findViewById(R.id.port_et);
            String ipGetText = ipEt.getText().toString();
            String portGetText = portEt.getText().toString();
            SharedPreferences.Editor editor = preferences.edit();
            //检查IP地址和端口号
            if (!ipGetText.equals("") && isRightAddress(ipGetText) && !portGetText.equals("") && isRightPort(portGetText)) {
                editor.putString("IP", ipGetText);
                editor.putString("PORT", portGetText);
                editor.apply();
                ToastUtil.showMsg(this, "保存成功");
                dialog12.dismiss();
            } else {
                ToastUtil.showMsg(this, "IP地址或端口号为空或不符合规则");
            }

        }).setTitle("网络设置").show();
    }

    private boolean isRightAddress(String address) {
        if (address != null && !address.isEmpty()) {
            return Pattern.matches("^([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}$", address);

        } else {
            return false;
        }
    }

    private boolean isRightPort(String port) {
        if (port != null && !port.isEmpty()) {
            return Integer.parseInt(port) > 0 && Integer.parseInt(port) < 65535;

        } else {
            return false;
        }
    }
}