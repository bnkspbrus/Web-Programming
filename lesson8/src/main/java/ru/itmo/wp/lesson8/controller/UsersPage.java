package ru.itmo.wp.lesson8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.lesson8.form.UserDisableEnableForm;
import ru.itmo.wp.lesson8.form.validator.UserDisableEnableValidator;
import ru.itmo.wp.lesson8.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UsersPage extends Page {
    private final UserService userService;
    private final UserDisableEnableValidator userDisableCredentialsValidator;

    @InitBinder("disableEnableForm")
    public void initDisableEnableForm(WebDataBinder binder) {
        binder.addValidators(userDisableCredentialsValidator);
    }

    public UsersPage(UserService userService, UserDisableEnableValidator userDisableCredentialsValidator) {
        this.userService = userService;
        this.userDisableCredentialsValidator = userDisableCredentialsValidator;
    }

    @GetMapping("/users/all")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "UsersPage";
    }

    @PostMapping("/disableEnableUser")
    public String disableEnableUser(
            @Valid @ModelAttribute("disableEnableForm") UserDisableEnableForm disableEnableForm,
            BindingResult bindingResult, Model model, HttpSession httpSession) {
        if (bindingResult.hasErrors() || disableEnableForm.getUserId() != getUser(httpSession).getId()) {
            return users(model);
        }
        userService.updateDisabled(disableEnableForm.getUserDisableId(), disableEnableForm.isDisabled());
        setMessage(httpSession, "You successfully changed users status");
        return "redirect:/users/all";
    }
}
