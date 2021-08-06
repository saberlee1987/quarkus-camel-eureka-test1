package com.saber.quarkus.client.camel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class HelloRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        rest("")
                .get("/hello")
                .id("hello-route")
                .enableCORS(true)
                .route()
                .routeId("hello-route")
                .setBody().simple("Hello Camel")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));
    }
}
