package io.cts.msa.om.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
//@RibbonClient(name="item-service")
public class ApplicationConfig {

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {

		return new RestTemplate();
	}

}
