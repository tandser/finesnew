package ru.tandser.finesnew.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.tandser.finesnew.model.User;
import ru.tandser.finesnew.repository.UserRepository;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Repository
class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Override
    public Optional<User> getByLicense(String license) {
        return jpaUserRepository.findOneByLicense(requireNonNull(license));
    }
}