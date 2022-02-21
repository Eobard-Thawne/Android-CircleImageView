package com.example.my_first_match_app.permission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * 请求权限类
 */
public class GetPermission {

    private final Activity activity;
    private final String write = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private final String read = Manifest.permission.READ_EXTERNAL_STORAGE;
    private final String[] WriteReadPermission = new String[]{write, read};

    public GetPermission(Activity activity) {
        this.activity = activity;
    }

    public void getStoragePermission() {

        int checkWrite = ContextCompat.checkSelfPermission(activity, write);
        int checkRead = ContextCompat.checkSelfPermission(activity, read);
        int checkOK = PackageManager.PERMISSION_GRANTED;
        if (checkWrite != checkOK && checkRead != checkOK) {
            /* 如果没有上面的代码没有申请权限成功,则申请权限 */
            ActivityCompat.requestPermissions(activity, WriteReadPermission, 1);
        }
    }
}
