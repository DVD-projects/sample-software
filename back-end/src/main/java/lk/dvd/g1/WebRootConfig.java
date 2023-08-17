package lk.dvd.g1;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;

@Configuration
public class WebRootConfig {

    @Bean
    public BasicDataSource getConnection() {
        BasicDataSource pool = new BasicDataSource();
        pool.setUsername("root");
        pool.setPassword("mysql");
        pool.setUrl("jdbc:mysql://localhost:3306/dvd_bank?createDatabaseIfNotExist=true");
        pool.setDriverClassName("com.mysql.cj.jdbc.Driver");
        pool.setInitialSize(10);
        pool.setMaxTotal(20);
        return pool;
    }
}
