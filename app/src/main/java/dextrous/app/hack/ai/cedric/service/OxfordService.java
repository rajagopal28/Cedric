package dextrous.app.hack.ai.cedric.service;

import dextrous.app.hack.ai.cedric.model.AnalysisResult;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface OxfordService {

    @POST("describe?maxCandidates=1") //&visualFeatures=Categories&details=Vegetation
    Call<AnalysisResult> describeImage(@Header("Ocp-Apim-Subscription-Key") String apiKey, @Body RequestBody image);


    @POST("analyze?maxCandidates=1") //&visualFeatures=Categories&details=Vegetation
    Call<AnalysisResult> analyzeImage(@Header("Ocp-Apim-Subscription-Key") String apiKey, @Body RequestBody image);
}
