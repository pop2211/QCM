package fr.eni.tp.qcm.dal.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.eni.tp.qcm.common.exception.TechnicalException;
import fr.eni.tp.qcm.config.AppConfig;

public class MSSQLConnectionFactory {

    private MSSQLConnectionFactory() {
    }
    
    /**
     * Getting new MSSQL COnnection.
     * 
     * @return A connection to the MSSQL DATABASE.
     */
    public static Connection get() {
        Connection connection = null;
        
        try {
            
            String url = AppConfig.get("database.mssql.url");
            String username = AppConfig.get("database.mssql.username");
            String password = AppConfig.get("database.mssql.password");
            
            connection = DriverManager.getConnection(url, username, password);
            
        } catch (SQLException e) {
            throw new TechnicalException("Could not load MSSQL Connection", e);
        }
        return connection;
    }
}

















