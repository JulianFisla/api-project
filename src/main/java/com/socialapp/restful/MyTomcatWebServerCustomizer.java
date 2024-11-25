package com.socialapp.restful;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 *
 * @author JulianFisla
 */
@Component
public class MyTomcatWebServerCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addConnectorCustomizers(connector -> {
            // Access and modify the connector's protocol handler settings
            var protocolHandler = (org.apache.coyote.http11.Http11NioProtocol) connector.getProtocolHandler();

            // Set the keep-alive timeout in milliseconds
            protocolHandler.setKeepAliveTimeout(20000); // 20 seconds

            // Set the maximum number of requests that can be handled on a single keep-alive connection
            protocolHandler.setMaxKeepAliveRequests(100);
        });
    }
}
