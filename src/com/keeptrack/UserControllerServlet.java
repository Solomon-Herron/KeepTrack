package com.keeptrack;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

public class UserControllerServlet extends javax.servlet.http.HttpServlet {

    private MySQLAccessor mySQLAccessor;
    @Resource(name="jdbc/web_user_tracker")
    private DataSource dataSource;

    @Override //intialize the connection as soon as tomcat starts up
    public void init() throws ServletException {
        super.init();
        try{
            mySQLAccessor = new MySQLAccessor(dataSource);
        } catch (Exception e) {
            throw new ServletException(e);
        }

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //List the students in MVC fashion.
        listUsers(request, response);

    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) {
        //get users from sqlaccessor

        //add user objects to request
    }
}
