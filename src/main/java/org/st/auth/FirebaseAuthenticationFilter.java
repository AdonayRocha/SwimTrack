package org.st.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

@Component
public class FirebaseAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        String idToken = req.getHeader("Authorization");
        if (idToken != null && idToken.startsWith("Bearer ")) {
            idToken = idToken.replace("Bearer ", "");
            try {
                // Verificar se Firebase está disponível
                Class<?> firebaseAuthClass = Class.forName("com.google.firebase.auth.FirebaseAuth");
                Class<?> firebaseTokenClass = Class.forName("com.google.firebase.auth.FirebaseToken");
                
                // Usar reflexão para verificar token
                Object firebaseAuth = firebaseAuthClass.getMethod("getInstance").invoke(null);
                Object decodedToken = firebaseAuthClass.getMethod("verifyIdToken", String.class)
                    .invoke(firebaseAuth, idToken);
                
                String uid = (String) firebaseTokenClass.getMethod("getUid").invoke(decodedToken);
                String email = (String) firebaseTokenClass.getMethod("getEmail").invoke(decodedToken);
                
                // Criar autenticação no Spring Security
                UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(
                        email != null ? email : uid,
                        null,
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                    );
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (ClassNotFoundException e) {
                // Firebase não disponível, ignorar autenticação Firebase
                System.err.println("Firebase não disponível, ignorando token: " + e.getMessage());
            } catch (Exception e) {
                // Erro ao verificar token
                System.err.println("Erro ao verificar token Firebase: " + e.getMessage());
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        chain.doFilter(request, response);
    }
}