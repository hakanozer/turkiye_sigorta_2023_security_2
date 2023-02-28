package com.works.controllers;

import com.works.entities.Admin;
import com.works.services.LoginService;
import com.works.services.TinkEncDec;
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
    final TinkEncDec tinkEncDec;

    @GetMapping("/")
    public String login() {
        String cipherText = tinkEncDec.encrypt("12345");
        System.out.println( cipherText );
        return "login";
    }

    @PostMapping("/adminLogin")
    public String adminLogin(@Valid Admin admin, BindingResult result, Model model) {
        if ( result.hasErrors() ) {
            List<FieldError> errors = result.getFieldErrors();
            model.addAttribute("errors", errors);
            return "login";
        }
        //boolean status = loginService.login(admin);
        boolean status = loginService.loginDB(admin);
        if ( status ) {
            return "redirect:/dashboard";
        }
        return "login";
    }

}
