<%--
  Created by IntelliJ IDEA.
  User: solom
  Date: 8/16/2020
  Time: 2:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Keep Track</title>
  </head>
  <body>
    <h1>Keep track of users</h1>

    <div class="wrapper">
      <div class="header">

      </div>

      <div class="add-user">
        <button type="button" value="Add User" onclick="window.location.href='add-user.jsp'; return false;"></button>
      </div>
      <div class="container">

        <table>
          <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
          </tr>

          <c:forEach var="tempUser" items="${USER_LIST}">
            <tr>
              <td>${tempUser.firstName}</td>
              <td>${tempUser.lastName}</td>
              <td>${tempUser.email}</td>
            </tr>
          </c:forEach>
        </table>

    </div>
  </div>
  </body>
</html>
