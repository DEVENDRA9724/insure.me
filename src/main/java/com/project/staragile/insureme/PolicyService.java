package com.project.staragile.insureme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyService {
    
    @Autowired
    PolicyRepository policyRepository;
    
    public Policy createPolicy(Policy policy) {
        return policyRepository.save(policy);
    }
    
    public Policy updatePolicy(String policyId, Policy policy) {
        if (policyRepository.existsById(policyId)) {
            policy.setPolicyId(policyId);
            return policyRepository.save(policy);
        }
        return null;
    }
    
    public Policy viewPolicy(String policyId) {
        return policyRepository.findById(policyId).orElse(null);
    }
    
    public String deletePolicy(String policyId) {
        if (policyRepository.existsById(policyId)) {
            policyRepository.deleteById(policyId);
            return "Policy deleted successfully";
        }
        return "Policy not found";
    }
}
