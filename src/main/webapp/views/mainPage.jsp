<%@ page import="java.util.List" %>
<%@ page import="services.DBService.dataSets.UsersDataSet" %>
<%@ page import="services.DBService.dataSets.MessageDataSet" %>
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
<h3>List of messages</h3>
<p>Add message</p>
<form method="post" >
    <label>message:
        <input type="text" name="message"><br />
    </label>
    <label>tag:
        <input type="text" name="tag"><br />
    </label>
    <button type="submit" >Add</button>
</form>
<p>Filter message</p>
<form method="get" >
    <label>tag:
        <input type="text" name="tag"><br />
    </label>
    <button type="submit" >Filter</button>
</form>

<ul>
    <%
        List<MessageDataSet> messagesFilter = (List<MessageDataSet>) request.getAttribute("FilterMessage");

        if (messagesFilter != null && !messagesFilter.isEmpty()) {
            for (MessageDataSet msg : messagesFilter) {
                out.println("<li>" + "id = " + msg.getId() + ", message = " + msg.getMessage() + ", tag = " + msg.getMessageTag() + "</li>");
            }
        }
    %>
</ul>
<p>All messages</p>
<ul>
    <%
            List<MessageDataSet> messages = (List<MessageDataSet>) request.getAttribute("Messages");

        if (messages != null && !messages.isEmpty()) {
            for (MessageDataSet message : messages) {
                out.println("<li>" + "message = " + message.getMessage() + ", tag = " + message.getMessageTag() + ", login = " + message.getUserLogin() + "</li>");
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
