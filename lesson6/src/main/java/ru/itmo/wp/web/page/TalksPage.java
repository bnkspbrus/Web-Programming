package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.TalkService;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class TalksPage extends Page {
    private final UserService userService = new UserService();
    private final TalkService talkService = new TalkService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("message", "User is not authenticated");
            throw new RedirectException("/index");
        }
        view.put("users", userService.findAll());
        User user = (User) request.getSession().getAttribute("user");
        List<Talk> talks = talkService.findAllByUserId(user.getId());
        view.put("talks", talks);
    }

    private void send(HttpServletRequest request, Map<String, Object> view) {
        view.put("users", userService.findAll());
        User user = getUser(request);
        if (user == null) {
            request.getSession().setAttribute("message", "User is not authenticated");
            throw new RedirectException("/talks");
        }
        Talk talk = new Talk();
        talk.setSourceUserId(user.getId());
        HttpSession session = request.getSession();
        User targetUser = userService.findByLogin(request.getParameter("targetUser"));
        if (targetUser == null) {
            session.setAttribute("message", "TargetUser login is incorrect");
            throw new RedirectException("/talks");
        }
        Object text = request.getParameter("text");
        if (text == null || text.toString().isBlank()) {
            session.setAttribute("message", "Text is incorrect");
            throw new RedirectException("/talks");
        }
        talk.setText(text.toString());
        talk.setTargetUserId(targetUser.getId());
        talkService.save(talk);
        List<Talk> talks = talkService.findAllByUserId(user.getId());
        view.put("talks", talks);
    }
}
