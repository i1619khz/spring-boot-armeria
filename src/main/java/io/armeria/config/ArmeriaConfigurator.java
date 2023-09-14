package io.armeria.config;

import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.docs.DocService;
import com.linecorp.armeria.server.logging.AccessLogWriter;
import com.linecorp.armeria.server.logging.LoggingService;
import com.linecorp.armeria.spring.ArmeriaServerConfigurator;
import io.armeria.framework.ArmeriaService;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * @author i1619kHz
 */
public class ArmeriaConfigurator implements ArmeriaServerConfigurator {
    private final ApplicationContext context;

    private static final String DOCS_PREFIX = "/docs";

    public ArmeriaConfigurator(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void configure(ServerBuilder builder) {
        builder.serviceUnder(DOCS_PREFIX, new DocService());
        builder.decorator(LoggingService.newDecorator());
        builder.accessLogWriter(AccessLogWriter.combined(), false);
        Map<String, Object> services = context.getBeansWithAnnotation(ArmeriaService.class);
        services.values().forEach(builder::annotatedService);
    }
}
