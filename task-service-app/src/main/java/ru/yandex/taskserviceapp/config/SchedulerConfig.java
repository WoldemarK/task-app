package ru.yandex.taskserviceapp.config;


import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSchedulerLock(defaultLockAtMostFor = "PT5M")
public class SchedulerConfig {
}
