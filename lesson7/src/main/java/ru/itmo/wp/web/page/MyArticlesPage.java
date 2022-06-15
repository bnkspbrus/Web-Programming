package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.web.annotation.Json;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class MyArticlesPage extends Page {
    private final ArticleService articleService = new ArticleService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        HttpSession session = request.getSession();
        authorization(request);
        view.put("myArticles", articleService.findAllByUserId(((User) session.getAttribute("user")).getId()));
    }

    @Json
    private void invertStatus(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        HttpSession session = request.getSession();
        authorization(request);
        if (request.getParameter("articleId") == null || request.getParameter("status") == null) {
            throw new ValidationException("Wrong request");
        }
        Article article = articleService.validateArticleId(request);
        article.setHidden(Article.Status.valueOf(request.getParameter("status")));
        if (getUser(request).getId() != article.getUserId()) {
            setMessage(request, "This are not your articles");
            throw new RedirectException("/index");
        } else {
            articleService.invertStatus(article);
        }
    }
}
