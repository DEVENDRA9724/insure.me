package com.project.staragile.insureme;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InsureMeApplicationTests {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    private static Policy testPolicy;
    
    @BeforeAll
    static void setup() {
        testPolicy = new Policy("POL9999", "Test Insurance", "Life Insurance", 
                                30000.00, "1000000", "Test Nominee", "ACTIVE");
    }
    
    @Test
    @Order(1)
    void testCreatePolicy() {
        ResponseEntity<Policy> response = restTemplate.postForEntity(
            "/createPolicy", testPolicy, Policy.class);
        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("POL9999", response.getBody().getPolicyId());
        System.out.println("? Create Policy Test Passed");
    }
    
    @Test
    @Order(2)
    void testViewPolicy() {
        // First create policy
        restTemplate.postForEntity("/createPolicy", testPolicy, Policy.class);
        
        // Then view it
        ResponseEntity<Policy> response = restTemplate.getForEntity(
            "/viewPolicy/POL9999", Policy.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Insurance", response.getBody().getPolicyName());
        System.out.println("? View Policy Test Passed");
    }
    
    @Test
    @Order(3)
    void testUpdatePolicy() {
        // Create policy first
        restTemplate.postForEntity("/createPolicy", testPolicy, Policy.class);
        
        // Update policy
        testPolicy.setPolicyName("Updated Test Insurance");
        testPolicy.setPremiumAmount(35000.00);
        
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Policy> requestEntity = new HttpEntity<>(testPolicy, headers);
        
        ResponseEntity<Policy> response = restTemplate.exchange(
            "/updatePolicy/POL9999", HttpMethod.PUT, requestEntity, Policy.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Test Insurance", response.getBody().getPolicyName());
        System.out.println("? Update Policy Test Passed");
    }
    
    @Test
    @Order(4)
    void testDeletePolicy() {
        // Create policy first
        restTemplate.postForEntity("/createPolicy", testPolicy, Policy.class);
        
        // Delete policy
        ResponseEntity<String> response = restTemplate.exchange(
            "/deletePolicy/POL9999", HttpMethod.DELETE, null, String.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("successfully"));
        System.out.println("? Delete Policy Test Passed");
    }
    
    @Test
    void testViewNonExistentPolicy() {
        ResponseEntity<Policy> response = restTemplate.getForEntity(
            "/viewPolicy/NONEXISTENT", Policy.class);
        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        System.out.println("? Non-existent Policy Test Passed");
    }
}
