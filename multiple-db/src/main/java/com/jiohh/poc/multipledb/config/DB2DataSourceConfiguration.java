package com.jiohh.poc.multipledb.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "com.jiohh.poc.multipledb.repository.db2",
        entityManagerFactoryRef = "db2EntityManagerFactory",
        transactionManagerRef= "db2TransactionManager"
)
public class DB2DataSourceConfiguration {

	@Bean
	@ConfigurationProperties("app.datasource.db2")
	public DataSourceProperties db2DataSourceProperties() {
	    return new DataSourceProperties();
	}

	@Bean
    @ConfigurationProperties("app.datasource.db2.configuration")
    public DataSource db2DataSource() {
        return db2DataSourceProperties().initializeDataSourceBuilder()
                .type(BasicDataSource.class).build();
    }
	
    @Bean(name = "db2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(db2DataSource())
                .packages("com.jiohh.poc.multipledb.model.db2")
                .build();
    }

    @Bean
    public PlatformTransactionManager db2TransactionManager(
            final @Qualifier("db2EntityManagerFactory") LocalContainerEntityManagerFactoryBean memberEntityManagerFactory) {
        return new JpaTransactionManager(memberEntityManagerFactory.getObject());
    }
}
