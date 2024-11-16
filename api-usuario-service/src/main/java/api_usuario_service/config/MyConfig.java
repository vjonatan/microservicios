package api_usuario_service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

    @Bean
    @LoadBalanced //permite que realice un balanceo de carga para optimizar la app
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
