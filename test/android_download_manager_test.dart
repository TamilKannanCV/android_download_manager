import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:android_download_manager/android_download_manager.dart';

void main() {
  const MethodChannel channel = MethodChannel('android_download_manager');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await AndroidDownloadManager.platformVersion, '42');
  });
}
