package com.example.my_first_match_app.util;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

public class ImageUtil {

    public static byte[] bitmapToBytes(Bitmap bitmap) {

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, b);
        return b.toByteArray();


    }

}
