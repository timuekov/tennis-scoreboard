package org.example.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.nio.file.Path;

import org.example.entity.MatchEntity;
import org.example.entity.PlayerEntity;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    private SessionFactory sessionFactory;

    public Properties loadLocalProperties() {

        Path configPath = Path.of(System.getProperty("user.home"), "hibernate-local.properties");

        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream(configPath.toFile())) {
            properties.load(fis);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load Hibernate local properties", e);

        }
        return properties;
    }

    public SessionFactory createSessionFactory() {

        Configuration configuration = new Configuration();

        configuration.configure();

        Properties properties = loadLocalProperties();

        configuration.addProperties(properties);

        configuration.addAnnotatedClass(PlayerEntity.class);

        configuration.addAnnotatedClass(MatchEntity.class);

        sessionFactory = configuration.buildSessionFactory();

        return sessionFactory;

    }

}
