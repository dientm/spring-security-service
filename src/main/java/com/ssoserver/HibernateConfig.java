package com.ssoserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

//@Configuration
public class HibernateConfig {
    /*@Autowired
    private Environment env;
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(env.getProperty("jdbc:mysql://localhost:3306/test1"));
        dataSource.setUsername(env.getProperty("root"));
        dataSource.setPassword(env.getProperty(""));
        return dataSource;
    }*/
}
