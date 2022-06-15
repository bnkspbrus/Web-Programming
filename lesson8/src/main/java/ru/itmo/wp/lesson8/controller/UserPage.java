package ru.itmo.wp.lesson8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wp.lesson8.domain.User;
import ru.itmo.wp.lesson8.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Pattern;

@Validated
@Controller
public class UserPage extends Page {
    private final UserService userService;

    public UserPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/{id}")
    public String profile(
            @PathVariable String id,
            Model model) {
        User user = null;
        try {
            user = userService.findById(Long.parseLong(id));
        } catch (NumberFormatException ignored) {

        }
        if (user != null) {
            model.addAttribute("user_to_profile", user);
        }
        return "UserPage";
    }
}
