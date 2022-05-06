package tech.shreshth.oneword.model;

public class Articles{
    private String title;
    private String description;
    private String content;
    private String url;
    private String image;
    private String publishedAt;

    public Articles(String title, String description, String content, String url, String image, String publishedAt) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.url = url;
        this.image = image;
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    public String getPublishedAt() {
        return publishedAt;
    }
}
