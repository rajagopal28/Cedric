package dextrous.app.hack.ai.cedric.callback;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;

import dextrous.app.hack.ai.cedric.activity.AnalysisResultActivity;
import dextrous.app.hack.ai.cedric.helper.AndroidHelper;
import dextrous.app.hack.ai.cedric.model.AnalysisResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static dextrous.app.hack.ai.cedric.constant.CedricConstants.INTENT_PARAM_DESCRIPTION_RESULT;
import static dextrous.app.hack.ai.cedric.constant.CedricConstants.LOG_TAG_HTTP_ERROR;
import static dextrous.app.hack.ai.cedric.constant.CedricConstants.LOG_TAG_HTTP_RESPONSE;
import static dextrous.app.hack.ai.cedric.constant.CedricConstants.MSG_PROGRESS_DIALOG_MESSAGE;
import static dextrous.app.hack.ai.cedric.constant.CedricConstants.MSG_PROGRESS_DIALOG_TITLE;

public class AnalysisResultCallback implements Callback<AnalysisResult> {
    private ProgressDialog progress;
    private Activity context;

    public AnalysisResultCallback(Activity callingActivity) {
        this.context = callingActivity;
        this.progress = AndroidHelper.showProgressDialog(context, MSG_PROGRESS_DIALOG_TITLE, MSG_PROGRESS_DIALOG_MESSAGE);
    }

    @Override
    public void onResponse(Call<AnalysisResult> call, Response<AnalysisResult> response) {
        Log.d(LOG_TAG_HTTP_RESPONSE, response.body() != null ? response.body().toString() : LOG_TAG_HTTP_ERROR);
        // open the analysis result activity
        Intent intent = new Intent(context, AnalysisResultActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(INTENT_PARAM_DESCRIPTION_RESULT, response.body());
        context.startActivity(intent);
        this.progress.dismiss();
    }

    @Override
    public void onFailure(Call<AnalysisResult> call, Throwable t) {
        Log.e(LOG_TAG_HTTP_ERROR, t.getMessage(), t);
        this.progress.dismiss();
    }
}
