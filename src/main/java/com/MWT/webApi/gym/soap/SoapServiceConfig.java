package com.MWT.webApi.gym.soap;

import java.util.Properties;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;



@EnableWs
@Configuration
public class SoapServiceConfig extends WsConfigurerAdapter {
	
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/ccx/*");
	}
	
 
    @Bean(name = "dieta")
    public Wsdl11Definition dietaWsdl11Definition() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("/wsdl/dieta.wsdl"));
 
        return wsdl11Definition;
    }
    
    @Bean
	public SoapFaultMappingExceptionResolver exceptionResolver() {
		SoapFaultMappingExceptionResolver exceptionResolver = new DetailSoapFaultDefinitionExceptionResolver();

		SoapFaultDefinition faultDefinition = new SoapFaultDefinition();
		faultDefinition.setFaultCode(SoapFaultDefinition.SERVER);
		exceptionResolver.setDefaultFault(faultDefinition);

		Properties errorMappings = new Properties();
		errorMappings.setProperty(Exception.class.getName(), SoapFaultDefinition.SERVER.toString());
		errorMappings.setProperty(ServiceFaultException.class.getName(), SoapFaultDefinition.SERVER.toString());
		exceptionResolver.setExceptionMappings(errorMappings);
		exceptionResolver.setOrder(1);
		return exceptionResolver;
	}



}
