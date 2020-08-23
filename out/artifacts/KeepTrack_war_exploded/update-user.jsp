<%--
  @author: Solomon Herron
  Date: 8/23/2020
  Time: 1:37 PM
  Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
  Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure 
  dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, 
  sunt in culpa qui officia deserunt mollit anim id est laborum.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="UserControllerServlet" method="GET">
    <input type="hidden" name="command" value="UPDATE"/>
    <input type="hidden" name="userId" value="${UPDATE_USER.id}"/>

    <table>
        <tbody>
        <tr>
            <td><label>First name:</label></td>
            <td><input type="text" name="firstName" value="${UPDATE_USER.firstName}" /></td>
        </tr>

        <tr>
            <td><label>Last name:</label></td>
            <td><input type="text" name="lastName" value="${UPDATE_USER.lastName}"/></td>
        </tr>

        <tr>
            <td><label>Email:</label></td>
            <td><input type="text" name="email" value="${UPDATE_USER.email}"/></td>
        </tr>

        <tr>
            <td><label></label></td>
            <td><input type="submit" value="Save" class="save" /></td>
        </tr>

        </tbody>
    </table>
    </form>
</body>
</html>
