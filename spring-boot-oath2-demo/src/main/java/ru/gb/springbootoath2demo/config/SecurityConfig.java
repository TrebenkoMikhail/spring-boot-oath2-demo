package ru.gb.springbootoath2demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.Map;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return  http.authorizeHttpRequests()
                .requestMatchers("/api**")
                .hasAuthority("api.resource")
                .anyRequest()
                .authenticated()
                .and()
//                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .oauth2ResourceServer(jwtConfigurer -> {
                    jwtConfigurer.jwt(jwtCustomazer -> {
                        JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
                        authenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
                            Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
                            List<String> roles = (List<String>) realmAccess.get("roles");

                            return roles.stream()
                                    .map(SimpleGrantedAuthority::new)
                                    .map(it -> (GrantedAuthority) it)
                                    .toList();
                        });
                        jwtCustomazer.jwtAuthenticationConverter(authenticationConverter);
                    });
                })
                .build();
    }
}
