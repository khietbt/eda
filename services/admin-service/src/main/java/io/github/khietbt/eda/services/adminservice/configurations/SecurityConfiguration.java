package io.github.khietbt.eda.services.adminservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityWebFilterChain filterChain(
        final ServerHttpSecurity http,
        final ReactiveClientRegistrationRepository repository
    ) {
        return http
            .authorizeExchange(spec -> spec.anyExchange().authenticated())
            .oauth2Login(Customizer.withDefaults())
            .oauth2ResourceServer(spec -> spec.jwt(Customizer.withDefaults()))
            .logout(spec -> spec.logoutSuccessHandler(oidcLogoutSuccessHandler(repository)))
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .build();
    }

    private ServerLogoutSuccessHandler oidcLogoutSuccessHandler(
        final ReactiveClientRegistrationRepository repository
    ) {
        var handler = new OidcClientInitiatedServerLogoutSuccessHandler(repository);

        handler.setPostLogoutRedirectUri("{baseUrl}");

        return handler;
    }
}
