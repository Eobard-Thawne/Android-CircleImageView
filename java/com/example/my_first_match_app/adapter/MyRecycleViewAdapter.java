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

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyRecycleViewHolder> {
    private final Context context;
    private onRecycleClickListener listener;

    public MyRecycleViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_rv, parent, false);
        return new MyRecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecycleViewHolder holder, int position) {
        int icon = MyData.getIcons()[position];

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(v, holder.getAdapterPosition() + 1);
            }
        });
//        holder.iv.setBackground(ContextCompat.getDrawable(context, icon));
        holder.iv.setImageResource(icon);
        if (position == MyData.getIcons().length - 1) {
            holder.tv.setText("更多");
        } else {
            holder.tv.setText(String.format(this.context.getResources().getString(R.string.myPosition), position + 1));
        }
    }


    @Override
    public int getItemCount() {
        return MyData.getIcons().length;
    }

    static class MyRecycleViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv;
        private final TextView tv;
        public MyRecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.icon_iv);
            tv = itemView.findViewById(R.id.icon_tv);

        }
    }

    public void addOnClickListener(onRecycleClickListener listener) {
        this.listener = listener;
    }

    public interface onRecycleClickListener {

        void onItemClick(View view, int position);
    }
}
