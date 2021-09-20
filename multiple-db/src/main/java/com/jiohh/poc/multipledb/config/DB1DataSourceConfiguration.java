package com.jiohh.poc.multipledb.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.jiohh.poc.multipledb.repository.db1",
        entityManagerFactoryRef = "db1EntityManagerFactory",
        transactionManagerRef= "db1TransactionManager"
)
public class DB1DataSourceConfiguration {
	
	@Bean
	@Primary
	@ConfigurationProperties("app.datasource.db1")
	public DataSourceProperties db1DataSourceProperties() {
	    return new DataSourceProperties();
	}

	@Bean
	@Primary
    @ConfigurationProperties("app.datasource.db1.configuration")
    public DataSource db1DataSource() {
        return db1DataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }
	
	@Primary
    @Bean(name = "db1EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(db1DataSource())
                .packages("com.jiohh.poc.multipledb.model.db1")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager db1TransactionManager(
            final @Qualifier("db1EntityManagerFactory") LocalContainerEntityManagerFactoryBean memberEntityManagerFactory) {
        return new JpaTransactionManager(memberEntityManagerFactory.getObject());
    }

}
