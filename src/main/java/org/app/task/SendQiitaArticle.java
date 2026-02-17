package org.app.task;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.app.Main;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class SendQiitaArticle {
    private final OkHttpClient okHttpClient;
    private final String endPoint;

    public SendQiitaArticle() {
        this.okHttpClient = new OkHttpClient();
        this.endPoint = Main.getAppConfig().getQiitaAPIEndPoint();
    }

    private List<QiitaArticle> fetchArticle() {
        HttpUrl url = HttpUrl.get(this.endPoint).newBuilder()
                .addQueryParameter("page", "1")
                .addQueryParameter("per_page", "5")
                .addQueryParameter("query", "AI")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();

        List<QiitaArticle> article = List.of();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                Gson gson = new Gson();
                var listType = new TypeToken<List<QiitaArticle>>(){}.getType();
                article = gson.fromJson(response.body().string(), listType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return article;
    }

    public void scheduleSendArticleToChannel(JDA jda) {
        List<QiitaArticle> articleList = this.fetchArticle();
        TextChannel channel = jda.getTextChannelById(Main.getAppConfig().getQiitaChannelId());

        if (articleList.isEmpty()) {
            MessageEmbed messageEmbed = new EmbedBuilder()
                    .setTitle("Qiitaの記事の取得に失敗しました")
                    .build();
            channel.sendMessageEmbeds(messageEmbed).queue();
            return;
        }

        List<MessageEmbed> articleEmbeds = new ArrayList<>();
        for (QiitaArticle item: articleList) {
            articleEmbeds.add(
                    new EmbedBuilder()
                            .setTitle(item.getTitle())
                            .setDescription(item.getBody().substring(0, 200) + "...")
                            .setUrl(item.getUrl())
                            .setFooter("create at:" + item.getCreateAt())
                            .build()
            );
        }
        channel.sendMessageEmbeds(articleEmbeds).queue();
    }
}
