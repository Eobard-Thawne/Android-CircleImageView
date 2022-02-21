package com.example.my_first_match_app.layout_manager;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;

public class MyGridLayout extends GridLayoutManager {
    private boolean isScrollEnable = true;

    public MyGridLayout(Context context, int spanCount) {
        super(context, spanCount);
    }

    public MyGridLayout(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    public void setScrollEnable(boolean scrollEnable) {
        isScrollEnable = scrollEnable;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnable && super.canScrollVertically();
    }
}
