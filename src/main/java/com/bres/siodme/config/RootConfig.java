package com.bres.siodme.config;

/**
 * Created by Adam on 2016-07-24.
 */
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.bres.siodme.config.*;

@Configuration
@ComponentScan(basePackages = "com.bres.siodme")
//@PropertySource("resources/application.properties")
public class RootConfig {

}