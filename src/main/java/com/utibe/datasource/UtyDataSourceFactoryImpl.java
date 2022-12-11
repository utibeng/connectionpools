package com.utibe.datasource;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtyDataSourceFactoryImpl implements UtyDataSourceFactory{

    private final static Logger logger = LoggerFactory.getLogger(UtyDataSourceFactoryImpl.class);

    public DataSource createDataSource(String datasourceType){
        if(datasourceType.equalsIgnoreCase("hikari")){
            logger.info("Hikari datasource selected, will attempt to configure");
            return UtyHikariDataSource.createHikariDataSource();
        }
        else if(datasourceType.equalsIgnoreCase("dbcp-basic")){
            logger.info("Apache Dbcp datasource selected, will attempt to configure");
            //return UtyHikariDataSource.createHikariDataSource();
            return UtyDbcpBasicDataSource.createDbcpDataSource();
        }
        else if(datasourceType.equalsIgnoreCase("dbcp-polling-data")){
            logger.info("Apache Dbcp polling data selected, will attempt to configure");
            return UtyDbcpPoolingDataSource.createDbcpDataSource();
        }
        else if(datasourceType.equalsIgnoreCase("c3p0")){
            logger.info("C3P0 data selected, will attempt to configure");
            return UtyC3P0DataSource.createC3P0DataSource();
        }

        logger.error("Invalid datasource type specified, will exit");

        System.exit(1);

        return null;
    }
}
