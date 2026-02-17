package org.app.task;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class QiitaArticle {
    private String id;
    private String title;
    private String body;
    private String url;
    @SerializedName(value = "create_at")
    private String createAt;
    @Override
    public String toString() {
        String temp = "QiitaArticle { id: %s, url: %s, title: %s, body: %s, createAt: %s }";
        return String.format(
                temp,
                this.id,
                this.url,
                this.title,
                this.body,
                this.createAt
        );
    }
}
