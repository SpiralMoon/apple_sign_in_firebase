package dev.spiralmoon.apple_sign_in_firebase;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.OAuthProvider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class AppleSignIn implements MethodChannel.MethodCallHandler {

    private Activity activity;

    private static AppleSignIn instance;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    private AppleSignIn() {

    }

    @Override
    public void onMethodCall(MethodCall call, final MethodChannel.Result result) {

        if (call.method.equals("signInApple")) {

            OAuthProvider.Builder provider = OAuthProvider.newBuilder("apple.com");
            provider.setScopes(new ArrayList<String>());

            FirebaseAuth auth = FirebaseAuth.getInstance();

            auth.signOut();

            auth.startActivityForSignInWithProvider(activity, provider.build())
                    .addOnSuccessListener(
                            new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    AuthCredential appleCredential = authResult.getCredential();
                                    try {
                                        Map credential = reflectAppleCredential(appleCredential);
                                        result.success(credential);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        result.error("Credential reflection failed", e.getMessage(), null);
                                    }
                                }
                            })
                    .addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    result.error(e.getClass().getSimpleName(), e.getMessage(), null);
                                }
                            });
        } else {
            result.notImplemented();
        }
    }

    private Map reflectAppleCredential(AuthCredential authCredential) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        Method getIdToken = authCredential.getClass().getDeclaredMethod("getIdToken");
        Method getAccessToken = authCredential.getClass().getDeclaredMethod("getAccessToken");

        getIdToken.setAccessible(true);
        getAccessToken.setAccessible(true);

        String idToken = (String) getIdToken.invoke(authCredential);
        String accessToken = (String) getAccessToken.invoke(authCredential);

        Map credential = new HashMap();
        credential.put("idToken", idToken);
        credential.put("accessToken", accessToken);

        return credential;
    }

    public static AppleSignIn getInstance() {

        if (instance == null) {
            instance = new AppleSignIn();
        }

        return instance;
    }
}
