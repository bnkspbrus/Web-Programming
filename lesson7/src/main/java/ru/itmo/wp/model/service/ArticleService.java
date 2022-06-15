package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();

    public void validate(Article article) throws ValidationException {
        if (Strings.isNullOrEmpty(article.getTitle())) {
            throw new ValidationException("Title is null or empty");
        }
        if (article.getText() == null) {
            throw new ValidationException("Text is null");
        }
    }

    public void save(Article article) {
        articleRepository.save(article);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public List<Article> findAllByUserId(long userId) {
        return articleRepository.findAllByUserId(userId);
    }

    public Article find(long id) {
        return articleRepository.find(id);
    }

    public void invertStatus(Article article) {
        articleRepository.invertStatus(article);
    }

    public Article validateArticleId(HttpServletRequest request) throws ValidationException {
        try {
            return find(Long.parseLong(request.getParameter("articleId")));
        } catch (NumberFormatException ignore) {
            throw new ValidationException("Wrong request");
        }
    }
}
