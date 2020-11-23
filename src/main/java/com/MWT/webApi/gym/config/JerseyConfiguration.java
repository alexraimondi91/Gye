package com.MWT.webApi.gym.config;


import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.MWT.webApi.gym.resource.AlimentoResource;

@Configuration
@Component
@ApplicationPath("/gym")
public class JerseyConfiguration extends ResourceConfig{
	 public JerseyConfiguration() {
	        register(AlimentoResource.class);

	 }
}
