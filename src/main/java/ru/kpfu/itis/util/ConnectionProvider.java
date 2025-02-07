package ru.kpfu.itis.util;

import ru.kpfu.itis.exception.DbException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionProvider {

    private static ConnectionProvider INSTANCE;

    public static ConnectionProvider getInstance() throws DbException {
        if (INSTANCE == null) {
            INSTANCE = new ConnectionProvider();
        }
        return INSTANCE;
    }

    private static Connection con;

    private ConnectionProvider() throws DbException {
        try {
            DbConfig dbConfig = new DbConfig();

            Class.forName(DbConfig.getDatabaseDriver()).newInstance();
            con = DriverManager.getConnection(DbConfig.getDatabaseUrl(),
                                              DbConfig.getDatabaseUsername(),
                                              DbConfig.getDatabasePassword());

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new DbException("Can't connect to DB.", e);
        }
    }

    public static Connection getCon() {
        return con;
    }

}
