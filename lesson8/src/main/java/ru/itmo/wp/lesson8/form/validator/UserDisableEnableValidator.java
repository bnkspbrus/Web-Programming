package ru.itmo.wp.lesson8.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.lesson8.domain.User;
import ru.itmo.wp.lesson8.form.UserDisableEnableForm;
import ru.itmo.wp.lesson8.service.UserService;

@Component
public class UserDisableEnableValidator implements Validator {
    private final UserService userService;

    public UserDisableEnableValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDisableEnableForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            UserDisableEnableForm disableForm = (UserDisableEnableForm) target;
            User user = userService.findById(disableForm.getUserDisableId());
            if (user == null) {
                errors.rejectValue("disabled", "can't.find.chosen.user", "Can't find chosen user user");
            }

        }
    }
}
