package org.app.task;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.app.Main;

import java.io.IOException;
import java.util.List;

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
                Gson gson = new Gson();
                var listType = new TypeToken<List<QiitaArticle>>(){}.getType();
                List<QiitaArticle> article = gson.fromJson(response.body().string(), listType);
                for (QiitaArticle item : article) {
                    System.out.println(item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
