package ru.tandser.finesnew.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.tandser.finesnew.model.User;

import java.util.Optional;

@Transactional(readOnly = true)
interface JpaUserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByLicense(String license);
}