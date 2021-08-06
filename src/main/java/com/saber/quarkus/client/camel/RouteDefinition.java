package com.saber.quarkus.client.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RouteDefinition extends RouteBuilder {

    @ConfigProperty(name = "quarkus.camel.api.base-path")
    private String contextPath;

    @ConfigProperty(name = "quarkus.camel.api.openapi-path")
    private String openApiPath;

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .enableCORS(true)
                .contextPath(contextPath)
                .apiContextPath(openApiPath)
                .apiProperty("cors","true")
                .apiProperty("api.title","camel quarkus sample")
                .apiProperty("api.version","version 1")
                .dataFormatProperty("prettyPrint", "true")
                .bindingMode(RestBindingMode.json);


    }
}
