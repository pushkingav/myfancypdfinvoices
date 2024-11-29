package com.apushkin.service;

import com.apushkin.model.User;
import com.github.javafaker.Faker;

public class UserService {
    final private Faker faker;

    public UserService(Faker faker) {
        this.faker = faker;
    }

    public User findById(String id) {
        String randomName = faker.funnyName().name();
        // always "finds" the user, every user has a random name
        return new User(id, randomName);
    }
}
