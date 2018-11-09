package ru.tandser.finesnew.repository;

import ru.tandser.finesnew.model.User;

public interface UserRepository {

    User getByLicense(String license);
}