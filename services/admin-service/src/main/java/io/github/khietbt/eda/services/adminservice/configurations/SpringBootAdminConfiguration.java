package io.github.khietbt.eda.services.adminservice.configurations;

import de.codecentric.boot.admin.server.web.client.InstanceWebClientCustomizer;
import io.github.khietbt.eda.services.adminservice.properties.SpringBootAdminInstanceAuthOauth2Properties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.InMemoryReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@ConditionalOnProperty(
    prefix = SpringBootAdminInstanceAuthOauth2Properties.PREFIX,
    name = {"access-token-uri", "client-id", "client-secret", "registration-id", "scope"}
)
@EnableConfigurationProperties({SpringBootAdminInstanceAuthOauth2Properties.class})
public class SpringBootAdminConfiguration {
    @Bean
    public InstanceWebClientCustomizer instanceWebClientCustomizer(
        final SpringBootAdminInstanceAuthOauth2Properties properties
    ) {
        var client = ClientRegistration
            .withRegistrationId(properties.getRegistrationId())
            .clientId(properties.getClientId())
            .clientSecret(properties.getClientSecret())
            .tokenUri(properties.getAccessTokenUri())
            .scope(properties.getScope())
            .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
            .build();

        var repository = new InMemoryReactiveClientRegistrationRepository(client);
        var service = new InMemoryReactiveOAuth2AuthorizedClientService(repository);
        var provider = ReactiveOAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();

        var manager = new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(repository, service);
        manager.setAuthorizedClientProvider(provider);

        var filter = new ServerOAuth2AuthorizedClientExchangeFilterFunction(manager);
        filter.setDefaultClientRegistrationId(properties.getRegistrationId());

        return builder -> builder.webClient(WebClient.builder().filter(filter));
    }
}
