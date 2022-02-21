package com.example.my_first_match_app.my_fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.my_first_match_app.R;
import com.example.my_first_match_app.ToastUtil.ToastUtil;
import com.example.my_first_match_app.adapter.MyHotAdapter;
import com.example.my_first_match_app.adapter.MyLooperAdapter;
import com.example.my_first_match_app.adapter.MyRecycleViewAdapter;
import com.example.my_first_match_app.data.MyData;
import com.example.my_first_match_app.itemDecoration.SpaceItemDecoration;
import com.example.my_first_match_app.layout_manager.MyGridLayout;
import com.example.my_first_match_app.layout_manager.MyStaggeredManager;
import com.example.my_first_match_app.my_loop.ScrollController;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class FirstFragment extends Fragment {
    private FragmentActivity activity;
    private LinearLayout ll;
    private ViewPager vp;
    private Timer timer;
    private TimerTask timerTask;

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        SearchView sv = view.findViewById(R.id.my_sv);
        sv.onActionViewExpanded();
        sv.clearFocus();


        /* 初始化Banner指示器 */
        initDots(view);
        /* 如果光标还在搜索框, 也就是焦点还在搜索框, 又如果滑动屏幕, 则清除搜索框的焦点 */
        ScrollView srv = view.findViewById(R.id.my_scrollView);
        srv.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                sv.clearFocus();
            }
            return false;
        });


        /* 显示图标的RecycleView和适配器 */
        RecyclerView rv = view.findViewById(R.id.my_rv);
        MyRecycleViewAdapter rv_adapter = new MyRecycleViewAdapter(getContext());

        rv_adapter.addOnClickListener((view1, position) -> ToastUtil.showMsg(view1.getContext(), "您点击了第" + position + "个"));
        MyGridLayout gl = new MyGridLayout(getContext(), 5);
        gl.setScrollEnable(false);
        rv.setAdapter(rv_adapter);
        rv.setLayoutManager(gl);


        RecyclerView hotRv = view.findViewById(R.id.hot_rv);
        MyHotAdapter myHotAdapter = new MyHotAdapter(view.getContext());
        MyStaggeredManager stg = new MyStaggeredManager(2, StaggeredGridLayoutManager.VERTICAL);
        stg.setCanVScroll(false);
        hotRv.setLayoutManager(stg);
        hotRv.addItemDecoration(new SpaceItemDecoration(15));
        hotRv.setAdapter(myHotAdapter);

        myHotAdapter.setOnItemClickListener((v, p) -> ToastUtil.showMsg(view.getContext(), "您点击了" + p + "个位置"));

        MyLooperAdapter adapter = new MyLooperAdapter(MyData.getYy());
        adapter.setPadding(15, 15, 15, 15);
        adapter.addViewPagerClickListener((view12, position) -> ToastUtil.showMsg(view12.getContext(), "您点击了第" + position + "个位置"));

        vp = view.findViewById(R.id.myLoopVP);
        vp.setAdapter(adapter);
        vp.setCurrentItem((Objects.requireNonNull(vp.getAdapter()).getCount()) / 2);
        ScrollController scl = new ScrollController(getContext());

        scl.setScDuration(2000);
        scl.applyChange(vp);


        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateState(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        vp.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                killTask();
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                killTask();
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                executeTask();
            }
            return false;
        });

        executeTask();
        return view;
    }

    /**
     * 此方法在添加了Fragment之后调用, 目的是为了让成员变量保存Activity活动状态,可以让子线程持续运行,而不是切换Activity之后就会导致空指针异常
     *
     * @param context 上下文对象
     */

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = getActivity();

    }

    /**
     * 初始化Banner指示器
     *
     * @param view 该参数为存放Banner的视图
     */

    private void initDots(View view) {

        ll = view.findViewById(R.id.myDots);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 0, 10, 0);
        for (int i = 0; i < MyData.getYy().length; i++) {
            ImageView iv = new ImageView(view.getContext());
            iv.setLayoutParams(params);
            iv.setImageResource(R.drawable.set_select);
            iv.setSelected(i == 0);
            ll.addView(iv);
        }
    }

    /**
     * 创建一个定时任务
     */

    private void executeTask() {
        //开始前先清理任务,保证只有一个Timer+TimerTask
        killTask();
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(() -> showNextPage());
            }
        };

        timer.schedule(timerTask, 3000, 3000);

    }

    /**
     * 在Fragment/Activity不见或者推出时清理任务,避免资源浪费
     */

    @Override
    public void onPause() {
        super.onPause();
        killTask();
    }

    /**
     * 在Fragment/Activity 启动时执行任务
     */
    @Override
    public void onStart() {
        super.onStart();
        executeTask();
    }

    /**
     * 清理任务
     */

    private void killTask() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    /**
     * 用于显示下一页
     */
    private void showNextPage() {
        vp.setCurrentItem(vp.getCurrentItem() + 1);
    }

    /**
     * 用于更新指示器的状态(选中/未选中)
     *
     * @param position 是ViewPager当前的位置,传进去之后会进行处理
     */
    private void updateState(int position) {
        position = position % ll.getChildCount();
        for (int i = 0; i < ll.getChildCount(); i++) {
            ll.getChildAt(i).setSelected(i == position);
        }
    }
}
