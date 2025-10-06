package com.say.popo.snapshelf;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;


@SpringBootApplication
public class SnapShelfApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnapShelfApplication.class, args);
	}
	
  /*  @Bean
    public ApplicationRunner credentialsInitializer() {
        return args -> {
            String base64 = System.getenv("GOOGLE_APPLICATION_CREDENTIALS_BASE64");

            if (base64 != null && !base64.isEmpty()) {
                base64 = base64.replaceAll("\\s", "");  // 改行やスペース除去
                System.out.println("Base64 length: " + base64.length());

                byte[] decoded = Base64.getDecoder().decode(base64);
                Path tempFile = Files.createTempFile("gcp-key-", ".json");
                Files.write(tempFile, decoded);

                // Java Systemプロパティとしてセット（Google公式推奨）
                System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", tempFile.toAbsolutePath().toString());

                System.out.println("✅ Google credentials set to: " + tempFile.toAbsolutePath());
            } else {
                System.err.println("❌ GOOGLE_APPLICATION_CREDENTIALS_BASE64 is not set.");
            }
        };
    }*/
}
