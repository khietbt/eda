package io.github.khietbt.eda.services.adminservice.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

@ConfigurationProperties(prefix = SpringBootAdminInstanceAuthOauth2Properties.PREFIX)
@Data
public class SpringBootAdminInstanceAuthOauth2Properties {
    public final static String PREFIX = "spring.boot.admin.instance-auth.oauth2";

    private Set<String> scope;
    private String accessTokenUri;
    private String clientId;
    private String clientSecret;
    private String registrationId;
}
