package io.github.soumikuxd.miniblogbackend.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/articles")
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @RequestMapping("/article/{id}")
    public Article getArticle(@PathVariable String id) {
        return articleService.getArticle(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/article")
    public void addArticle(@RequestBody Article article) {
        articleService.addArticle(article);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/article/{id}")
    public void updateArticle(@PathVariable String id, @RequestBody Article article) {
        articleService.updateArticle(id, article);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/article/{id}")
    public void deleteArticle(@PathVariable String id) {
        articleService.deleteArticle(id);
    }
}
