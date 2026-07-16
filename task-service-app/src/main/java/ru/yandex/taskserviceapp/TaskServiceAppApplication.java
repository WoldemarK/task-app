package ru.yandex.taskserviceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TaskServiceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskServiceAppApplication.class, args);
    }

}
