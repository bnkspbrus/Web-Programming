package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.EventService;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class EnterPage extends Page {
    private final UserService userService = new UserService();
    private final EventService eventService = new EventService();

    private void enter(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        String loginOrEmail = request.getParameter("loginOrEmail");
        String password = request.getParameter("password");
        if (Strings.isNullOrEmpty(loginOrEmail) || Strings.isNullOrEmpty(password)) {
            throw new ValidationException("Invalid login or password");
        }
        User user = userService.findByLoginAndPassword(loginOrEmail, password);
        if (user == null) {
            user = userService.findByEmailAndPassword(loginOrEmail, password);
        }
        if (user == null) {
            throw new ValidationException("Invalid login or password");
        }
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("message", "Hello, " + user.getLogin());
        Event event = new Event();
        event.setUserId(user.getId());
        event.setType(Event.Type.ENTER);
        eventService.save(event);
        throw new RedirectException("/index");
    }
}
