import 'dart:async';

import 'package:flutter/services.dart';

class AppleSignInFirebase {
  static const MethodChannel _channel =
      const MethodChannel('apple_sign_in_firebase');

  static Future<Map> signIn() async {
    try {
      final Map credential = await _channel.invokeMethod('signInApple');
      return credential;
    } on PlatformException catch (e) {
      throw Exception(e);
    }
  }
}
