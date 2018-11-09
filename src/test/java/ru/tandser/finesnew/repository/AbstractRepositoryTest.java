package ru.tandser.finesnew.repository;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.tandser.finesnew.RepositoryConfig;

@ActiveProfiles(RepositoryConfig.PROFILE_TEST)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RepositoryConfig.class)
public abstract class AbstractRepositoryTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
}