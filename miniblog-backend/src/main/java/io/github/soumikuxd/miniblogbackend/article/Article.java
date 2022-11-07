package io.github.soumikuxd.miniblogbackend.article;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Article {
    @Id
    private String id;
    private String title;
    private String text;

    public  Article() {

    }

    public Article(String id, String title, String text) {
        super();
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
