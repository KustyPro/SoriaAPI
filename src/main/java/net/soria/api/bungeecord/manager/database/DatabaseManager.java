package net.soria.api.bungeecord.manager.database;

public enum DatabaseManager {

    SORIAMC(new DatabaseCredentials("127.0.0.1", "root", "", "players", 3306));

    private DatabaseAccess databaseAccess;

    DatabaseManager(DatabaseCredentials databaseCredentials) {
        this.databaseAccess = new DatabaseAccess(databaseCredentials);
    }

    public DatabaseAccess getDatabaseAccess(){
            return databaseAccess;
        }

    public static void initAllDatabaseConnection(){
        for(DatabaseManager databaseManager : values()){
            databaseManager.databaseAccess.initPool();
        }
    }

    public static void closeAllDatabaseConnection(){
        for(DatabaseManager databaseManager : values()){
            databaseManager.databaseAccess.closePool();
        }
    }


}
