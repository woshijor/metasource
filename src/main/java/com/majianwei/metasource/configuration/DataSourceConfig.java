package com.majianwei.metasource.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

        /**
         * @description:Singapore数据源配置对象
         * @author: majianwei
         * @time: 2020-05-30 17:20
         */
        @Primary
        @Bean(name ="SingaporeDataSourceProperties")
        @ConfigurationProperties(prefix = "spring.datasource.singapo")
        public DataSourceProperties singerpodatasoruceProperties(){
            return new DataSourceProperties();
        }


        /**
         * @description:创建Singgapore数据源
         * @author: majianwei
         * @time: 2020-05-30 17:28
         */

        @Bean(name="singaporeSource")
        @ConfigurationProperties(prefix = "spring.datasource.singapo.hikari")
        public DataSource singaporeDataSource(){
            DataSourceProperties dataSourceProperties = this.singerpodatasoruceProperties();
            return createHikarDataSource(dataSourceProperties);
        }


       /**
        * @description:taiguo 数据源配置对象
        * @author: majianwei
        * @time: 2020-05-30 17:38
        */

       @Bean(name="taiguoDataSourceProperties")
       @ConfigurationProperties(prefix = "spring.datasource.taiguo")
       public DataSourceProperties taiguoDataSourceProperties(){
           return new DataSourceProperties();
       }


       @Bean(name = "taiguoDataSource")
       @ConfigurationProperties(prefix = "spring.datasource.taiguo.hikari")
       public DataSource taiguoDataSource(){
           DataSourceProperties dataSourceProperties = this.taiguoDataSourceProperties();
           return createHikarDataSource(dataSourceProperties);

       }




        private static HikariDataSource createHikarDataSource(DataSourceProperties dataSourceProperties){
            //创建HikariDataSource对象
            HikariDataSource dataSource = dataSourceProperties.initializeDataSourceBuilder()
                    .type(HikariDataSource.class).build();
            //设置线程池名
            if(StringUtils.hasText(dataSourceProperties.getName())){
                dataSource.setPoolName(dataSourceProperties.getName());
            }

            return dataSource;
        }



}
