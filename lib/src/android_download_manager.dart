import 'dart:async';

import 'package:android_download_manager/src/models/notification_visibility.dart';
import 'package:flutter/services.dart';

class AndroidDownloadManager {
  static const MethodChannel _channel = MethodChannel('download_manager');
  static const EventChannel _eventChannel =
      EventChannel("download_manager/complete");

  ///Requests permission to download file
  static Future<void> requestPermission() async {
    await _channel.invokeMethod("requestPermission");
  }

  static Stream _streamSubscription = const Stream.empty();

  static Future<int> enqueue({
    required String downloadUrl,
    required String downloadPath,
    required String fileName,
    String description = "Downloading in progress",
    Map<String, String>? headers,
    bool allowScanningByMediaScanner = true,
    int notificationVisibility =
        NotificationVisibility.VISIBILITY_VISIBLE_NOTIFY_COMPLETED,
  }) async {
    return await _channel.invokeMethod("enqueue", {
      "downloadUrl": downloadUrl,
      "downloadPath": downloadPath,
      "description": description,
      "headers": headers,
      "allow_scanning_by_media_scanner": allowScanningByMediaScanner,
      "notification_visibility": notificationVisibility,
    });
  }

  ///Returns the id of downloaded task as stream
  static void listen(Function callback) {
    _streamSubscription = _eventChannel.receiveBroadcastStream();
    _streamSubscription.listen((data) {
      callback(data);
    });
  }
}
