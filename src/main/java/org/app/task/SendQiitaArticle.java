package org.app.task;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.app.Main;

import java.io.IOException;

public class SendQiitaArticle {
    private final OkHttpClient okHttpClient;
    private String endPoint;

    public SendQiitaArticle() {
        this.okHttpClient = new OkHttpClient();
        this.endPoint = Main.getAppConfig().getQiitaAPIEndPoint();
    }

    public void fetchArticle() {
        HttpUrl url = HttpUrl.get(this.endPoint).newBuilder()
                .addQueryParameter("page", "1")
                .addQueryParameter("per_page", "5")
                .addQueryParameter("query", "AI")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
