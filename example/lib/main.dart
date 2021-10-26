import 'dart:developer';

import 'package:android_download_manager/android_download_manager.dart';
import 'package:flutter/material.dart';

void main() {
  AndroidDownloadManager.listen((data) {
    String id = data["id"];
    log("Download complete");
  });
  runApp(MaterialApp(
    home: Scaffold(
        floatingActionButton: FloatingActionButton(
          child: const Icon(Icons.download),
          onPressed: () async {
            AndroidDownloadManager.enqueue(
              downloadUrl: "TODO:Download link goes here",
              downloadPath: "/storage/emulated/0/Download/",
              fileName: "test.mp4",
            );
          },
        ),
        body: const Center(child: Text("This is an example"))),
  ));
}
