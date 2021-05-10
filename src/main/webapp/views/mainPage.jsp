<%@ page import="java.util.List" %>
<%@ page import="services.DBService.dataSets.UsersDataSet" %>
<%--
  Created by IntelliJ IDEA.
  User: LLoginov
  Date: 08.05.2021
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MainPage</title>
</head>
<body>
<h1>Main page</h1>
<p>Welcome to main page</p>
<h3>List of users</h3>
<ul>
    <%
        List<UsersDataSet> users = (List<UsersDataSet>) request.getAttribute("ListOfUser");

        if (users != null && !users.isEmpty()) {
            for (UsersDataSet user : users) {
                out.println("<li>" + "id = " + user.getId() + ", login = " + user.getName() + ", e-mail = " + user.getEmail() + "</li>");
            }
        }
    %>
</ul>
<div>       <!-- content -->
    <div>    <!-- buttons holder -->
        <button onclick="location.href='/signOut'">SignOut</button>
    </div>
</div>

</body>
</html>
