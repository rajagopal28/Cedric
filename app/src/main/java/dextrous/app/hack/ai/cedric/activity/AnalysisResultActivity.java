package dextrous.app.hack.ai.cedric.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import dextrous.app.hack.ai.cedric.layout.TagLayout;
import dextrous.app.hack.ai.cedric.model.AnalysisResult;
import dextrous.app.hack.ai.cedric.model.Caption;
import dextrous.app.hack.ai.cedric.model.TagDescription;

import static dextrous.app.hack.ai.cedric.constant.CedricConstants.INTENT_PARAM_DESCRIPTION_RESULT;

public class AnalysisResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_result);
        TextView identifiedCategoryView = (TextView) findViewById(R.id.category_value_label);
        TextView confidenceValueView = (TextView) findViewById(R.id.confidence_value_label);
        TagLayout tagLayout = (TagLayout) findViewById(R.id.tagLayout);
        Object temp = getIntent().getSerializableExtra(INTENT_PARAM_DESCRIPTION_RESULT);
        if (temp != null
                && temp instanceof AnalysisResult) {
            AnalysisResult analysisResult = (AnalysisResult) temp;
            TagDescription description = analysisResult.getDescription();
            if(description != null) {
                List<String> tags = description.getTags();
                if(tags != null
                        && !tags.isEmpty()) {
                    inflateTagsView(tagLayout, tags);
                }
                List<Caption> captions = description.getCaptions();
                if(captions != null
                        && !captions.isEmpty()
                        && identifiedCategoryView != null
                        && confidenceValueView != null) {
                    Caption topCaption = captions.get(0);
                    identifiedCategoryView.setText(topCaption.getText());
                    confidenceValueView.setText(String.valueOf(topCaption.getConfidence()));
                }
            }

        }
    }

    private void inflateTagsView(TagLayout tagLayout, List<String> tags) {
        LayoutInflater layoutInflater = getLayoutInflater();
        for (String tag : tags) {
            View tagView = layoutInflater.inflate(R.layout.tag_item_view, null, false);
            TextView tagTextView = (TextView) tagView.findViewById(R.id.tagTextView);
            tagTextView.setText(tag);
            tagLayout.addView(tagView);
        }
    }
}
