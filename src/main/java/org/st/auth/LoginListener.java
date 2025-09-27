package org.st.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.st.user.UserService;

@Component
public class LoginListener implements ApplicationListener<AuthenticationSuccessEvent> {
    private static final Logger log = LoggerFactory.getLogger(LoginListener.class);
    private final UserService userService;

    public LoginListener(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        Object principal = event.getAuthentication().getPrincipal();
        
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            log.info("Logado com usuário: {}", userDetails.getUsername());
        } else {
            log.info("Logado com usuário: {}", principal.toString());
        }
        
        // TODO: Implementar registro/atualização de usuário se necessário
    }
}