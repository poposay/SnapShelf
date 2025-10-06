package com.say.popo.snapshelf.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageAnnotatorSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

@Configuration
public class GoogleConfig {

    @Value("${google.credentials.base64:}")
    private String credentialsBase64;

    @Value("${google.credentials.location}")  
    private Resource credentialsLocation;      

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
            credentials = GoogleCredentials.fromStream(credentialsLocation.getInputStream());
        }

        ImageAnnotatorSettings settings = ImageAnnotatorSettings.newBuilder()
                .setCredentialsProvider(() -> credentials)
                .build();

        return ImageAnnotatorClient.create(settings);
    }
}