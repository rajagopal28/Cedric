package dextrous.app.hack.ai.cedric.activity;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import dextrous.app.hack.ai.cedric.helper.AndroidHelper;
import dextrous.app.hack.ai.cedric.view.CameraPreview;

import static dextrous.app.hack.ai.cedric.constant.CedricConstants.APP_IMAGES_DIRECTORY;
import static dextrous.app.hack.ai.cedric.constant.CedricConstants.APP_ROOT_DIRECTORY;
import static dextrous.app.hack.ai.cedric.constant.CedricConstants.DATE_FORMAT_IN_FILE_NAME;
import static dextrous.app.hack.ai.cedric.constant.CedricConstants.FILE_NAME_SYNTAX;
import static dextrous.app.hack.ai.cedric.constant.CedricConstants.INTENT_PARAM_IMAGE_FILE_PATH;

public class CaptureImageActivity extends AppCompatActivity implements Camera.PictureCallback {
    private CaptureImageActivity self = this;
    private Camera mCamera;
    private CameraPreview mCameraPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_image);
        AndroidHelper.checkAndPromptCameraRequestLocationIfNotGranted(this);
        if(AndroidHelper.checkCameraPermissionGranted(this)) {
            mCamera = getCameraInstance();
            mCameraPreview = new CameraPreview(self, mCamera);
            FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
            if (preview != null) {
                preview.addView(mCameraPreview);
            }

            Button captureButton = (Button) findViewById(R.id.capture_button);
            if (captureButton != null) {
                captureButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCamera.takePicture(null, null, self);
                    }
                });
            }
        }

    }

    /**
     * Helper method to access the camera returns null if it cannot get the
     * camera or does not exist
     *
     * @return
     */
    private Camera getCameraInstance() {
        Camera camera = null;
        try {
            camera = Camera.open();
        } catch (Exception e) {
            // cannot get camera or does not exist
            e.printStackTrace();
        }
        return camera;
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        AndroidHelper.checkAndPromptStorageWritePermissionIfNotGranted(this);
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            return;
        }
        if(AndroidHelper.checkWriteStoragePermissionGranted(this)) {
            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
                // call the preview activity to preview the image and upload
                Intent intent = new Intent(getApplicationContext(), ImagePreviewActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(INTENT_PARAM_IMAGE_FILE_PATH, pictureFile.getAbsolutePath());
                startActivity(intent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(APP_ROOT_DIRECTORY),
                APP_IMAGES_DIRECTORY);
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.e("MyCameraApp", "failed to create directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat(DATE_FORMAT_IN_FILE_NAME, Locale.ENGLISH)
                .format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator
                + String.format(FILE_NAME_SYNTAX, timeStamp));

        return mediaFile;
    }
}
