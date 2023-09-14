package io.armeria.config;

import com.linecorp.armeria.spring.ArmeriaServerConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author i1619kHz
 */
@Configuration
public class ArmeriaConfig {
    private final ApplicationContext context;

    @Autowired
    public ArmeriaConfig(ApplicationContext context) {
        this.context = context;
    }

    @Bean
    public ArmeriaServerConfigurator configurator() {
        return new ArmeriaConfigurator(context);
    }
}
