package com.utibe.datasource;

import javax.sql.DataSource;

public interface UtyDataSourceFactory {



    public DataSource createDataSource(String datasourceType);
}
