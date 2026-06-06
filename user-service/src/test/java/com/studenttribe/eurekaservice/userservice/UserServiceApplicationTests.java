package com.studenttribe.eurekaservice.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "eureka.client.enabled=false",
        "eureka.client.register-with-eureka=false",
        "eureka.client.fetch-registry=false",
        "jwt.secret=test-secret-key",
        "jwt.validity.accessToken=3600000",
        "jwt.validity.refreshToken=86400000"
})
class UserServiceApplicationTests {

    @Test
    void contextLoads() {
    }
}
