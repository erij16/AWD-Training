package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        //si on a une seule instance et on fait une conf statique
        /*return builder.routes().route("candidat",r->r.path("/mic1/**")
                .uri("http://localhost:8081")).build();*/


        ///si on a deux instances et on fait une conf statique
        /*return builder.routes().route("candidat",r->r.path("/mic1/**")
                .uri("http://localhost:8081"))
                .route("candidat",r->r.path("/mic1/**")
                        .uri("http://localhost:8090")).build();*/


        //ici on va passer par l'eureka , il detecte dejà les instances dispo
        //du microser candidat => ena hatit lb c à dire load balancer
        //CANDIDAT : c le nom du microser dans l'interface EUREKA
        //eureka bch trajaali les instances dispo du projet candidat w
        //baad l api gateway bch yaamel la redirection via l'une des instances
        //a l'aide du load balancer
        return builder.routes()
                .route("candidat",r->r.path("/mic1/**")
                        .uri("lb://CANDIDAT"))
                        .build();

    }


}
