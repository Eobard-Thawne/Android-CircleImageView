package com.example.my_first_match_app.personal_center;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.my_first_match_app.R;

public class OrderList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        RelativeLayout rl = findViewById(R.id.include_title);
        TextView title = rl.findViewById(R.id.this_title);
        Bundle bundle = getIntent().getExtras();
        String orderTitle = bundle.getString("orderTitle");
        title.setText(orderTitle);

        ImageView iv = rl.findViewById(R.id.back_arrow);
        iv.setOnClickListener(v -> finish());

    }
}