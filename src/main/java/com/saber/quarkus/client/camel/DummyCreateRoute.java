package com.saber.quarkus.client.camel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
public class DummyCreateRoute extends RouteBuilder {

    @ConfigProperty(name = "quarkus.camel.service.dummy.create")
    private String createDummyAddress;

    @Override
    public void configure() throws Exception {

        rest("/dummy")
                .post("/create")
                .id("dummy-create")
                .description("Dummy Create Service")
                .consumes(MediaType.APPLICATION_JSON)
                .produces(MediaType.APPLICATION_JSON)
                .enableCORS(true)
                .type(PersonDto.class)
                .bindingMode(RestBindingMode.json)
                .route()
                .routeId("dummy-create-route")
                .log("Request for dummy create with body ===> ${in.body}")
                .setHeader(Exchange.HTTP_METHOD,constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE,constant("application/json"))
                .marshal().json(JsonLibrary.Jackson,PersonDto.class)
                .process(exchange -> {
                    exchange.getMessage().setHeader(Exchange.HTTP_PATH,"");
                })
                .to(createDummyAddress+"?bridgeEndpoint=true")
                .convertBodyTo(String.class)
                .log("Response body create dummy ===> ${in.body}")
                .unmarshal().json(JsonLibrary.Jackson)
                .setHeader(Exchange.HTTP_RESPONSE_CODE,constant(200));
    }
}
