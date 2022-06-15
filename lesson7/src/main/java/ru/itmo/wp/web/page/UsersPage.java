package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.annotation.Json;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @noinspection unused
 */
public class UsersPage extends Page {
    private final UserService userService = new UserService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    private void findAll(HttpServletRequest request, Map<String, Object> view) {
        view.put("users", userService.findAll());
    }

    private void findUser(HttpServletRequest request, Map<String, Object> view) {
        view.put("user", userService.find(Long.parseLong(request.getParameter("userId"))));
    }

    @Json
    private void changeAdmin(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        if (request.getParameter("userId") == null || request.getParameter("status") == null) {
            throw new ValidationException("Wrong request");
        }
        User user = userService.validateUserId(request);
        String status = request.getParameter("status");
        if (getUser(request).isAdmin()) {
            userService.changeAdmin(user, status);
            updateUserStatus(request);
        } else {
            setMessage(request, "You are not admin");
            throw new RedirectException("/index");
        }
    }
}
