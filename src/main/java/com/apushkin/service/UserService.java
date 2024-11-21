package com.apushkin.service;

import com.apushkin.model.User;

import static com.apushkin.context.Application.faker;

public class UserService {
    public User findById(String id) {
        String randomName = faker.funnyName().name();
        // always "finds" the user, every user has a random name
        return new User(id, randomName);
    }
}
