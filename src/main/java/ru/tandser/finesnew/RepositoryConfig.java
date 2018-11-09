package ru.tandser.finesnew;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static java.util.Objects.requireNonNull;

@Configuration
@Import({RepositoryConfig.DataSourceConfigProd.class, RepositoryConfig.DataSourceConfigTest.class})
@ComponentScan(RepositoryConfig.PACKAGE_REPOSITORIES)
@EnableTransactionManagement
@EnableJpaRepositories(RepositoryConfig.PACKAGE_REPOSITORIES)
public class RepositoryConfig {

    static final String PACKAGE_ENTITIES     = "ru.tandser.finesnew.model";
    static final String PACKAGE_REPOSITORIES = "ru.tandser.finesnew.repository";

    public static final String PROFILE_PROD = "prod";
    public static final String PROFILE_TEST = "test";

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Properties hibernateProperties;

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    @DependsOn("flyway")
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setPackagesToScan(PACKAGE_ENTITIES);
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setJpaProperties(hibernateProperties);
        factoryBean.afterPropertiesSet();

        return factoryBean.getNativeEntityManagerFactory();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }

    private static Properties fillProperties(Environment env) {
        Properties properties = new Properties();

        properties.put(AvailableSettings.DIALECT,          requireNonNull(env.getProperty("hibernate.dialect")));
        properties.put(AvailableSettings.SHOW_SQL,         requireNonNull(env.getProperty("hibernate.show_sql")));
        properties.put(AvailableSettings.FORMAT_SQL,       requireNonNull(env.getProperty("hibernate.show_sql")));
        properties.put(AvailableSettings.USE_SQL_COMMENTS, requireNonNull(env.getProperty("hibernate.use_sql_comments")));

        return properties;
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        FluentConfiguration configuration = Flyway.configure();

        configuration.baselineOnMigrate(true);
        configuration.locations("classpath:sql");
        configuration.dataSource(dataSource);

        return configuration.load();
    }

    @Configuration
    @Profile(RepositoryConfig.PROFILE_PROD)
    @PropertySource("classpath:properties/db_pg.properties")
    static class DataSourceConfigProd {

        @Autowired
        private Environment env;

        @Bean
        public DataSource dataSource() {
            org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();

            dataSource.setDriverClassName(env.getProperty("local.driverClassName"));
            dataSource.setUrl(env.getProperty("local.url"));
            dataSource.setUsername(env.getProperty("local.username"));
            dataSource.setPassword(env.getProperty("local.password"));

            return dataSource;
        }

        @Bean
        public Properties hibernateProperties() {
            return RepositoryConfig.fillProperties(env);
        }
    }

    @Configuration
    @Profile(RepositoryConfig.PROFILE_TEST)
    @PropertySource("classpath:properties/db_h2.properties")
    static class DataSourceConfigTest {

        @Autowired
        private Environment env;

        @Bean
        public DataSource dataSource() {
            return new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .addScripts()
                    .build();
        }

        @Bean
        public Properties hibernateProperties() {
            return RepositoryConfig.fillProperties(env);
        }
    }
}