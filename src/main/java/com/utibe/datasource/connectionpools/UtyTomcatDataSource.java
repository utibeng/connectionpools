package com.utibe.datasource.connectionpools;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.utibe.datasource.UtyDataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Properties;


public class UtyTomcatDataSource extends UtyDataSource {

    private final static Logger logger = LoggerFactory.getLogger(UtyTomcatDataSource.class);

    public UtyTomcatDataSource(){

    }

    public static void configureTomcatDataSource( ) {

        Properties connectionPoolProperties = getConnectionPoolProperties();
        String driverClassName = connectionPoolProperties.getProperty("dbcp.DriverClassName");
        String jdbcUrl = connectionPoolProperties.getProperty("dbcp.Url");
        String user = connectionPoolProperties.getProperty("user");
        String password = connectionPoolProperties.getProperty("password");

        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setUrl(jdbcUrl);
        poolProperties.setDriverClassName(driverClassName);
        poolProperties.setUsername(user);
        poolProperties.setPassword(password);

        UtyTomcatDataSource.dataSource = new org.apache.tomcat.jdbc.pool.DataSource(poolProperties);
    }

    public static DataSource createTomcatDataSource(){
        configureTomcatDataSource( );
        return UtyTomcatDataSource.dataSource;
    }

    public DataSource getConnection(){
        return UtyTomcatDataSource.dataSource;
    }

    /*public void closeConnection() throws SQLException {
        ComboPooledDataSource comboPooledDataSource= (ComboPooledDataSource)dataSource;
        comboPooledDataSource.close();
    }*/

}
