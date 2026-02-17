package org.app.config;

import com.google.gson.Gson;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AppConfig {

    private AppConfigObject appConfigObject;

    public AppConfig() {
        this.appConfigObject = new AppConfigObject();
        this.loadConfigJson();
    }

    private void loadConfigJson() {
        Path configJsonPath = Path.of("./app-config.json");
        Gson gson = new Gson();

        try {
            if (!Files.exists(configJsonPath)) {
                System.out.println("コンフィグファイルがありません");
                return;
            }

            BufferedReader br = Files.newBufferedReader(configJsonPath);
            AppConfigObject object = gson.fromJson(br, AppConfigObject.class);
            this.appConfigObject = object;
        } catch (IOException | SecurityException e) {
            System.out.println("コンフィグファイルの読み込みに失敗しました");
            throw new RuntimeException(e);
        }

    }

    public String getDiscordToken() {
        return this.appConfigObject.getDiscordToken();
    }

    public String getQiitaAPIEndPoint() {
        return this.appConfigObject.getQiitaAPIEndPoint();
    }

    public String getQiitaChannelId() {
        return this.appConfigObject.getQiitaChannelId();
    }
}
