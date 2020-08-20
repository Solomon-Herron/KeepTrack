package com.keeptrack;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

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
        try {
            //List the students in MVC fashion.
            listUsers(request, response);
        }catch (Exception e){
            throw new ServletException(e);
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //get users from sqlaccessor
        List<User> users = mySQLAccessor.getUsers();
        //append user objects to request before sending the request to JSP, so the JSP can read values and display them in the response.
        request.setAttribute("USER_LIST", users); // when you append an attribute(object) to a request, the method signature require you to provide a name for the attribute and a value(the object/data structure you are appending.
        //send to JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list_users.jsp");
        dispatcher.forward(request, response);

    }
}
