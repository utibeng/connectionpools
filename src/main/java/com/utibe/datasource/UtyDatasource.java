package com.utibe.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class UtyDatasource {


    DataSource dataSource;
    String hikariPropertiesLocation = "src/main/resources/hikari.properties";
    public UtyDatasource(String datasourceType){
        if(datasourceType.equalsIgnoreCase("hikari")){
            HikariConfig hikariConfig = new HikariConfig(hikariPropertiesLocation);
            dataSource = new HikariDataSource(hikariConfig);
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}