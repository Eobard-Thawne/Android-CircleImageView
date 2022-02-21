package com.example.my_first_match_app.layout_manager;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class MyStaggeredManager extends StaggeredGridLayoutManager {

    private boolean canVScroll = true;

    public MyStaggeredManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    public void setCanVScroll(boolean canVScroll) {
        this.canVScroll = canVScroll;
    }

    @Override
    public boolean canScrollVertically() {
        return canVScroll && super.canScrollVertically();
    }
}
