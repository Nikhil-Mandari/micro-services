package com.studenttribe.eurekaservice.apigatewayservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "eureka.client.enabled=false",
        "eureka.client.register-with-eureka=false",
        "eureka.client.fetch-registry=false"
})
class ApiGatewayServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
