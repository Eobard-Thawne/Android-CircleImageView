package com.example.my_first_match_app.personal_center;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.my_first_match_app.R;
import com.example.my_first_match_app.data.MyData;
import com.example.my_first_match_app.my_loop.CircleImageView;
import com.example.my_first_match_app.permission.GetPermission;
import com.example.my_first_match_app.personal_center_function_page.ChangeName;

public class PersonInfo extends AppCompatActivity {
    /* 头像  昵称  性别  联系电话  证件号码的布局 */
    private RelativeLayout rProfilePhoto, rNickName, rSex, rPhoneNumber, rID;
    /* 描述昵称的TextView */
    private TextView descNickName;
    /* 轻量级存储数据 */
    private SharedPreferences sharedPreferences;
    /* 圆角头像 */
    private CircleImageView headDescription;
    /* 存放图片的Uri */
    private Uri uri;
    /* 用于获取昵称 */
    private String nickNameDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);

        /* 查找个人信息界面下的控件 */
        /* 自定义的标题栏 */
        RelativeLayout includeTitle = findViewById(R.id.include_title);
        /* 标题栏中的标题 */
        TextView tv = includeTitle.findViewById(R.id.this_title);
        /* 头像 */
        rProfilePhoto = findViewById(R.id.profilePhotoRl);
        /* 昵称 */
        rNickName = findViewById(R.id.nickNameRl);
        /* 性别 */
        rSex = findViewById(R.id.sexRl);
        /* 联系电话 */
        rPhoneNumber = findViewById(R.id.phoneNumberRl);
        /* 证件号码 */
        rID = findViewById(R.id.idRl);
        /* 昵称描述 */
        descNickName = rNickName.findViewById(R.id.description_nickName);
        /* 头像描述 */
        headDescription = findViewById(R.id.head_description);
        /* 设置头像为圆角 */
        headDescription.setAngle(20);

        sharedPreferences = getSharedPreferences(MyData.getFileName(), MODE_PRIVATE);
        String str_uri = sharedPreferences.getString("Uri", "");
        if ("".equals(str_uri)) {
            headDescription.setImageResource(R.drawable.default_profile);
        } else {
            headDescription.setImageURI(Uri.parse(str_uri));
        }
        String[] personTitleAndDefaultNames = getIntent().getStringArrayExtra("personTitleAndDefaultName");

        tv.setText(personTitleAndDefaultNames[0]);

        descNickName.setText(personTitleAndDefaultNames[1]);


        ImageView backIv = includeTitle.findViewById(R.id.back_arrow);
        backIv.setOnClickListener(v -> finish());

        RelativeLayout[] relativeLayouts = {rProfilePhoto, rNickName, rSex, rPhoneNumber, rID};
        setClick(relativeLayouts);

    }

    /* 设置布局的点击事件 */
    private void setClick(RelativeLayout[] r) {
        for (RelativeLayout rs : r) {
            rs.setOnClickListener(new MyRelativeLayoutClick());
        }
    }

    class MyRelativeLayoutClick implements View.OnClickListener {
        /* 如果点击的为头像, 则打开相册选取照片  如果点击的为昵称, 则跳转到更改昵称的界面 */
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.profilePhotoRl) {
                GetPermission permission = new GetPermission(PersonInfo.this);
                permission.getStoragePermission();
                openAlbum();
            } else if (view.getId() == R.id.nickNameRl) {
                nickNameDesc = descNickName.getText().toString();
                Bundle title = new Bundle();
                String[] strArr = {"更改昵称", nickNameDesc};
                Intent title_intent = new Intent(PersonInfo.this, ChangeName.class);
                title.putStringArray("title_and_name", strArr);
                title_intent.putExtras(title);
                startActivityForResult(title_intent, MyData.STRING_CODE);
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        String str_uri = sharedPreferences.getString("Uri", "");
        if ("".equals(str_uri)) {
            headDescription.setImageResource(R.drawable.default_profile);
        } else {
            headDescription.setImageURI(Uri.parse(str_uri));
        }
        String name = sharedPreferences.getString("nameDescription","未设置昵称");


    }


    /**
     * 打开相册
     */

    private void openAlbum() {
        /* 另一种打开相册方法
         *  Intent album = new Intent(Intent.ACTION_GET_CONTENT);
         *
         * */
        Intent album = new Intent(Intent.ACTION_PICK);
        album.setType("image/*");
        startActivityForResult(album, MyData.PHOTO_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /* 选择完图片后的图片数据为一个Uri */
        if (requestCode == MyData.PHOTO_CODE) {
            if (data != null) {
                uri = data.getData();
                headDescription.setImageURI(uri);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Uri", String.valueOf(uri)).apply();
            }
            /* 如果resultCode == MyData.STRING_CODE 则说明, 该返回值是从昵称界面返回的, 就把昵称设置到昵称描述TextView上 */
        }
        if (resultCode == MyData.STRING_CODE) {
            if (data != null) {
                String changedName = data.getExtras().getString("changedName");
                descNickName.setText(changedName);
            }
        }
    }

    /**
     * 返回的时候, 把数据传给个人中心界面
     */
    @Override
    public void finish() {

        nickNameDesc = descNickName.getText().toString();

        MyData.setMyPicUri(uri);
        MyData.setStr(nickNameDesc);
        super.finish();
    }
}