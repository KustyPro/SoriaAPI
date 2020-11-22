package net.soria.api.bungeecord.manager.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseAccess {

    private DatabaseCredentials databaseCredentials;
    private HikariDataSource hikariDataSource;

    public DatabaseAccess(DatabaseCredentials databaseCredentials){
        this.databaseCredentials = databaseCredentials;
    }

    private void setupHikariCP(){
        final HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setJdbcUrl(databaseCredentials.toURL());
        hikariConfig.setUsername(databaseCredentials.getUserName());
        hikariConfig.setPassword(databaseCredentials.getPassword());
        hikariConfig.setMaxLifetime(600000L);
        hikariConfig.setIdleTimeout(300000L);
        hikariConfig.setLeakDetectionThreshold(300000L);
        hikariConfig.setConnectionTimeout(10000L);

        this.hikariDataSource = new HikariDataSource(hikariConfig);
    }

    public void initPool(){
        setupHikariCP();
    }

    public void closePool(){
        this.hikariDataSource.close();
    }

    public Connection getConnection() throws SQLException {
        if(this.hikariDataSource == null){
            setupHikariCP();
        }
        return this.hikariDataSource.getConnection();
    }
}