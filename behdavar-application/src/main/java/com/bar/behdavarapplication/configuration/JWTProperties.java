package com.bar.behdavarapplication.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JWTProperties {

    private String secret;

    private Integer expiry;

    private String signatureAlgorithmName;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Integer getExpiry() {
        return expiry;
    }

    public void setExpiry(Integer expiry) {
        this.expiry = expiry;
    }

    public String getSignatureAlgorithmName() {
        return signatureAlgorithmName;
    }

    public void setSignatureAlgorithmName(String signatureAlgorithmName) {
        this.signatureAlgorithmName = signatureAlgorithmName;
    }
}
