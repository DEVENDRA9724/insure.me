package com.project.staragile.insureme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PolicyController {
    
    @Autowired
    private PolicyService policyService;
    
    @PostMapping("/createPolicy")
    public ResponseEntity<Policy> createPolicy(@RequestBody Policy policy) {
        Policy created = policyService.createPolicy(policy);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    @GetMapping("/viewPolicy/{policyId}")
    public ResponseEntity<Policy> viewPolicy(@PathVariable String policyId) {
        Policy policy = policyService.viewPolicy(policyId);
        if (policy != null) {
            return ResponseEntity.ok(policy);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/updatePolicy/{policyId}")
    public ResponseEntity<Policy> updatePolicy(@PathVariable String policyId, @RequestBody Policy policy) {
        Policy updated = policyService.updatePolicy(policyId, policy);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/deletePolicy/{policyId}")
    public ResponseEntity<String> deletePolicy(@PathVariable String policyId) {
        String result = policyService.deletePolicy(policyId);
        if (result.contains("successfully")) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }
}
