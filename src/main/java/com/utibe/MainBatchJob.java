package com.utibe;

import com.utibe.datasource.UtyDataSource;
import com.utibe.datasource.UtyDataSourceFactory;
import com.utibe.datasource.UtyDataSourceFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//https://mvnrepository.com/open-source/jdbc-pools?p=1


public class MainBatchJob {

    private final static Logger logger = LoggerFactory.getLogger(MainBatchJob.class);
    //private final static String connectionPoolType = "HikaRi";
    //private final static String connectionPoolType = "dbcp-basic";
    //private final static String connectionPoolType = "dbcp-polling-data";
    private final static String connectionPoolType = "C3p0";



    public static void main (String [] args){

        UtyDataSourceFactory utyDataSourceFactory = new UtyDataSourceFactoryImpl();
        DataSource datasource = utyDataSourceFactory.createDataSource(connectionPoolType);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try{
            connection = datasource.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM HOSTS");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                logger.info("Found Games {} hosted by {}", resultSet.getString(1), resultSet.getString(4));
            }

        }
        catch (SQLException exception) {

            logger.error("Problem querying Hosts table, {}", exception.getMessage());

        }
        finally {

            try {

                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (connection != null) {
                    connection.close();
                }

                UtyDataSource.closeConnection(connectionPoolType, datasource);

                //datasource.close();

            }
            catch (SQLException exception) {

                logger.error("Problem querying Hosts table, {}", exception.getMessage());
            }
        }
    }

}
