package com.webpilot.services;

import com.webpilot.domain.Registration;
import com.webpilot.repositories.RegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: Access H2 db via SpringData RegistrationRepository, no DAOs needed
 * Date: 4/11/17
 *
 * @author RGH
 */
@Service
public class RegistrationService {
    private static final Logger log = LoggerFactory.getLogger(RegistrationService.class);

    @Autowired
    private RegistrationRepository registrationRepository;

    public Registration getRegistration(String userName) {
        return registrationRepository.findByUserName(userName);
    }

    public List<Registration> getRegistrations() {
        List<Registration> registrationsList = new ArrayList<>();
        Iterable<Registration> registrations = registrationRepository.findAll();
        registrations.forEach(registration -> registrationsList.add(registration));
        return registrationsList;
    }

    public void deleteRegistration(String userName) {
        Registration registration = registrationRepository.findByUserName(userName);
        if (registration == null) {
            log.error("Registration not found for userName " + userName);
            return;
        }
        registrationRepository.delete(registration.getId());
    }

    public void addRegistration(Registration registration) {
        registrationRepository.save(registration);
    }
}
