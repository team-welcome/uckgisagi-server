package server.uckgisagi.config.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import server.uckgisagi.common.util.YamlPropertySourceFactory;

import java.io.IOException;

@Slf4j
@Component
@PropertySource(value = "classpath:application-firebase.yml", factory = YamlPropertySourceFactory.class, ignoreResourceNotFound = true)
public class FirebaseInitializer {

    @Value("${firebase.fcm.config.path}")
    private String firebaseConfigPath;

    private static FirebaseApp firebaseApp;

    @Bean
    public void initFirebase() throws IOException {
        ClassPathResource resource = new ClassPathResource(firebaseConfigPath);
        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(resource.getInputStream());
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(googleCredentials)
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            firebaseApp = FirebaseApp.initializeApp(options);
            log.info("✅ FirebaseApp Initialization Complete 🚀");
        }
    }

    public static FirebaseMessaging getFirebaseMessaging() {
        return FirebaseMessaging.getInstance(firebaseApp);
    }
}
