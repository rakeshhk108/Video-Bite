package com.rakesh.videoBite.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//enabling the web Security
@EnableWebSecurity
public class SecrtityConfig{


    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    //audience is the url from where we will receive the request
    @Value("${auth0.audience}")
    private String audiance;

    //to tell the spring to manage the lifecycle of the securityFilterChain
    @Bean
    public SecurityFilterChain FilterChain(HttpSecurity http) throws Exception{
        http
                //Set the authorization rules for the requests
                .authorizeHttpRequests()
                //permit all the url with starting "/videos/public"
                .requestMatchers("/videos/public**").permitAll()
                //except that any request should be authenticated
                .anyRequest()
                .authenticated()
                //the server will not store any session data(stateless)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //Customizer.withDefaults() allows request of all origins
                .cors(Customizer.withDefaults())
                //method configures the Spring Security module to act as a resource server that validates incoming JWTs.
                .oauth2ResourceServer()
                .jwt();

        return http.build();
    }

    //creating bean wich will decode the jwt
    @Bean
    JwtDecoder jwtDecoder(){



        //using nimbus jwtDecoder
        NimbusJwtDecoder jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuer);

        //validating the audience
        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audiance);
        //it is a method that creates an instance of OAuth2TokenValidator and withIssuer ensure that the tocken provider is a valid
        //so we are providing the url of the issue so that every time the jwt token it validates its source
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
        //By creating a DelegatingOAuth2TokenValidator with withIssuer and audienceValidator validators, withAudience ensures that the JWT token's issuer and audience claims are validated before the token is considered valid.
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer,audienceValidator);
        //When a JWT token is received, the jwtDecoder bean will decode the token using
        // the NimbusJwtDecoder instance and then validate it using the withAudience validator, which includes both withIssuer and audienceValidator validators.
        jwtDecoder.setJwtValidator(withAudience);

        return  jwtDecoder;
    }
}
