package com.example.my_first_match_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_first_match_app.R;
import com.example.my_first_match_app.data.MyData;

public class MyViewAdapter extends RecyclerView.Adapter<MyViewAdapter.MyViewsHolder> {
    private final Context context;
    private ItemClickListener listener;

    public MyViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_views, parent, false);
        return new MyViewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewsHolder holder, int position) {
        int icon = MyData.getInfo_ico()[position];
        String text = MyData.getInformation()[position];
        holder.iv.setImageResource(icon);
        holder.tv.setText(text);
        holder.arrow_iv.setImageResource(MyData.getArrow());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(v, position + 1);
            }
        });
    }


    @Override
    public int getItemCount() {
        return MyData.getInfo_ico().length;
    }

    static class MyViewsHolder extends RecyclerView.ViewHolder {
        private final ImageView iv;
        private final TextView tv;
        private final ImageView arrow_iv;

        public MyViewsHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.views_iv);
            tv = itemView.findViewById(R.id.views_tv);
            arrow_iv = itemView.findViewById(R.id.arrow_iv);
        }
    }

    public void setItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public interface ItemClickListener {
        void onItemClick(View v, int p);
    }
}
