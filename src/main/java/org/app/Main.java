package org.app;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.app.config.AppConfig;
import org.app.task.SendQiitaArticle;
import org.jetbrains.annotations.NotNull;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main extends ListenerAdapter {

    @Getter
    private static JDA jda = null;
    @Getter
    private static AppConfig appConfig = null;
    private static SendQiitaArticle sendQiitaArticle = null;

    public static void main(String[] args) {
        appConfig = new AppConfig();
        sendQiitaArticle = new SendQiitaArticle();
        String token = appConfig.getDiscordToken();

        List<GatewayIntent> intents = new ArrayList<>(List.of(
                GatewayIntent.MESSAGE_CONTENT,
                GatewayIntent.GUILD_MESSAGES
        ));

        JDABuilder builder  = JDABuilder.createDefault(token, intents);
        builder.addEventListeners(new Main());

        jda = builder.build();
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.println("Name: "+ event.getState().name());
        System.out.println("==============bot is ready!!===============");

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> sendQiitaArticle.scheduleSendArticleToChannel(event.getJDA()), 0,1, TimeUnit.DAYS);
    }
}
