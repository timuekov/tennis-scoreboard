package org.example;

import org.example.config.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HibernateUtilTest {

    @Test
    void createSessionFactorySuccessfully() {

        HibernateUtil hibernateUtil = new HibernateUtil();

        SessionFactory sessionFactory = hibernateUtil.createSessionFactory();

        assertNotNull(sessionFactory);

        sessionFactory.close();

    }


    @Test
    void loadLocalPropertiesReadsPassword() {

        HibernateUtil hibernateUtil = new HibernateUtil();

        Properties properties = hibernateUtil.loadLocalProperties();

        String res = properties.getProperty("hibernate.connection.password");

        assertNotNull(res);
    }

}
