package org.imagehub.springcloud.msvc.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {

  private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

  public LoggingFilter() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      logger.info("Request path: {}", exchange.getRequest().getPath());
      return chain.filter(exchange).then(Mono.fromRunnable(() -> {
        logger.info("Response status: {}", exchange.getResponse().getStatusCode());
      }));
    };
  }

  public static class Config {
    // Put the configuration properties here
  }
}
