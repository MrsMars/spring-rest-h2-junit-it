package com.aoher.init;

import com.aoher.model.User;
import com.aoher.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Component
public class DataInit implements ApplicationRunner {

    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    private UserRepository userRepository;

    @Autowired
    public DataInit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = userRepository.count();

        if (count == 0) {
            User user1 = new User();
            user1.setFirstName("John");
            user1.setLastName("sina");
            user1.setDateOfBirth(df.parse("1980-12-20"));

            User user2 = new User();
            user2.setFirstName("Smith");
            user1.setLastName("will");
            user2.setDateOfBirth(df.parse("1985-11-11"));

            userRepository.save(user1);
            userRepository.save(user2);
        }
    }
}
