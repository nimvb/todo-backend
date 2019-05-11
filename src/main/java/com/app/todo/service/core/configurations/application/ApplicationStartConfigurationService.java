package com.app.todo.service.core.configurations.application;

import com.app.todo.service.core.configurations.properties.DatabaseProperties;
import com.app.todo.service.core.configurations.seeders.DatabaseSeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ApplicationStartConfigurationService {


    @Autowired
    DatabaseSeederService seederService;

    @EventListener
    public void init(ContextRefreshedEvent cntx) {

        seederService.seed();
    }


}
