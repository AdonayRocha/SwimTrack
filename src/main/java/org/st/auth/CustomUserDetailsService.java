package org.st.auth;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Conta mocada para desenvolvimento
        if ("admin@admin.com".equals(username)) {
            return User.builder()
                    .username("admin@admin.com")
                    .password(passwordEncoder.encode("admin"))
                    .authorities("USER")
                    .build();
        }
        
        throw new UsernameNotFoundException("Usuário não encontrado: " + username);
    }
}