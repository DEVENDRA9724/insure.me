package com.project.staragile.insureme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InsureMeApplication implements CommandLineRunner {
    
    @Autowired
    private PolicyRepository policyRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(InsureMeApplication.class, args);
        System.out.println("=== InsureMe Insurance Application Started Successfully ===");
        System.out.println("=== Available APIs ===");
        System.out.println("POST   /createPolicy");
        System.out.println("PUT    /updatePolicy/{policyId}");
        System.out.println("GET    /viewPolicy/{policyId}");
        System.out.println("DELETE /deletePolicy/{policyId}");
    }
    
    @Override
    public void run(String... args) {
        if (policyRepository.count() == 0) {
            System.out.println("Loading initial policy data...");
            policyRepository.save(new Policy("POL1001", "Family Health Plus", "Health Insurance", 15000.00, "500000", "Rajesh Kumar", "ACTIVE"));
            policyRepository.save(new Policy("POL1002", "Secure Life Pro", "Life Insurance", 25000.00, "1000000", "Sunita Sharma", "ACTIVE"));
            policyRepository.save(new Policy("POL1003", "Auto Shield", "Car Insurance", 12000.00, "300000", "Amit Patel", "ACTIVE"));
            policyRepository.save(new Policy("POL1004", "Home Safe", "Home Insurance", 18000.00, "750000", "Priya Singh", "PENDING"));
            policyRepository.save(new Policy("POL1005", "Travel Guard", "Travel Insurance", 5000.00, "200000", "Michael John", "ACTIVE"));
            System.out.println("Loaded " + policyRepository.count() + " policies into database");
        }
    }
}
