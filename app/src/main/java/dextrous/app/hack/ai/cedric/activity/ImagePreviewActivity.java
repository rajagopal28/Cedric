package dextrous.app.hack.ai.cedric.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

import static dextrous.app.hack.ai.cedric.constant.CedricConstants.INTENT_PARAM_IMAGE_FILE_PATH;

public class ImagePreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);
        ImageView previewImage = (ImageView) findViewById(R.id.image_preview);
        String fileName = getIntent().getStringExtra(INTENT_PARAM_IMAGE_FILE_PATH);
        if(previewImage != null
                && fileName != null) {
            File imgFile = new  File(fileName);
            if(imgFile.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                previewImage.setImageBitmap(myBitmap);
            }
        }
        Button uploadButton = (Button)findViewById(R.id.upload_image);
        if(uploadButton != null) {
            uploadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Make API call with image path and trigger open result activity
                }
            });
        }
    }
}
