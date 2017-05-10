package com.webpilot.repositories;

import com.webpilot.domain.Registration;
import org.springframework.data.repository.CrudRepository;

/**
 * Description: SpringData interface for Registration
 * Date: 4/11/17
 *
 * @author RGH
 */
public interface RegistrationRepository extends CrudRepository<Registration, Integer> {
    Registration findByUserName(String userName);
}
