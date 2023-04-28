package com.rakesh.videoBite.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;


//it will check whether the request coming form a valid server
@RequiredArgsConstructor
public class AudienceValidator implements OAuth2TokenValidator<Jwt> {

    private final String audiance;


    @Override
    //the validate method takes the jwt object and checks weather the audiences in the jwt token is valid
    public OAuth2TokenValidatorResult validate(Jwt jwt) {
        //checking the audiance
        if(jwt.getAudience().contains(audiance))
        {
            //if it is valid then it will send a success message
            return OAuth2TokenValidatorResult.success();
        }
        //if it is not valid then send a failure message.
        return OAuth2TokenValidatorResult.failure(new OAuth2Error("invalid audiance fot the given tocken"));
    }
}
