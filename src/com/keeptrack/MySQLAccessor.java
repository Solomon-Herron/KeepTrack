package com.keeptrack;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//This is the DAO class, (data accessor object)
public class MySQLAccessor {

    private DataSource dataSource;
    public MySQLAccessor(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public List<User> getUsers() throws Exception{
        List<User> users =new ArrayList<>();
        Connection conn = null;
        Statement stmnt = null;
        ResultSet rs = null;

        try{
            //Create Connection
            conn = dataSource.getConnection();
            //create SQL statement
            String sql  = "select * from web_user_tracker"; //sql statement
            stmnt = conn.createStatement(); //statement object created for connection
            //execute query
            rs =stmnt.executeQuery(sql);
            //proccess result set
            while (rs.next()){
                //extract data from rs
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String  lastName = rs.getString("last_name");
                String email = rs.getString("email");
                //create new student object

                //add student to array list
            }
            return users;
        } finally {
            close(conn, stmnt, rs);
        }
    }

    private void close(Connection conn, Statement stmnt, ResultSet rs) {
        try{
            if(rs != null){
                rs.close();
            }
            if(conn != null){
                conn.close(); //Does not actually close connection, it just adds connection back to pool.
            }
            if(stmnt != null){
                stmnt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
