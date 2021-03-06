package com.project.validator;

import com.project.models.User;
import com.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "Required");
        if (user.getLogin().length() > 256) {
            errors.rejectValue("login", "Size.userForm.login", "Login need to be less than 256 characters ");
        }
        if (userService.findByLogin(user.getLogin()) != null) {
            errors.rejectValue("login", "login.dublicate", "Such username already exists");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Password must not be empty.");

        Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$" );
        Matcher matcher= pattern.matcher(user.getPassword());
         if (!matcher.matches())  errors.rejectValue("password", "password.error", "The password should consist of uppercase and lowercase letters and numbers");

    }
}
