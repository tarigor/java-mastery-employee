package com.tarigor.javamastery;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@SpringBootTest
class JavaMasteryApplicationTests {

    @Test
    void contextLoads() {
        Date date = Date.valueOf("1980-05-21");


        long daysUntilBirthday = ChronoUnit.YEARS.between(date.toLocalDate(), LocalDate.now());

        System.out.println("result -> " + daysUntilBirthday);

    }

}
