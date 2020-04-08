package com.aoher.controller;

import com.aoher.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class ParamTestFixtures {

    private static Logger log = LoggerFactory.getLogger("ParamTestFixtures");

    public static void assertionResponseUser(ResponseEntity<User> response, User userMock,
                                             String testName, HttpStatus expectedStatus) {
        String sb = "user(id=" +
                (userMock.getId() != null ? String.valueOf(userMock.getId()) : "") +
                ") " +
                testName +
                "=> status response :" +
                response.getStatusCode() +
                ", expectedStatus :" + expectedStatus;
        log.info(sb);

        assertThat(response.getStatusCode(), is(equalTo(expectedStatus)));
        assertNotNull(response.getBody());
        assertThat(response.getBody().getFirstName(), is(equalTo(userMock.getFirstName())));
        assertThat(response.getBody().getLastName(), is(equalTo(userMock.getLastName())));
    }

    public static void assertionResponseFailure(ResponseEntity response, String testName, HttpStatus expectedStatus) {
        String info= testName +
                "=> status response :" +
                response.getStatusCode() +
                ", expectedStatus :" +
                expectedStatus;
        log.info(info);

        assertThat(response.getStatusCode(),
                not(equalTo(expectedStatus)));
    }

    private ParamTestFixtures() {
    }
}
