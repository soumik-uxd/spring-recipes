package io.github.soumikuxd.miniblogbackend.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getAllArticles() {
        List<Article> articles = new ArrayList<>();
        articleRepository.findAll().forEach(articles::add);
        return articles;
    }

    public Article getArticle(String id) {
        return articleRepository.findById(id).get();
    }

    public void addArticle(Article article) {
        articleRepository.save(article);
    }

    public void updateArticle(String id, Article article) {
        articleRepository.save(article);
    }

    public void deleteArticle(String id) {
        articleRepository.deleteById(id);
    }
}
