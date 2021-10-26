package com.tk.android_download_manager;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;

import java.io.File;
import java.util.Map;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class DownloadMethodChannelHandler implements MethodChannel.MethodCallHandler {
    private final Context context;
    private final DownloadManager manager;
    private final Activity activity;

    public DownloadMethodChannelHandler(Context context, DownloadManager manager, Activity activity) {
        this.context = context;
        this.manager = manager;
        this.activity = activity;
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {

        switch (call.method) {
            case "requestPermission":
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        this.activity.requestPermissions(permissions, 1000);
                    }
                }
                break;
            case "enqueue":
                Long downloadId = null;
                String downloadUrl = call.argument("downloadUrl");
                String fileName = call.argument("fileName");
                String downloadPath = call.argument("downloadPath");
                String description = call.argument("description");
                Map<String, String> headers = call.argument("headers");
                Boolean allowScanningByMediaScanner = call.argument("allow_scanning_by_media_scanner");
                Integer notificationVisibility = call.argument("notification_visibility");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        downloadId = enqueue(downloadUrl, fileName, downloadPath, headers, allowScanningByMediaScanner, description, notificationVisibility);
                    } else {
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        this.activity.requestPermissions(permissions, 1000);
                    }
                } else {
                    downloadId = enqueue(downloadUrl, fileName, downloadPath, headers, allowScanningByMediaScanner, description, notificationVisibility);
                }
                result.success(downloadId);
                break;
            default:
                result.notImplemented();
                break;
        }
    }

    private Long enqueue(String downloadUrl, String fileName, String downloadPath, Map<String, String> headers, Boolean allowScanningByMediaScanner, String description, Integer notificationVisibility) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
        if (headers != null) {
            for (String key : headers.keySet()) {
                request.addRequestHeader(key, headers.get(key));
            }
        }
        if (allowScanningByMediaScanner == null || allowScanningByMediaScanner) {
            request.allowScanningByMediaScanner();
        }
        request.setDestinationUri(Uri.fromFile(new File(downloadPath, fileName)));
        request.setTitle(fileName);
        request.setAllowedOverRoaming(true);
        request.setDescription(description);
        request.setNotificationVisibility(notificationVisibility);
        return manager.enqueue(request);

    }


}
