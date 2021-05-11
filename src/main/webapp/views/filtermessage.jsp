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
        List<MessageDataSet> messagesFilter = (List<MessageDataSet>) request.getAttribute("FilterMessage");

        if (messagesFilter != null && !messagesFilter.isEmpty()) {
            for (MessageDataSet msg : messagesFilter) {
                out.println("<li>" + "id = " + msg.getId() + ", login = " + msg.getMessage() + ", e-mail = " + msg.getMessageTag() + "</li>");
            }
        }
    %>
</ul>
</body>
</html>
