package io.github.soumikuxd.miniblogbackend.article;

import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, String> {

}
