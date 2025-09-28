package org.st.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthWebController {

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout,
                                @RequestParam(value = "registered", required = false) String registered,
                                Model model) {
        if (error != null) {
            model.addAttribute("error", "Usuário ou senha inválidos!");
        }
        if (logout != null) {
            model.addAttribute("message", "Logout realizado com sucesso!");
        }
        if (registered != null) {
            model.addAttribute("message", "Usuário cadastrado com sucesso! Faça login.");
        }
        return "login";
    }

    @GetMapping("/auth/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/auth/register")
    public String processRegisterForm(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("registered", "true");
        return "redirect:/login";
    }

    @GetMapping("/auth/google")
    public String googleAuth() {
        return "redirect:/login?error=google-not-configured";
    }

}
