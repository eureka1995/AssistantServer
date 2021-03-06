package com.yuliia.khoptiak.assistant.server.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.Basic;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("com.yuliia.khoptiak.assistant.server.repository")
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@ComponentScan("com.yuliia.khoptiak.assistant.server")

public class DatabaseConfig {

   @Resource
    private Environment env;


   @Bean
   public LocalContainerEntityManagerFactoryBean  entityManagerFactory() {
       LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
       em.setDataSource(dataSource());
       em.setPackagesToScan(env.getRequiredProperty("db.entity.package"));
       em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
       em.setJpaProperties(getHibernateProperties());

       return em;
   }

    @Bean
   public DataSource dataSource() {

       BasicDataSource ds = new BasicDataSource();
       ds.setUrl(env.getRequiredProperty("db.url"));
       ds.setDriverClassName(env.getRequiredProperty("db.driver"));
       ds.setUsername(env.getRequiredProperty("db.username"));
       ds.setPassword(env.getRequiredProperty("db.password"));

       ds.setInitialSize(Integer.valueOf(env.getRequiredProperty("db.initialSize")));
       ds.setMaxIdle(Integer.valueOf(env.getRequiredProperty("db.maxIdle")));
       ds.setMinIdle(Integer.valueOf(env.getRequiredProperty("db.minIdle")));
       ds.setTimeBetweenEvictionRunsMillis(Long.valueOf(env.getRequiredProperty("db.timeBetweenEvictionRunsMillis")));
       ds.setMinEvictableIdleTimeMillis(Long.valueOf(env.getRequiredProperty("db.minEvictableIdleTimeMillis")));
       ds.setTestOnBorrow(Boolean.valueOf(env.getRequiredProperty("db.testOnBorrow")));
       ds.setValidationQuery(env.getRequiredProperty("db.validationQuery"));

       return  ds;
   }

   @Bean
   public PlatformTransactionManager transactionManager(){
       JpaTransactionManager manager = new JpaTransactionManager();
       manager.setEntityManagerFactory(entityManagerFactory().getObject());

       return  manager;
   }

   public Properties getHibernateProperties(){
       try {
           Properties propeties = new Properties();
           InputStream is = getClass().getClassLoader().getResourceAsStream("hibernate.properties");
           propeties.load(is);
           return  propeties;
       } catch ( IOException e) {
           throw new IllegalArgumentException("Can't find 'hibernate.properties' in classpath",e);
       }
        }
}
