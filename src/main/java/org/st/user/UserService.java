package org.st.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    private UserRepository userRepository;

    public User findByEmail(String email) {
        log.info("Buscando usuário por email: {}", email);
        return userRepository.findByEmail(email).orElse(null);
    }
    
    public User save(User user) {
        log.info("Salvando usuário: {}", user.getName());
        return userRepository.save(user);
    }
    
    public User register(String name, String email, String password) {
        log.info("Registrando novo usuário: {} - {}", name, email);
        
        // Verificar se o usuário já existe
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Usuário já existe com este email");
        }
        
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        
        // TODO: Hash da senha e salvamento
        
        User savedUser = userRepository.save(user);
        log.info("Usuário registrado com sucesso: {}", savedUser.getName());
        return savedUser;
    }
}