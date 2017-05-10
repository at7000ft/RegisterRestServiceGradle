package com.webpilot;

import com.webpilot.domain.Registration;
import com.webpilot.repositories.RegistrationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Description: RegistrationController integration test
 * Date: 4/11/17
 *
 * @author RGH
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
@WebAppConfiguration
public class RegistrationControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    private HttpMessageConverter mappingJackson2HttpMessageConverter;
    private String userName = "joe1";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        registrationRepository.deleteAll();
        Registration reg1 = new Registration(userName, userName + "@gmail.com",new Date());
        registrationRepository.save(reg1);

        Registration reg2 = new Registration("joe2", "joe2@gmail.com",new Date());
        registrationRepository.save(reg2);
    }

    @Test
    public void userNameNotFound() throws Exception {
        mockMvc.perform(get("/registrations/george")
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void readSingleUser() throws Exception {
        mockMvc.perform(get("/registrations/" + userName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }

    @Test
    public void readAllUsers() throws Exception {
        mockMvc.perform(get("/registrations"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void addUser() throws Exception {
        String uname = "ace";
        String registrationJson = json(new Registration(uname,uname + "@gmail.com", new Date()));
        mockMvc.perform(post("/registrations/add").contentType(contentType).content(registrationJson))
                .andExpect(status().isCreated());
        Registration registration = registrationRepository.findByUserName(uname);
        assertNotNull(registration);
    }

    @Test
    public void deleteUser() throws Exception {
        mockMvc.perform(delete("/registrations/" + userName))
                .andExpect(status().isOk());
        Registration registration = registrationRepository.findByUserName(userName);
        assertNull(registration);
    }



    private String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
