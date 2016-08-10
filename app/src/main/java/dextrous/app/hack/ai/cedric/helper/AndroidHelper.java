package dextrous.app.hack.ai.cedric.helper;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public class AndroidHelper {
    public static final int MY_WRITE_STORAGE_PERMISSION_REQUEST = 999;
    public static final int MY_READ_STORAGE_PERMISSION_REQUEST = 888;
    public static final int MY_CAMERA_PERMISSION_REQUEST = 777;

    public static ProgressDialog showProgressDialog(Context context, String titleText, String processingText) {
        ProgressDialog progress = new ProgressDialog(context);
        progress.setTitle(titleText);
        progress.setCancelable(Boolean.FALSE);
        progress.setCanceledOnTouchOutside(Boolean.FALSE);
        progress.setMessage(processingText);
        progress.show();
        return progress;
    }

    public static void checkAndPromptStorageWritePermissionIfNotGranted(Activity callingActivity) {
        if (!checkWriteStoragePermissionGranted(callingActivity)) {
            promptPermission(callingActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE, MY_WRITE_STORAGE_PERMISSION_REQUEST);
        }
    }

    public static void checkAndPromptStorageReadPermissionIfNotGranted(Activity callingActivity) {
        if (!checkReadStoragePermissionGranted(callingActivity)) {
            promptPermission(callingActivity, Manifest.permission.READ_EXTERNAL_STORAGE, MY_READ_STORAGE_PERMISSION_REQUEST);
        }
    }

    public static void checkAndPromptCameraRequestLocationIfNotGranted(Activity callingActivity) {
        if (!checkCameraPermissionGranted(callingActivity)) {
            promptPermission(callingActivity, Manifest.permission.CAMERA, MY_CAMERA_PERMISSION_REQUEST);
        }
    }

    public static boolean checkReadStoragePermissionGranted(Activity callingActivity) {
        return checkPermissionGranted(callingActivity, Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    public static boolean checkWriteStoragePermissionGranted(Activity callingActivity) {
        return checkPermissionGranted(callingActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    public static boolean checkCameraPermissionGranted(Activity callingActivity) {
        return checkPermissionGranted(callingActivity, Manifest.permission.CAMERA);
    }

    private static boolean checkPermissionGranted(Activity activity, String permissionString) {
        return ActivityCompat.checkSelfPermission(activity, permissionString) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, permissionString) == PackageManager.PERMISSION_GRANTED;
    }

    private static void promptPermission(Activity callingActivity,
                                         String permissionString, int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(callingActivity,
                permissionString)) {
            ActivityCompat.requestPermissions(callingActivity,
                    new String[]{permissionString},
                    requestCode);
        } else {
            ActivityCompat.requestPermissions(callingActivity,
                    new String[]{permissionString},
                    requestCode);
        }
    }

}
