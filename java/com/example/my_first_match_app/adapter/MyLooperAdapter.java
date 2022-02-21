package com.example.my_first_match_app.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.my_first_match_app.R;
import com.example.my_first_match_app.my_loop.CircleImageView;


public class MyLooperAdapter extends PagerAdapter {
    /* 数据源 */
    private final int[] img;
    /* 适配器的左、上、右、下边距 */
    private int paddingLeft, paddingTop, paddingRight, paddingBottom;
    /* 内部单击接口 */
    private onViewPagerClickListener listener;

    public MyLooperAdapter(int[] img) {
        this.img = img;
    }

    @Override
    public int getCount() {
        return img.length == 0 ? 0 : img.length * 1000000;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int myPosition = position % img.length;
        View view = View.inflate(container.getContext(), R.layout.item_circle, null);
        CircleImageView civ = view.findViewById(R.id.circle_iv);
        civ.setScaleType(ImageView.ScaleType.CENTER_CROP);
        civ.setAngle(30);
        RelativeLayout rl = view.findViewById(R.id.rly);
        rl.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        civ.setImageResource(img[myPosition]);
        civ.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(v, myPosition + 1);
            }
        });
        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    /* 以下的方法为自己写的方法, 用于更改适配器的某些设置 */

    /**
     * @param paddingLeft 设置左内边距
     * @return MyLooperAdapter
     */
    public MyLooperAdapter setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
        return this;
    }

    /**
     * @param paddingTop 设置上内边距
     * @return MyLooperAdapter
     */
    public MyLooperAdapter setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
        return this;
    }

    /**
     * @param paddingRight 设置右内边距
     * @return MyLooperAdapter
     */

    public MyLooperAdapter setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
        return this;
    }

    /**
     * @param paddingBottom 设置下内边距
     * @return MyLooperAdapter
     */

    public MyLooperAdapter setPaddingBottom(int paddingBottom) {
        this.paddingBottom = paddingBottom;
        return this;
    }

    /**
     * @param left   左内边距
     * @param top    上内边距
     * @param right  右内边距
     * @param bottom 下内边距
     */

    public void setPadding(int left, int top, int right, int bottom) {
        this.paddingLeft = left;
        this.paddingTop = top;
        this.paddingRight = right;
        this.paddingBottom = bottom;
    }

    public void addViewPagerClickListener(onViewPagerClickListener listener) {
        this.listener = listener;
    }

    public interface onViewPagerClickListener {
        void onItemClick(View view, int position);
    }
}
