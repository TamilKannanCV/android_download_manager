# android_download_manager
This package lets you download files via Android Native Download Manager.

![Pub version](https://img.shields.io/pub/v/android_download_manager)
![License](https://img.shields.io/github/license/TamilKannanCV-personal/android_download_manager)
---

## Getting Started

This package lets you download files via Android Native Download Manager. 
This package is made available only for android.

## Permissions

 - WRITE_EXTERNAL_STORAGE
 - READ_EXTERNAL_STORAGE

# How to download?
```dart
AndroidDownloadManager.enqueue(
    downloadUrl: "https://raw.githubusercontent.com/ableco/test-files/master/images/test-image-png_4032x3024.png",
    downloadPath: Directory.systemTemp.path,
    fileName: "test.png",
);
```
|| Type | Description |
| --- | --- | --- |
| downloadUrl | String | The url of the file to be downloaded |
| downloadPath | String | The location where the downloaded file to be saved |
| fileName | String | Name of the downloaded file with extension |
| description (optional) | String | Description for the download manager |
| headers (optional) | Map<String, String> | Headers for the download url |
| allowScanningByMediaScanner (optional) | bool | Whether allow download manager to scan by MediaScanner |
| notificationVisibility (optional) | NotificationVisibility | VISIBILITY_VISIBLE<br> VISIBILITY_VISIBLE_NOTIFY_COMPLETED<br>NETWORK_MOBILE<br>NETWORK_WIFI<br>VISIBILITY_HIDDEN<br>VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION

## Listen to completed downloads

```dart
AndroidDownloadManager.listen((data) {
    String id = data["id"];
    log("Download complete");
  });
```
Here we receive IDs of the completed downloads.
