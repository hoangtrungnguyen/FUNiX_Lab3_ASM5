package org.example.studentmanager.datalayer;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    private static DataSource instance;

    Connection conn;

    private DataSource(){}

    public static DataSource getInstance(){
        synchronized(DataSource.class) {
            if(null == instance) {
                instance  = new DataSource();
                try {
                    instance.conn = DriverManager.getConnection("jdbc:h2:~/test");
                } catch (SQLException e) {
                    System.out.println("unable to open connection");
                }
            }
        }
         return  instance;
    }


    public void close(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("error while closing db");
        }
    }





}
