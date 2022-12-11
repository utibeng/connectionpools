package com.utibe.datasource.connectionpools;


import com.utibe.datasource.UtyDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class UtyDbcpBasicDataSource extends UtyDataSource {

    private final static Logger logger = LoggerFactory.getLogger(UtyHikariDataSource.class);

    public UtyDbcpBasicDataSource(){

    }

    public static void configureBasicDataSource(BasicDataSource basicDataSource) {
        Properties connectionPoolProperties = getConnectionPoolProperties();
        basicDataSource.setDriverClassName(connectionPoolProperties.getProperty("dbcp.DriverClassName"));
        basicDataSource.setUrl(connectionPoolProperties.getProperty("dbcp.Url"));
        basicDataSource.setUsername(connectionPoolProperties.getProperty("dataSource.user"));
        basicDataSource.setPassword(connectionPoolProperties.getProperty("dataSource.password"));
        basicDataSource.setMinIdle(Integer.parseInt(connectionPoolProperties.getProperty("minimumIdle")));
        basicDataSource.setMaxIdle(Integer.parseInt(connectionPoolProperties.getProperty("maximumPoolSize")));

    }

    public static DataSource createDbcpDataSource(){
        BasicDataSource basicDataSource = new BasicDataSource();
        UtyDbcpBasicDataSource.configureBasicDataSource(basicDataSource);
        return basicDataSource;
    }

    public DataSource getConnection(){
        return createDbcpDataSource();
    }

    public void closeConnection() throws SQLException {
        BasicDataSource basicDataSource = (BasicDataSource)dataSource;
        basicDataSource.close();
    }

}
