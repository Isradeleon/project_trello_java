package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Isra
 */
public abstract class Model_T {
    private final String USER_DB = "root";
    private final String PASSWORD_DB = "";
    private final String HOST_DB = "localhost";
    private final String DATABASE = "project_db";
    private final String PORT_DB = "3306";
    
    protected Connection conn;
    protected Statement stm;
    protected String query; 
    
    protected Connection connectDB(){
        try{
            conn = DriverManager.getConnection(
                // CONNECTION STRING:
                "jdbc:mysql://"+this.HOST_DB+":"+this.PORT_DB+"/"+this.DATABASE,
                // CREDENTIALS:
                this.USER_DB,
                this.PASSWORD_DB
            );
            return conn;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public void closeConn(){
        try{
            if (!conn.isClosed()) {
                conn.close();
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
