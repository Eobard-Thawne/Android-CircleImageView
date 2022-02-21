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
import com.example.my_first_match_app.my_loop.CircleImageView;

public class MyHotAdapter extends RecyclerView.Adapter<MyHotAdapter.MyHotHolder> {
    private final Context context;
    private addItemClickListener listener = null;

    public MyHotAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyHotHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.hot_item, parent, false);
        return new MyHotHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHotHolder holder, int position) {
        int hotImage = MyData.getImages()[position];
        holder.hotCiv.setImageResource(hotImage);
        holder.hotCiv.setAdjustViewBounds(true);
        holder.hotCiv.setLeftTopRightTop(20, 20);
        holder.hotCiv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.hotTv.setText(String.format(context.getResources().getString(R.string.myPosition), position + 1));

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(v, position + 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return MyData.getImages().length;
    }

    static class MyHotHolder extends RecyclerView.ViewHolder {
        private final CircleImageView hotCiv;
        private final TextView hotTv;

        public MyHotHolder(@NonNull View itemView) {
            super(itemView);
            hotCiv = itemView.findViewById(R.id.hot_civ);
            hotTv = itemView.findViewById(R.id.hot_title);
        }
    }

    public void setOnItemClickListener(addItemClickListener listener) {
        this.listener = listener;
    }

    public interface addItemClickListener {
        void onClick(View v, int p);
    }
}
