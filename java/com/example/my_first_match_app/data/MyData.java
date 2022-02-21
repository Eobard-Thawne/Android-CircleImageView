package com.example.my_first_match_app.data;

import android.net.Uri;

import com.example.my_first_match_app.R;


public class MyData {
    private static String backStatus = "";
    private static String str = "";
    private static Uri MyPicUri;
    public static final int STRING_CODE = 2;
    public static final int PHOTO_CODE = 1;



    /* 一些数据 */

    /* 箭头图标 */
    private static final int arrow = R.drawable.arrow;
    /* 个人主页功能入口名字 */
    private static final String[] information = {"个人信息", "订单列表", "修改密码", "意见反馈"};
    /* 个人主页功能入口图标 */
    private static final int[] info_ico = {R.drawable.person_info, R.drawable.my_order, R.drawable.change_passwd, R.drawable.feedback};
    /* Banner图片 */
    private static final int[] images = {R.drawable.tt1, R.drawable.tt2, R.drawable.tt3, R.drawable.tt4, R.drawable.tt5, R.drawable.tt6, R.drawable.tt7, R.drawable.tt8, R.drawable.tt9, R.drawable.tt10};
    /* 主页各个小功能图标 */
    private static final int[] icons = {R.drawable.t1, R.drawable.t2, R.drawable.t3, R.drawable.t4, R.drawable.t5, R.drawable.t6, R.drawable.t7, R.drawable.t8, R.drawable.t9, R.drawable.t10};

    private static final String FileName = "AllSaveData";

    private static final int[] yy = {R.drawable.yy, R.drawable.yy2, R.drawable.yy3, R.drawable.yy4, R.drawable.yy5, R.drawable.yy6, R.drawable.yy7, R.drawable.yy8, R.drawable.yy9, R.drawable.together2};

    public static int[] getYy() {
        return yy;
    }

    /**
     * 用于返回存放图片的数据
     *
     * @return int[]
     */

    public static int[] getImages() {
        return images;
    }

    /**
     * 用于返回存放图标的数据
     *
     * @return int[]
     */
    public static int[] getIcons() {
        return icons;
    }

    /**
     * 返回个人主页中各个功能的图标
     *
     * @return int[]
     */
    public static int[] getInfo_ico() {
        return info_ico;
    }

    /**
     * 返回个人主页中各个功能入口名字
     *
     * @return String[]
     */

    public static String[] getInformation() {
        return information;
    }

    /**
     * 返回箭头图标
     *
     * @return int
     */

    public static int getArrow() {
        return arrow;
    }

    /**
     * SharedPreference存储路径
     *
     * @return String
     */
    public static String getFileName() {
        return FileName;
    }

    public static void setStr(String s) {
        str = s;
    }

    public static void setIsBack(String status) {
        MyData.backStatus = status;
    }

    public static String getBackStatus() {
        return MyData.backStatus;
    }

    public static String getStr() {
        return str;
    }

    public static Uri getMyPicUri() {
        return MyPicUri;
    }

    public static void setMyPicUri(Uri myPicUri) {
        MyPicUri = myPicUri;
    }

}
