package dextrous.app.hack.ai.cedric.service;

import dextrous.app.hack.ai.cedric.model.AnalysisResult;
import dextrous.app.hack.ai.cedric.model.ImageDescription;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface OxfordService {
    @Headers({
            "Content-Type: application/json"
    })
    @Multipart
    @POST("describe?maxCandidates=1")
    Call<ImageDescription> describeImage(@Header("Ocp-Apim-Subscription-Key") String apiKey, @Part("file") RequestBody image);

    @Headers({
            "Content-Type: application/json"
    })
    @Multipart
    @POST("analyze?maxCandidates=1")
    Call<AnalysisResult> analyzeImage(@Header("Ocp-Apim-Subscription-Key") String apiKey, @Part("file") RequestBody image);
}
