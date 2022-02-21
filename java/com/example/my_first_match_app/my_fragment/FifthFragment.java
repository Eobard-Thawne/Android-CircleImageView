package com.example.my_first_match_app.my_fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_first_match_app.R;
import com.example.my_first_match_app.adapter.MyViewAdapter;
import com.example.my_first_match_app.data.MyData;
import com.example.my_first_match_app.layout_manager.MyLinearLayoutManager;
import com.example.my_first_match_app.my_loop.CircleImageView;
import com.example.my_first_match_app.permission.GetPermission;
import com.example.my_first_match_app.personal_center.ChangePasswd;
import com.example.my_first_match_app.personal_center.FeedBack;
import com.example.my_first_match_app.personal_center.OrderList;
import com.example.my_first_match_app.personal_center.PersonInfo;
import com.example.my_first_match_app.util.ImageUtil;

public class FifthFragment extends Fragment {
    private Context context;
    private CircleImageView profilePhoto;
    private TextView nameTv;
    private SharedPreferences sharedPreferences;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /* 填充布局 */
        View view = inflater.inflate(R.layout.fifth_fragment, container, false);
        /* 查找控件 */
        profilePhoto = view.findViewById(R.id.profile_head);
        profilePhoto.setAngle(20);
        nameTv = view.findViewById(R.id.name_tv);
        /* 如果本地没有存储昵称和头像, 则使用默认的昵称和头像 */
        sharedPreferences = context.getSharedPreferences(MyData.getFileName(), Context.MODE_PRIVATE);
        String defaultName = sharedPreferences.getString("nameDescription", "未设置昵称");
        nameTv.setText(defaultName);
        String str_uri = sharedPreferences.getString("Uri", "");
        /* 如果本地没有保存头像, 则使用默认头像 */
        if (str_uri.equals("")) {
            profilePhoto.setImageResource(R.drawable.default_profile);

        } else {
            profilePhoto.setImageURI(Uri.parse(str_uri));
        }

        RecyclerView rv = view.findViewById(R.id.views_rv);

        /* 该适配器包含四个布局, 分别为 个人信息  订单列表  修改密码  意见反馈 */
        MyViewAdapter adapter = new MyViewAdapter(this.context);

        /* 为每一个布局设置点击事件 */

        String getDefaultName = nameTv.getText().toString().trim();
        String[] title_and_defaultName = {MyData.getInformation()[0], getDefaultName};

        profilePhoto.setOnClickListener(v -> {
            GetPermission permission = new GetPermission(getActivity());
            permission.getStoragePermission();
            openAlbum();
        });

        adapter.setItemClickListener((v, p) -> {
            if (p == 1) {
                Intent intent = new Intent(v.getContext(), PersonInfo.class);
                intent.putExtra("personTitleAndDefaultName", title_and_defaultName);
                profilePhoto.setDrawingCacheEnabled(true);
                byte[] bytes = ImageUtil.bitmapToBytes(profilePhoto.getDrawingCache());
                intent.putExtra("image", bytes);
                startActivityForResult(intent, MyData.STRING_CODE);
            } else if (p == 2) {
                Intent intent = new Intent(v.getContext(), OrderList.class);
                Bundle bundle = new Bundle();
                bundle.putString("orderTitle", MyData.getInformation()[1]);
                intent.putExtras(bundle);
                startActivityForResult(intent, MyData.STRING_CODE);
            } else if (p == 3) {
                Intent intent = new Intent(v.getContext(), ChangePasswd.class);
                Bundle bundle = new Bundle();
                bundle.putString("changePasswdTitle", MyData.getInformation()[2]);
                intent.putExtras(bundle);
                startActivityForResult(intent, MyData.STRING_CODE);
            } else if (p == 4) {
                Intent intent = new Intent(v.getContext(), FeedBack.class);
                Bundle bundle = new Bundle();
                bundle.putString("feedBackTitle", MyData.getInformation()[3]);
                intent.putExtras(bundle);
                startActivityForResult(intent, MyData.STRING_CODE);
            }
        });

        rv.setAdapter(adapter);
        MyLinearLayoutManager llm = new MyLinearLayoutManager(this.context);
        llm.setCanScrollV(false);
        rv.setLayoutManager(llm);
        rv.addItemDecoration(new DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL));


        return view;
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context.getApplicationContext();

    }

    @Override
    public void onResume() {
        super.onResume();
        /* 当返回到个人中心界面后, 把头像换为选择的图片 */
        if (MyData.getMyPicUri() != null) {
            profilePhoto.setImageURI(MyData.getMyPicUri());
            MyData.setMyPicUri(null);
        }

        String string = sharedPreferences.getString("nameDescription", "未设置昵称");
        nameTv.setText(string);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("requestCode----->" + requestCode);
        System.out.println("resultCode------>" + resultCode);
        if (requestCode == MyData.PHOTO_CODE) {
            if (data != null) {
                Uri uri = data.getData();
                profilePhoto.setImageURI(uri);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Uri", String.valueOf(uri)).apply();
            }
        }
    }
}
