package org.st.auth;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
                Class<?> firebaseAuthClass = Class.forName("com.google.firebase.auth.FirebaseAuth");
                Class<?> firebaseTokenClass = Class.forName("com.google.firebase.auth.FirebaseToken");
                
                Object firebaseAuth = firebaseAuthClass.getMethod("getInstance").invoke(null);
                Object decodedToken = firebaseAuthClass.getMethod("verifyIdToken", String.class)
                    .invoke(firebaseAuth, idToken);
                
                String uid = (String) firebaseTokenClass.getMethod("getUid").invoke(decodedToken);
                String email = (String) firebaseTokenClass.getMethod("getEmail").invoke(decodedToken);
                
                UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(
                        email != null ? email : uid,
                        null,
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                    );
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (ClassNotFoundException e) {
                System.err.println("Firebase não disponível, ignorando token: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Erro ao verificar token Firebase: " + e.getMessage());
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        chain.doFilter(request, response);
    }
}