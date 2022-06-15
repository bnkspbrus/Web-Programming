package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Article;

import java.util.List;

public interface ArticleRepository {

    void save(Article article);

    List<Article> findAll();

    List<Article> findAllByUserId(long userId);

    Article find(long id);

    void invertStatus(Article article);
}
