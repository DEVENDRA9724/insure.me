package com.project.staragile.insureme;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "POLICY")
public class Policy {
    @Id
    private String policyId;
    private String policyName;
    private String policyType;
    private double premiumAmount;
    private String coverageAmount;
    private String nomineeName;
    private String policyStatus;
    
    public Policy() {}
    
    public Policy(String policyId, String policyName, String policyType, double premiumAmount, 
                  String coverageAmount, String nomineeName, String policyStatus) {
        this.policyId = policyId;
        this.policyName = policyName;
        this.policyType = policyType;
        this.premiumAmount = premiumAmount;
        this.coverageAmount = coverageAmount;
        this.nomineeName = nomineeName;
        this.policyStatus = policyStatus;
    }
    
    // Getters
    public String getPolicyId() { return policyId; }
    public String getPolicyName() { return policyName; }
    public String getPolicyType() { return policyType; }
    public double getPremiumAmount() { return premiumAmount; }
    public String getCoverageAmount() { return coverageAmount; }
    public String getNomineeName() { return nomineeName; }
    public String getPolicyStatus() { return policyStatus; }
    
    // Setters
    public void setPolicyId(String policyId) { this.policyId = policyId; }
    public void setPolicyName(String policyName) { this.policyName = policyName; }
    public void setPolicyType(String policyType) { this.policyType = policyType; }
    public void setPremiumAmount(double premiumAmount) { this.premiumAmount = premiumAmount; }
    public void setCoverageAmount(String coverageAmount) { this.coverageAmount = coverageAmount; }
    public void setNomineeName(String nomineeName) { this.nomineeName = nomineeName; }
    public void setPolicyStatus(String policyStatus) { this.policyStatus = policyStatus; }
}
