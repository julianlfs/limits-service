package br.com.julianlfs.microservices.limitsservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration config;

    @GetMapping("/limits")
    @HystrixCommand(fallbackMethod = "fallbackConfiguration")
    public LimitsConfiguration retrieveLimitsFromConfigurations(){
        return new LimitsConfiguration(config.getMaximum(), config.getMinimum());
    }

    public LimitsConfiguration fallbackConfiguration() {
        return new LimitsConfiguration(999, 9);
    }


}
