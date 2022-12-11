package com.utibe.datasource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.utibe.datasource.connectionpools.UtyHikariDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.PoolingDataSource;
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

    public static void closeConnection(String connectionPoolType, DataSource dataSource) throws SQLException {

        if(connectionPoolType.equalsIgnoreCase("hikari")){
            logger.info("Closing hikari connection pool");
            HikariDataSource hikariDataSource = (HikariDataSource)dataSource;
            hikariDataSource.close();
        }
        else if(connectionPoolType.equalsIgnoreCase("dbcp-basic")){
            logger.info("Closing dbcp-basic connection pool");
            BasicDataSource basicDataSource  = (BasicDataSource)dataSource;
            basicDataSource.close();
        }
        else if(connectionPoolType.equalsIgnoreCase("dbcp-polling-data")){
            logger.info("Closing dbcp-polling-data connection pool");
            PoolingDataSource poolingDataSource = (PoolingDataSource) dataSource;
            poolingDataSource.close();
        }
        else if(connectionPoolType.equalsIgnoreCase("c3p0")){
            logger.info("Closing C3p0 connection pool");
            ComboPooledDataSource comboPooledDataSource = (ComboPooledDataSource)dataSource;
            comboPooledDataSource.close();
        }

    }
}
