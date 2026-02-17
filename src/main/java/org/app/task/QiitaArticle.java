package org.app.task;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QiitaArticle {
    @SerializedName("rendered_body")
    private String renderedBody;

    private String body;

    private boolean coediting;

    @SerializedName("comments_count")
    private int commentsCount;

    @SerializedName("created_at")
    private String createdAt;

    private Group group;

    private String id;

    @SerializedName("likes_count")
    private int likesCount;

    @SerializedName("private")
    private boolean isPrivate; // 'private'は予約語のため別名に

    @SerializedName("reactions_count")
    private int reactionsCount;

    @SerializedName("stocks_count")
    private int stocksCount;

    private List<Tag> tags;

    private String title;

    @SerializedName("updated_at")
    private String updatedAt;

    private String url;

    private User user;

    @SerializedName("page_views_count")
    private int pageViewsCount;

    @SerializedName("team_membership")
    private TeamMembership teamMembership;

    @SerializedName("organization_url_name")
    private String organizationUrlName;

    private boolean slide;

    // --- 内部クラス定義 (static class) ---

    public static class Group {
        @SerializedName("created_at")
        private String createdAt;

        private String description;

        private String name;

        @SerializedName("private")
        private boolean isPrivate;

        @SerializedName("updated_at")
        private String updatedAt;

        @SerializedName("url_name")
        private String urlName;
    }

    public static class Tag {
        private String name;
        private List<String> versions;
    }

    public static class User {
        private String description;

        @SerializedName("facebook_id")
        private String facebookId;

        @SerializedName("followees_count")
        private int followeesCount;

        @SerializedName("followers_count")
        private int followersCount;

        @SerializedName("github_login_name")
        private String githubLoginName;

        private String id;

        @SerializedName("items_count")
        private int itemsCount;

        @SerializedName("linkedin_id")
        private String linkedinId;

        private String location;

        private String name;

        private String organization;

        @SerializedName("permanent_id")
        private int permanentId;

        @SerializedName("profile_image_url")
        private String profileImageUrl;

        @SerializedName("team_only")
        private boolean teamOnly;

        @SerializedName("twitter_screen_name")
        private String twitterScreenName;

        @SerializedName("website_url")
        private String websiteUrl;
    }

    public static class TeamMembership {
        private String name;
    }
}
