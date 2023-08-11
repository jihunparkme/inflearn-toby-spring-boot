package tobyspring.config.autoconfig;

import lombok.Getter;
import lombok.Setter;
import tobyspring.config.MyConfigurationProperties;

@Getter
@Setter
@MyConfigurationProperties(prefix = "server")
public class ServerProperties {
    private String contextPath;
    private int port;
}
