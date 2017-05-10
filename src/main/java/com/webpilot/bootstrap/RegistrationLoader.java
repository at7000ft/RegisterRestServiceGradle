package com.webpilot.bootstrap;

import com.webpilot.domain.Registration;
import com.webpilot.repositories.RegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * Description: load default registrations on startup, for testing only
 * Date: 4/11/17
 *
 * @author RGH
 */
@Component
public class RegistrationLoader implements ApplicationListener<ContextRefreshedEvent>  {
    private static final Logger log = LoggerFactory.getLogger(RegistrationLoader.class);

    @Autowired
    private RegistrationRepository registrationRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("onApplicationEvent: called");
        Registration reg1 = new Registration("joe1", "joe1@gmail.com",new Date());
        registrationRepository.save(reg1);

        Registration reg2 = new Registration("joe2", "joe2@gmail.com",new Date());
        registrationRepository.save(reg2);
    }
}
