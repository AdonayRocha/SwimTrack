package org.st.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.st.user.User;
import org.st.user.UserService;

@Component
public class LoginListener implements ApplicationListener<AuthenticationSuccessEvent> {
    private static final Logger log = LoggerFactory.getLogger(LoginListener.class);
    private final UserService userService;

    public LoginListener(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(@org.springframework.lang.NonNull AuthenticationSuccessEvent event) {
        Object principal = event.getAuthentication().getPrincipal();
        

        if (principal instanceof UserDetails userDetails) {
            log.info("Logado com usuário: {}", userDetails.getUsername());
            
            // Usa o userService para verificar informações do usuário
            try {
                // Tenta encontrar o usuário por email (assumindo que username = email)
                User user = userService.findByEmail(userDetails.getUsername());
                if (user != null) {
                    log.debug("Usuário {} encontrado no sistema: {}", userDetails.getUsername(), user.getName());
                } else {
                    log.debug("Usuário {} não encontrado na base local", userDetails.getUsername());
                }
            } catch (Exception e) {
                log.warn("Erro ao acessar informações do usuário: {}", e.getMessage());
            }
        } else if (principal != null) {
            log.info("Logado com usuário: {}", principal.toString());
        } else {
            log.warn("Principal é null no evento de autenticação");
        }
    }
}