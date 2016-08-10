package dextrous.app.hack.ai.cedric.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import dextrous.app.hack.ai.cedric.callback.AnalysisResultCallback;
import dextrous.app.hack.ai.cedric.helper.AndroidHelper;
import dextrous.app.hack.ai.cedric.model.AnalysisResult;
import dextrous.app.hack.ai.cedric.service.OxfordService;
import dextrous.app.hack.ai.cedric.wrapper.RetrofitWrapper;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

import static dextrous.app.hack.ai.cedric.constant.CedricConstants.BITMAP_WIDTH_THRESHOLD;
import static dextrous.app.hack.ai.cedric.constant.CedricConstants.COMPRESSION_QUALITY;
import static dextrous.app.hack.ai.cedric.constant.CedricConstants.CONTENT_TYPE_OCTET_STREAM;
import static dextrous.app.hack.ai.cedric.constant.CedricConstants.DELIMITER_DOT;
import static dextrous.app.hack.ai.cedric.constant.CedricConstants.INTENT_PARAM_IMAGE_FILE_PATH;
import static dextrous.app.hack.ai.cedric.constant.CedricConstants.MAX_FILE_SIZE_LIMIT;
import static dextrous.app.hack.ai.cedric.constant.CedricConstants.SUFFIX_COMPRESSED;

public class ImagePreviewActivity extends AppCompatActivity {

    private Activity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);
        AndroidHelper.checkAndPromptStorageWritePermissionIfNotGranted(this);
        AndroidHelper.checkAndPromptStorageReadPermissionIfNotGranted(this);
        ImageView previewImage = (ImageView) findViewById(R.id.image_preview);
        String fileName = getIntent().getStringExtra(INTENT_PARAM_IMAGE_FILE_PATH);
        if(AndroidHelper.checkWriteStoragePermissionGranted(this)
                && AndroidHelper.checkReadStoragePermissionGranted(this)) {
            if (previewImage != null
                    && fileName != null) {
                File imgFile = new File(fileName);
                if (imgFile.exists()) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    int nh = (int) (myBitmap.getHeight() * ((BITMAP_WIDTH_THRESHOLD * 1.0) / myBitmap.getWidth()));
                    Bitmap scaled = Bitmap.createScaledBitmap(myBitmap, BITMAP_WIDTH_THRESHOLD, nh, true);
                    if (imgFile.length() >= MAX_FILE_SIZE_LIMIT) {
                        // compress only if file size exceeds limit
                        try {
                            OutputStream out;
                            int extensionIndex = fileName.lastIndexOf(DELIMITER_DOT);
                            String compressedFileName = fileName.substring(0, extensionIndex)
                                    + SUFFIX_COMPRESSED
                                    + fileName.substring(extensionIndex);
                            File compressedFile = new File(compressedFileName);
                            if (!compressedFile.exists()
                                    && compressedFile.createNewFile()) {
                                out = new FileOutputStream(compressedFile);
                                scaled.compress(Bitmap.CompressFormat.PNG, COMPRESSION_QUALITY, out);
                                fileName = compressedFileName;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    previewImage.setImageBitmap(scaled);
                }
            }
            Button uploadButton = (Button)findViewById(R.id.upload_image);
            if(uploadButton != null
                    && fileName != null) {
                final String finalFileName = fileName;
                uploadButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Make API call with image path and trigger open result activity
                        String BASE_URL = getResources().getString(R.string.base_api_url);
                        String API_KEY = getResources().getString(R.string.ocp_apim_subscription_key);
                        final OxfordService apiService = RetrofitWrapper.build(BASE_URL);

                        try {
                            File imageFile = new File(finalFileName);
                            InputStream in = new FileInputStream(imageFile);
                            byte[] buf;
                            buf = new byte[in.available()];
                            while (in.read(buf) != -1) {}
                            RequestBody requestFile =
                                    RequestBody.create(MediaType.parse(CONTENT_TYPE_OCTET_STREAM), buf);
                            Call<AnalysisResult> analysisResultCall = apiService.describeImage(API_KEY, requestFile);
                            analysisResultCall.enqueue(new AnalysisResultCallback(self));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                });
            }
        }
    }
}
