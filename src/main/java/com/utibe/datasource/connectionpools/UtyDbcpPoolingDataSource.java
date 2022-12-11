package com.utibe.datasource.connectionpools;

import com.utibe.datasource.UtyDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;


public class UtyDbcpPoolingDataSource extends UtyDataSource {

    private final static Logger logger = LoggerFactory.getLogger(UtyHikariDataSource.class);

    public UtyDbcpPoolingDataSource(){

    }

    public static void configureBasicDataSource( ) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Properties connectionPoolProperties = getConnectionPoolProperties();
        String connectionUri = connectionPoolProperties.getProperty("dbcp.Url");
        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(connectionUri,connectionPoolProperties );

        PoolableConnectionFactory poolableConnectionFactory =
                new PoolableConnectionFactory(connectionFactory, null);
        ObjectPool<PoolableConnection> connectionPool =
                new GenericObjectPool<>(poolableConnectionFactory);
        poolableConnectionFactory.setPool(connectionPool);
        //PoolingDataSource<PoolableConnection> poolingDataSource = new PoolingDataSource<>(connectionPool);
        UtyDbcpPoolingDataSource.dataSource = new PoolingDataSource<PoolableConnection>(connectionPool);
    }

    public static DataSource createDbcpDataSource(){
        configureBasicDataSource();
        return UtyDbcpPoolingDataSource.dataSource;
    }

    public DataSource getConnection(){
        return UtyDbcpPoolingDataSource.dataSource;
    }

    public void closeConnection() throws SQLException {
        PoolingDataSource poolingDataSource = (PoolingDataSource)dataSource;
        poolingDataSource.close();
    }

}
