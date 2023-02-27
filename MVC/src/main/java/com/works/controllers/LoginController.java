package com.works.controllers;

import com.works.entities.Admin;
import com.works.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginController {

    final LoginService loginService;

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/adminLogin")
    public String adminLogin(@Valid Admin admin, BindingResult result, Model model) {
        if ( result.hasErrors() ) {
            List<FieldError> errors = result.getFieldErrors();
            model.addAttribute("errors", errors);
            return "login";
        }
        boolean status = loginService.login(admin);
        if ( status ) {
            return "redirect:/dashboard";
        }
        return "login";
    }

}
