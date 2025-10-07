package com.say.popo.snapshelf.config;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageAnnotatorSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

@Configuration
public class GoogleConfig {

    @Value("${GOOGLE_APPLICATION_CREDENTIALS_BASE64:}")
    private String credentialsBase64;
  
    @Bean
    public CredentialsProvider credentialsProvider() throws IOException {
        GoogleCredentials credentials;

        if (credentialsBase64 != null && !credentialsBase64.isEmpty()) {
            byte[] decodedBytes = Base64.getDecoder().decode(credentialsBase64);
            credentials = GoogleCredentials.fromStream(new ByteArrayInputStream(decodedBytes));
        } else {
            ClassPathResource resource = new ClassPathResource("keys/vision-key.json");
            credentials = GoogleCredentials.fromStream(resource.getInputStream());
        }

        return FixedCredentialsProvider.create(credentials);
    }

    
    @Bean
    public ImageAnnotatorClient imageAnnotatorClient() throws IOException {
        GoogleCredentials credentials;

        if (credentialsBase64 != null && !credentialsBase64.isEmpty()) {
            // 本番環境：Base64デコード
            System.out.println("✅ 本番環境: Base64から認証情報を読み込み");
            byte[] decodedBytes = Base64.getDecoder().decode(credentialsBase64);
            ByteArrayInputStream credentialsStream = new ByteArrayInputStream(decodedBytes);
            credentials = GoogleCredentials.fromStream(credentialsStream);
        } else {
            // ローカル環境：ファイルから読み込み
            System.out.println("✅ ローカル環境: ファイルから認証情報を読み込み");
            ClassPathResource resource = new ClassPathResource("keys/vision-key.json");
            if (!resource.exists()) {
                throw new IllegalStateException("認証情報が見つかりません。" );
        }
            credentials = GoogleCredentials.fromStream(resource.getInputStream());
        }
        
        ImageAnnotatorSettings settings = ImageAnnotatorSettings.newBuilder()
                .setCredentialsProvider(() -> credentials)
                .build();

        return ImageAnnotatorClient.create(settings);
    }
}