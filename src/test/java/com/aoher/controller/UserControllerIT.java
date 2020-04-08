package com.aoher.controller;

import com.aoher.App;
import com.aoher.model.User;
import com.aoher.repository.UserRepository;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static com.aoher.controller.ParamTestFixtures.assertionResponseFailure;
import static com.aoher.controller.ParamTestFixtures.assertionResponseUser;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(
        classes = App.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class UserControllerIT {

    private static final Logger log = LoggerFactory.getLogger(UserControllerIT.class);

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Rule
    public TestName name = new TestName();

    private static final String ENDPOINT = "/users";
    private static final User newUser = new User();
    private Long id;

    @Before
    public void setUp() {
        id = 1L;
        newUser.setId(id);
        newUser.setFirstName("John"); //John
        newUser.setLastName("sina"); //sina
        userRepository.save(newUser);
    }

    /**************************** success use cases **************************************/

    @Test
    public void test0_add_user_success() {
        ResponseEntity<User> user = restTemplate.postForEntity(
                ENDPOINT,
                new HttpEntity<User>(newUser),
                User.class);
        assertionResponseUser(user, newUser, name.getMethodName(), HttpStatus.CREATED);
    }

    @Test
    public void test2_find_user_success() {
        ResponseEntity<User> user = restTemplate.getForEntity(
                ENDPOINT + "/" + id,
                User.class);

        assertionResponseUser(user, newUser, name.getMethodName(), HttpStatus.OK);
    }

    @Test
    public void test3_findall_users_success() {
        ResponseEntity<User[]> response = restTemplate.getForEntity(
                ENDPOINT,
                User[].class);

        log.info("{} => status response {} , expectedStatus :{}",
                name.getMethodName(), response.getStatusCode(), HttpStatus.OK);

        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));
        assertNotNull(response.getBody());
        assertThat(response.getBody().length, is(equalTo((int) userRepository.count())));
    }

    @Test
    public void test4_update_user_success() {
        newUser.setFirstName("New name");
        ResponseEntity<User> user = restTemplate.exchange(
                ENDPOINT + "/" + id,
                HttpMethod.PUT,
                new HttpEntity<>(newUser),
                User.class);

        assertionResponseUser(user, newUser, name.getMethodName(), HttpStatus.OK);
    }

    @Test
    public void test5_delete_user_success() {
        ResponseEntity<String> response = restTemplate.exchange(
                ENDPOINT + "/" + id,
                HttpMethod.DELETE,
                null,
                String.class);

        log.info("{} => status response {} , expectedStatus :{}",
                name.getMethodName(), response.getStatusCode(), HttpStatus.OK);

        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));

    }

    /**************************** failure use cases **************************************/

    @Test
    public void test6_add_user_failure() {
        ResponseEntity<User> response = restTemplate.exchange(
                ENDPOINT,
                HttpMethod.POST,
                null,
                User.class);
        assertionResponseFailure(response, name.getMethodName(), HttpStatus.CREATED);
    }

    @Test
    public void test7_find_user_failure() {
        ResponseEntity<User> response = restTemplate.getForEntity(
                ENDPOINT + "/" + id,
                User.class);

        assertionResponseFailure(response, name.getMethodName(), HttpStatus.OK);
    }

    @Test
    public void test8_update_user_failure() {
        newUser.setFirstName("new Name");
        ResponseEntity<User> response = restTemplate.exchange(
                ENDPOINT + "/" + id,
                HttpMethod.PUT,
                new HttpEntity<>(newUser),
                User.class);

        assertionResponseFailure(response, name.getMethodName(), HttpStatus.OK);
    }

    @Test
    public void test9_delete_user_failure() {
        ResponseEntity<String> response = restTemplate.exchange(ENDPOINT + "/" + id,
                HttpMethod.DELETE,
                null,
                String.class);

        assertionResponseFailure(response, name.getMethodName(), HttpStatus.OK);
    }
}