package tech.shreshth.oneword.model;

import java.util.ArrayList;

public class News {
    private int totalArticles;
    private ArrayList<Articles> articles;

    public News(int totalArticles, ArrayList<Articles> articles) {
        this.totalArticles = totalArticles;
        this.articles = articles;
    }

    public int getTotalArticles() {
        return totalArticles;
    }

    public ArrayList<Articles> getArticles() {
        return articles;
    }
}


