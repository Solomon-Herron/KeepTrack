<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
  <title>user Tracker App</title>

  <link type="text/css" rel="stylesheet" href="styles/style.css">
</head>

<body>

<div id="wrapper">
  <div id="header">
    <h2>User Tracker</h2>
  </div>
</div>

<div id="container">

  <div id="content">

    <!-- put new button: Add user -->

    <input type="button" value="Add user"
           onclick="window.location.href='add-user.jsp'; return false;"
           class="add-user-button"
    />

    <table>

      <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Action</th>
      </tr>

      <c:forEach var="tempuser" items="${USER_LIST}">

        <!-- set up a link for each user -->
        <c:url var="tempLink" value="UserControllerServlet">
          <c:param name="command" value="LOAD" />
          <c:param name="userId" value="${tempuser.id}" />
        </c:url>

        <!--  set up a link to delete a user -->
        <c:url var="deleteLink" value="UserControllerServlet">
          <c:param name="command" value="DELETE" />
          <c:param name="userId" value="${tempuser.id}" />
        </c:url>

        <tr>
          <td> ${tempuser.firstName} </td>
          <td> ${tempuser.lastName} </td>
          <td> ${tempuser.email} </td>
          <td>
            <a href="${tempLink}">Update</a>
            |
            <a href="${deleteLink}"
               onclick="if (!(confirm('Are you sure you want to delete this user?'))) return false">
              Delete</a>
          </td>
        </tr>

      </c:forEach>

    </table>

  </div>

</div>
</body>


</html>








