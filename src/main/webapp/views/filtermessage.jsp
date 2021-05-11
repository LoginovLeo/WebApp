<%@ page import="services.DBService.dataSets.UsersDataSet" %>
<%@ page import="java.util.List" %>
<%@ page import="services.DBService.dataSets.MessageDataSet" %><%--
  Created by IntelliJ IDEA.
  User: Leonid
  Date: 11.05.2021
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <%
        List<MessageDataSet> messages = (List<MessageDataSet>) request.getAttribute("FilterMessage");

        if (messages != null && !messages.isEmpty()) {
            for (MessageDataSet message : messages) {
                out.println("<li>" + "id = " + message.getId() + ", login = " + message.getMessage() + ", e-mail = " + message.getMessageTag() + "</li>");
            }
        }
    %>
</ul>
</body>
</html>
