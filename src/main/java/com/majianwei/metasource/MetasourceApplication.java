package com.majianwei.metasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class MetasourceApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(MetasourceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try{
            Connection conn = dataSource.getConnection();
            logger.info("run获得连接：{}",conn);

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
