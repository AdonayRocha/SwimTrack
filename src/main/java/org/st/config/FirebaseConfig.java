package org.st.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import jakarta.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initialize() {
        try {
            Class.forName("com.google.firebase.FirebaseApp");
            
            InputStream serviceAccount = new ClassPathResource("swimtrack-23e56-firebase-adminsdk-fbsvc-ee8197a1f7.json").getInputStream();
            
            Class<?> googleCredentialsClass = Class.forName("com.google.auth.oauth2.GoogleCredentials");
            Class<?> firebaseOptionsClass = Class.forName("com.google.firebase.FirebaseOptions");
            Class<?> firebaseAppClass = Class.forName("com.google.firebase.FirebaseApp");
            
            Object credentials = googleCredentialsClass.getMethod("fromStream", InputStream.class)
                .invoke(null, serviceAccount);
            
            Object optionsBuilder = firebaseOptionsClass.getMethod("builder")
                .invoke(null);
                
            optionsBuilder.getClass().getMethod("setCredentials", googleCredentialsClass)
                .invoke(optionsBuilder, credentials);
                
            Object options = optionsBuilder.getClass().getMethod("build")
                .invoke(optionsBuilder);
            
            java.util.List<?> apps = (java.util.List<?>) firebaseAppClass.getMethod("getApps")
                .invoke(null);
                
            if (apps.isEmpty()) {
                firebaseAppClass.getMethod("initializeApp", firebaseOptionsClass)
                    .invoke(null, options);
                System.out.println("Firebase inicializado com sucesso!");
            }
        } catch (Exception e) {
            System.err.println("Aviso: Não foi possível inicializar Firebase: " + e.getMessage());
            System.err.println("A aplicação continuará funcionando sem Firebase.");
        }
    }
}