package com.utibe.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Properties;


public class UtyC3P0DataSource extends UtyDataSource{

    private final static Logger logger = LoggerFactory.getLogger(UtyC3P0DataSource.class);

    public UtyC3P0DataSource(){

    }

    public static void configureC3P0DataSource( ) {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        Properties connectionPoolProperties = getConnectionPoolProperties();
        String driverClassName = connectionPoolProperties.getProperty("dbcp.DriverClassName");
        String jdbcUrl = connectionPoolProperties.getProperty("dbcp.Url");
        String user = connectionPoolProperties.getProperty("user");
        String password = connectionPoolProperties.getProperty("password");
        //String driverClassName = "org.postgresql.Driver";
        //String jdbcUrl = "jdbc:postgresql://aws-postgres-athletes.cox16scn1rsd.eu-west-2.rds.amazonaws.com:5432/postgres";
        //String user = "postgres";
        //String password = "postgresNEW";

        try{
            comboPooledDataSource.setDriverClass( driverClassName );
            logger.info("1");
        }
        catch(PropertyVetoException propertyVetoException){
            logger.info("4");
            logger.info("Error loading driver {}", propertyVetoException.getMessage());
        }
        comboPooledDataSource.setJdbcUrl( jdbcUrl );
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);
        logger.info("3");

        UtyC3P0DataSource.dataSource = comboPooledDataSource;
        logger.info("5");
    }

    public static DataSource createC3P0DataSource(){
        configureC3P0DataSource( );
        logger.info("2");
        return UtyC3P0DataSource.dataSource;
    }

    public DataSource getConnection(){
        return UtyC3P0DataSource.dataSource;
    }

    public void closeConnection() throws SQLException {
        ComboPooledDataSource comboPooledDataSource= (ComboPooledDataSource)dataSource;
        comboPooledDataSource.close();
    }

}
