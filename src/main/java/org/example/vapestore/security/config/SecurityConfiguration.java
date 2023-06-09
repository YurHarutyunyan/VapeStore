package org.example.vapestore.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
@Bean

    public SecurityFilterChain securityfilterchain(HttpSecurity httpSecurity)throws Exception{
    httpSecurity
            .authorizeHttpRequests((requests) -> requests
                    .requestMatchers(new AntPathRequestMatcher("/auth/**")).permitAll()
                    .anyRequest().authenticated())
            .httpBasic();
    return httpSecurity.build();

}
}
