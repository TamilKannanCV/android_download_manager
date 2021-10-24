import 'dart:developer';
import 'dart:io';

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
          onPressed: () {
            AndroidDownloadManager.enqueue(
              downloadUrl:
                  "https://raw.githubusercontent.com/ableco/test-files/master/images/test-image-png_4032x3024.png",
              downloadPath: Directory.systemTemp.path,
              fileName: "test.png",
            );
          },
        ),
        body: const Center(child: Text("This is an example"))),
  ));
}
