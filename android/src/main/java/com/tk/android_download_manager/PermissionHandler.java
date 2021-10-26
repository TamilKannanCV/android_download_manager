package com.tk.android_download_manager;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

abstract public class PermissionHandler {
    @RequiresApi(api = Build.VERSION_CODES.M)
    static boolean isPermissionGranted(Context context) {
        return context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    static void requestPermission(Activity activity) {
        int PERMISSION_CODE = 5000;
        activity.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_CODE);
    }
}
