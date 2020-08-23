package com.keeptrack;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            //read command parameter
            String command = request.getParameter("command");

            //if command is missing, then default to listing students
            if (command == null){
                command = "LIST";
            }
            //route to the appropriate method
            switch (command){ //you can use the switch conditional to redirect to different servlets that route traffic to different models.
                case "LIST":
                    listUsers(request, response);
                    break;
                case "ADD":
                    addUser(request, response);
                    break;
                case "LOAD":
                    loadUser(request, response);
                    break;
                case "UPDATE":
                    updateUser(request, response);
                    break;
                case "DELETE":
                    deleteUser(request, response);
                    break;
                default:
                    listUsers(request, response);
            }
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

    private void loadUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get user id from jsp
        String userId= request.getParameter("userId");
        //get user info from mysqlaccessor
        User updatedUser = mySQLAccessor.getUser(userId);
        //place that student in a request attribute
        request.setAttribute("UPDATE_USER", updatedUser);
        //send this to the update-user.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/update-user.jsp");
        dispatcher.forward(request,response);

    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) {
       //read user data
       String firstName = request.getParameter("firstName");
       String lastName = request.getParameter("lastName");
       String email = request.getParameter("email");
       //create a new user obj
       User newUser = new User(firstName, lastName, email);
       //add the user to the DB
       mySQLAccessor.addUser(newUser);
        //send back to main page
        try {
            listUsers(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("userId");
        int userID = Integer.parseInt(userId);
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        User updatedUser = new User(userID, firstName, lastName, email);
        mySQLAccessor.updateUser(updatedUser, userID);
        listUsers(request, response);

    }
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String userId = request.getParameter("userId");
    mySQLAccessor.deleteUser(userId);
    listUsers(request, response);
    }


}
