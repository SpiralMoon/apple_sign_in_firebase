import 'package:flutter/material.dart';
import 'package:firebase_core/firebase_core.dart';
import 'dart:async';

import 'package:apple_sign_in_firebase/apple_sign_in_firebase.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Native firebase auth call by Flutter'),
        ),
        body: Center(
          child: RaisedButton(
            child: Text('Sign In with Apple'),
            onPressed: () async {
              await AppleSignInFirebase.signIn().then((credential) {
                print(credential['idToken']);
                print(credential['accessToken']);
              }).catchError((error) {
                print(error);
              });
            },
          ),
        ),
      ),
    );
  }
}
