package dextrous.app.hack.ai.cedric.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import dextrous.app.hack.ai.cedric.helper.FileChooser;
import dextrous.app.hack.ai.cedric.model.AnalysisResult;
import dextrous.app.hack.ai.cedric.model.Caption;
import dextrous.app.hack.ai.cedric.model.TagDescription;

import static dextrous.app.hack.ai.cedric.constant.CedricConstants.INTENT_PARAM_DESCRIPTION_RESULT;
import static dextrous.app.hack.ai.cedric.constant.CedricConstants.INTENT_PARAM_IMAGE_FILE_PATH;
import static dextrous.app.hack.ai.cedric.constant.CedricConstants.VALID_IMAGE_FILE_EXTENSIONS;

public class HomeActivity extends AppCompatActivity implements FileChooser.FileSelectedListener {
    private HomeActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button chooseFileButton = (Button) findViewById(R.id.choose_button);
        if (chooseFileButton != null) {
            chooseFileButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FileChooser fileChooser = new FileChooser(self)
                            .setFileListener(self);
                    fileChooser.setExtension(VALID_IMAGE_FILE_EXTENSIONS);
                    fileChooser.showDialog();
                }
            });
        }
        Button captureImage = (Button) findViewById(R.id.capture_button);
        if (captureImage != null) {
            captureImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), CaptureImageActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void fileSelected(File file) {
        System.out.println(file.getAbsolutePath());
        // call the preview activity to preview the image and upload
        Intent intent = new Intent(getApplicationContext(), ImagePreviewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(INTENT_PARAM_IMAGE_FILE_PATH, file.getAbsolutePath());
        startActivity(intent);
    }

}
