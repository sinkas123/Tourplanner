package com.example.jpademo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = JpaDemoApplication.class)
@Transactional
class JpaDemoApplicationTests {

    @Test
    void contextLoads() {
        Assertions.assertTrue(true);
    }


}
