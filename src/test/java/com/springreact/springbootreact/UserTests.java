package com.springreact.springbootreact;

import com.springreact.springbootreact.model.User;
import com.springreact.springbootreact.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser(){
        User user = new User(1,"John","Doe","john.doe@email.com","johhny","strong-password");
        userRepository.save(user);
        userRepository.findById(new Integer(1))
                .map(newUser ->{
                    Assert.assertEquals("John",newUser.getName());
                    return true;
                });
    }

    @Test
    public void getUser(){
        User user = new User(1,"John","Doe","john.doe@email.com","johhny","strong-password");
        User user2 = new User(2,"Daniel","Marcus","daniel@daniel.com","danie","super_strong_password");
        userRepository.save(user);

        userRepository.save(user2);

        userRepository.findById(new Integer(1))
                .map(newUser ->{
                    Assert.assertEquals("danie",newUser.getUsername());
                    return true;
                });

    }

    @Test
    public void getUsers(){
        User user = new User(1,"John","Doe","john.doe@email.com","johhny","strong-password");
        User user2 = new User(2,"Daniel","Marcus","daniel@daniel.com","danie","super_strong_password");
        userRepository.save(user);
        userRepository.save(user2);

        Assert.assertNotNull(userRepository.findAll());
    }

    @Test
    public void deleteUser(){
        User user = new User(1,"John","Doe","john.doe@email.com","johhny","strong-password");
        userRepository.save(user);
        userRepository.delete(user);
        Assert.assertTrue(userRepository.findAll().isEmpty());
    }


}