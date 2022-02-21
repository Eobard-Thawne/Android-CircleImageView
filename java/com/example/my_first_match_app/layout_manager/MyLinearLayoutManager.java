package com.example.my_first_match_app.layout_manager;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

public class MyLinearLayoutManager extends LinearLayoutManager {

    private boolean canScrollV = true;

    public MyLinearLayoutManager(Context context) {
        super(context);
    }

    /**
     * 是否能上下滑动
     *
     * @param canScrollV true则可以上下滑动, 反之则不能滑动
     */

    public void setCanScrollV(boolean canScrollV) {
        this.canScrollV = canScrollV;
    }

    @Override
    public boolean canScrollVertically() {
        return canScrollV && super.canScrollVertically();
    }
}
