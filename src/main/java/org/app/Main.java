package org.app;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.app.config.AppConfig;

import java.util.ArrayList;
import java.util.List;

public class Main {

    @Getter
    private static JDA jda = null;
    @Getter
    private static AppConfig appConfig = null;

    public static void main(String[] args) {
        appConfig = new AppConfig();
        String token = appConfig.getAppConfigObject().getDiscordToken();

        List<GatewayIntent> intents = new ArrayList<>(List.of(
                GatewayIntent.MESSAGE_CONTENT,
                GatewayIntent.GUILD_MESSAGES
        ));
        jda = JDABuilder.createDefault(token, intents)
                .build();
    }
}
