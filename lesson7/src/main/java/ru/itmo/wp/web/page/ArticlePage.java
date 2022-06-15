package ru.itmo.wp.web.page;

import org.checkerframework.checker.units.qual.A;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.web.annotation.Json;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class ArticlePage extends Page {

    private final ArticleService articleService = new ArticleService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            session.setAttribute("message", "User is not authenticated");
            throw new RedirectException("/index");
        }
    }

    @Json
    private void addArticle(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        Article article = new Article();
        article.setTitle(request.getParameter("title"));
        article.setText(request.getParameter("text"));
        HttpSession session = request.getSession();
        article.setUserId(((User) session.getAttribute("user")).getId());
        articleService.validate(article);
        articleService.save(article);
        setMessage(request, "You are successfully add new article");
        throw new RedirectException("/index");
    }
}
