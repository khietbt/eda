package io.github.khietbt.eda.services.userservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(
        final HttpSecurity http,
        final ClientRegistrationRepository clientRegistrationRepository
    ) throws Exception {
        http.authorizeHttpRequests(
                authorizationConfigurer -> {
                    authorizationConfigurer.requestMatchers("/actuator/**").permitAll();
                    authorizationConfigurer.anyRequest().authenticated();
                })
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
            .oauth2Login(Customizer.withDefaults())
            .logout(logoutConfigurer -> {
                var handler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);

                handler.setPostLogoutRedirectUri("{baseUrl}");

                logoutConfigurer.logoutSuccessHandler(handler);
            })
            .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
