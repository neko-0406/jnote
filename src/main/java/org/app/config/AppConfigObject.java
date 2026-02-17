package org.app.config;

import lombok.Getter;
import lombok.Setter;

public class AppConfigObject {
    @Getter
    @Setter
    private String discordToken;
    @Getter
    @Setter
    private String qiitaAPIEndPoint;
    @Getter
    @Setter
    private String qiitaChannelId;
}
