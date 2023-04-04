package com.rakesh.youtube_clone_backend.config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;


public class AudienceValidator implements OAuth2TokenValidator<Jwt> {

    private final String audiance;
    public AudienceValidator(String audiance) {
        this.audiance = audiance;
    }

    @Override
    public OAuth2TokenValidatorResult validate(Jwt jwt) {
        if(jwt.getAudience().contains(audiance))
        {
            return OAuth2TokenValidatorResult.success();
        }
        return OAuth2TokenValidatorResult.failure(new OAuth2Error("invalid audiance fot the given tocken"));
    }
}
