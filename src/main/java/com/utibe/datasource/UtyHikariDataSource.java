package com.utibe.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.Properties;

public class UtyHikariDataSource extends UtyDataSource{

    private final static Logger logger = LoggerFactory.getLogger(UtyHikariDataSource.class);

    public UtyHikariDataSource(){
        HikariConfig hikariConfig = new HikariConfig(propertiesLocation);
        UtyHikariDataSource.dataSource = new HikariDataSource(hikariConfig);
    }

    public static DataSource createHikariDataSource(){
        Properties connectionPoolProperties = getConnectionPoolProperties();
        logger.info("Properties are {}", connectionPoolProperties.toString());
        HikariConfig hikariConfig = new HikariConfig(propertiesLocation);
        dataSource = new HikariDataSource(hikariConfig);
        logger.info("Created and will return Hikari data source");
        return UtyHikariDataSource.dataSource;
    }

    public DataSource getConnection(){
        return createHikariDataSource();
    }

    public void closeConnection(){
        HikariDataSource hikariDataSource = (HikariDataSource)dataSource;
        hikariDataSource.close();
    }

}
