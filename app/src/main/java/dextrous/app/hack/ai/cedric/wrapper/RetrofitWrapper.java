package dextrous.app.hack.ai.cedric.wrapper;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dextrous.app.hack.ai.cedric.constant.CedricConstants;
import dextrous.app.hack.ai.cedric.service.OxfordService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static dextrous.app.hack.ai.cedric.constant.CedricConstants.DEFAULT_CONNECT_TIMEOUT;
import static dextrous.app.hack.ai.cedric.constant.CedricConstants.DEFAULT_READ_TIMEOUT;


public class RetrofitWrapper {

    private static Gson gson = new GsonBuilder()
            .setDateFormat(CedricConstants.SERVER_DATE_FORMAT)
            .create();
    //Date format Sun, 03 Apr 2016 13:30:39 GMT
    private static Retrofit retrofit = null;
    private RetrofitWrapper() {

    }

    private static void start(String baseURL) {
        baseURL = baseURL != null ? baseURL : CedricConstants.LOCAL_SERVER_URL;

        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(getUnsafeOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
    public static OxfordService build(String baseURL) {
        start(baseURL);
        return retrofit.create(OxfordService.class);
    }
    private static OkHttpClient getUnsafeOkHttpClient() {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            } };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();

            return new OkHttpClient.Builder()
                    .readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.MINUTES)
                    .connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .sslSocketFactory(sslSocketFactory)
                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
