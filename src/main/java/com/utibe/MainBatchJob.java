package com.utibe;

import com.utibe.datasource.UtyDatasource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MainBatchJob {

    //private static final Logger logger = LogManager.getLogger();
    private final static Logger logger = LoggerFactory.getLogger(MainBatchJob.class);


    public static void main (String [] args){

        logger.info("emile");

        UtyDatasource utyDatasource = new UtyDatasource("HIKARI");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        DataSource datasource = utyDatasource.getDataSource();

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

                //datasource.close();

            } catch (SQLException exception) {

                logger.error("Problem querying Hosts table, {}", exception.getMessage());

                //Logger lgr = Logger.getLogger(HikariCPEx.class.getName());
                //lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }




    }

}
