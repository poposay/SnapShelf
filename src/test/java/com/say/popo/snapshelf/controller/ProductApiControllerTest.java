/*
 * package com.say.popo.snapshelf.controller;


import com.say.popo.snapshelf.dto.UpdateDescriptionRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
class ProductApiControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    void testPostMapping_正常系() {
        UpdateDescriptionRequestDto dto = new UpdateDescriptionRequestDto();
        dto.setId(1L);
        dto.setDescription("テストデータ");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UpdateDescriptionRequestDto> request = new HttpEntity<>(dto, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
            "/api/update_description", 
            request, 
            String.class);

        System.out.println("Response Status: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(2)
    void testWithSameJson() {
        String jsonPayload = "{\"id\":\"1\",\"description\":\"オシッコシート\"}";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> request = new HttpEntity<>(jsonPayload, headers);
        
        ResponseEntity<String> response = restTemplate.postForEntity(
            "/api/update_description",
            request,
            String.class);
            
        System.out.println("=== testWithSameJson ===");
        System.out.println("Request: " + jsonPayload);
        System.out.println("Response Status: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
        
        // ステータスコードが何であっても一旦確認
        System.out.println("実際のステータス: " + response.getStatusCode());
    }

    @Test
    @Order(3)
    void testWithNumericId() {
        String jsonPayload = "{\"id\":1,\"description\":\"数値IDテスト\"}";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> request = new HttpEntity<>(jsonPayload, headers);
        
        ResponseEntity<String> response = restTemplate.postForEntity(
            "/api/update_description",
            request,
            String.class);
            
        System.out.println("=== testWithNumericId ===");
        System.out.println("Request: " + jsonPayload);
        System.out.println("Response Status: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
        
        // ステータスコードが何であっても一旦確認
        System.out.println("実際のステータス: " + response.getStatusCode());
    }
}

*/