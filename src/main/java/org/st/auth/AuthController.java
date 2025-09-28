package org.st.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.st.user.User;
import org.st.user.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        User user = userService.findByEmail(req.getEmail());
        if (user != null) {
            return ResponseEntity.ok("Login successful for: " + user.getName());
        }
        return ResponseEntity.badRequest().body("Invalid credentials");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        try {
            User user = userService.register(req.getName(), req.getEmail(), req.getPassword());
            return ResponseEntity.ok("Registration successful for: " + user.getName());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/firebase-login")
    public ResponseEntity<?> firebaseLogin(@RequestBody FirebaseLoginRequest req) {
        try {
            // Verificar o token Firebase (já implementado no FirebaseAuthenticationFilter)
            // Aqui você pode adicionar lógica específica para o login Google
            
            // Verificar se o usuário já existe na base de dados
            User user = userService.findByEmail(req.getEmail());
            if (user == null && req.getName() != null) {
                // Criar usuário automaticamente se não existir
                user = new User();
                user.setName(req.getName());
                user.setEmail(req.getEmail());
                user = userService.save(user);
            }
            
            return ResponseEntity.ok("Firebase login successful for: " + req.getEmail());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Firebase login failed: " + e.getMessage());
        }
    }
    
    // Classes internas para requests
    public static class LoginRequest {
        private String email;
        private String password;
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
    
    public static class RegisterRequest {
        private String name;
        private String email;
        private String password;
        
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
    
    public static class FirebaseLoginRequest {
        private String token;
        private String email;
        private String name;
        
        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }
}