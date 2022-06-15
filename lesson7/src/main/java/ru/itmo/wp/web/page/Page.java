package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;

public abstract class Page {
    private final UserService userService = new UserService();
    private final ArticleService articleService = new ArticleService();

    protected void authorization(HttpServletRequest request) {
        if (getUser(request) == null) {
            setMessage(request, "User is not authenticated");
            throw new RedirectException("/index");
        }
    }

    protected void updateUserStatus(HttpServletRequest request) {
        request.getSession().setAttribute("user", userService.find(getUser(request).getId()));
    }

    protected void setMessage(HttpServletRequest request, String message) {
        request.getSession().setAttribute("message", message);
    }

    protected User getUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }

}
