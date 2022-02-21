package com.example.my_first_match_app.my_loop;

import android.content.Context;
import android.widget.Scroller;

import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;

public class ScrollController extends Scroller {
    private int scDuration;

    public ScrollController(Context context) {
        super(context);
    }

    /**
     * 设置滑动动画的时间 ---> 单位为毫秒
     *
     * @param scDuration 滑动动画的时间
     */
    public void setScDuration(int scDuration) {
        this.scDuration = scDuration;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, scDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, scDuration);
    }

    /**
     * 把设置的滑动时间应用到ViewPager上面
     *
     * @param vp 需要应用滑动时间的ViewPager
     */
    public void applyChange(ViewPager vp) {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            field.set(vp, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
