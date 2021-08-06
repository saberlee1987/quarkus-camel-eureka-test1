package com.saber.quarkus.client.config;

import com.orbitz.consul.Consul;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class AppConfig {

    @Produces
    Consul consul  = Consul.builder().build();
}
