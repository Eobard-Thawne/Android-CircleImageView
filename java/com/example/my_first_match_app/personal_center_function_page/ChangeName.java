package com.example.my_first_match_app.personal_center_function_page;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.my_first_match_app.R;
import com.example.my_first_match_app.data.MyData;
import com.example.my_first_match_app.personal_center.PersonInfo;

public class ChangeName extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private String changedName;
    private EditText changeNameET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);
        RelativeLayout rl = findViewById(R.id.include_title1);
        TextView changeNameTitle = rl.findViewById(R.id.this_title);
        changeNameET = findViewById(R.id.change_name_et);
        TextView saveTV = findViewById(R.id.save_tv);

        String[] title_and_name = getIntent().getStringArrayExtra("title_and_name");
        changeNameET.setText(title_and_name[1]);
        changeNameTitle.setText(title_and_name[0]);
        saveTV.setText(getResources().getString(R.string.save));
        ImageView backIV = rl.findViewById(R.id.back_arrow);
        backIV.setOnClickListener(v -> finish());
        saveTV.setOnClickListener(v -> {
            changedName = changeNameET.getText().toString();

            sharedPreferences = getSharedPreferences(MyData.getFileName(), MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("nameDescription", changedName).apply();

            Intent intent = new Intent(v.getContext(), PersonInfo.class);
            intent.putExtra("changedName", changedName);
            setResult(MyData.STRING_CODE, intent);

            finish();
        });
    }

    @Override
    public void finish() {
        SharedPreferences sharedPreferences1 = getSharedPreferences(MyData.getFileName(), MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences1.edit();
        edit.putString("changedName", changedName).apply();
        super.finish();
    }
}