package com.utibe.datasource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class UtyDataSource {
    protected static DataSource dataSource;
    protected static final String propertiesLocation = "src/main/resources/datasource.properties";
    //protected static Properties connectionPoolProperties = new Properties();
    private final static Logger logger = LoggerFactory.getLogger(UtyHikariDataSource.class);

    public UtyDataSource() {

    }

    public abstract DataSource getConnection();
    //public abstract void closeConnection();


    public static Properties getConnectionPoolProperties() {

        Properties connectionPoolProperties = new Properties();
        try{
            connectionPoolProperties.load( new FileInputStream(UtyDataSource.propertiesLocation) );
        }
        catch (IOException ioException){
            logger.error("Error : {}", ioException.getMessage());
        }
        return connectionPoolProperties;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        UtyDataSource.dataSource = dataSource;
    }

    public static void closeConnection(String connectionPoolType, DataSource dataSource){

        if(connectionPoolType.equalsIgnoreCase("hikari")){
            HikariDataSource hikariDataSource = (HikariDataSource)dataSource;
            hikariDataSource.close();
        }

    }
}
