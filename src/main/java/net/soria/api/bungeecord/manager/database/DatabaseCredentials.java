package net.soria.api.bungeecord.manager.database;

public class DatabaseCredentials {

    private String host;

    private String userName;

    private String password;

    private String databaseName;

    private int port;

    public DatabaseCredentials(String host, String userName, String password, String databaseName, int port){

        this.host = host;
        this.userName = userName;
        this.password = password;
        this.databaseName = databaseName;
        this.port = port;
    }

    public String toURL(){
        final StringBuilder sb = new StringBuilder();

        sb.append("jdbc:mysql://")
                .append(host)
                .append(":")
                .append(port)
                .append("/")
                .append(databaseName);

        return sb.toString();
    }

    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }
}
