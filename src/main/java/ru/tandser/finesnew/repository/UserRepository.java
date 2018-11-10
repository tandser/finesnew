package ru.tandser.finesnew.repository;

import ru.tandser.finesnew.model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> getByLicense(String license);
}