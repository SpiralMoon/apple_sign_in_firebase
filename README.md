# apple_sign_in_firebase

Access `Sign In with Apple in firebase` from flutter android.
Because you can't get the credential of an app with the [flutter firebase](https://github.com/FirebaseExtended/flutterfire/tree/master/packages/firebase_auth) alone, you can import the credential directly through the [native firebase](https://firebase.google.com/docs/auth/android/apple).

## Getting Started
The project must have a pre-set firebase.

## Platform support
Support only Android!
There's a [iOS](https://pub.dev/packages/apple_sign_in) framework.

## Example
```
Map crendential = await AppleSignInFirebase.signIn();

print(credential['idToken']);
print(credential['accessToken']);
```