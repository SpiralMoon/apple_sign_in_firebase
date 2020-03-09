#import "AppleSignInFirebasePlugin.h"
#if __has_include(<apple_sign_in_firebase/apple_sign_in_firebase-Swift.h>)
#import <apple_sign_in_firebase/apple_sign_in_firebase-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "apple_sign_in_firebase-Swift.h"
#endif

@implementation AppleSignInFirebasePlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftAppleSignInFirebasePlugin registerWithRegistrar:registrar];
}
@end
