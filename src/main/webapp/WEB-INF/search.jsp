<%@ page import="Model.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
</head>
<body>

    <%
        ArrayList<Book> list = (ArrayList<Book>) request.getAttribute("books");

        for (int i = 0; i < list.size(); i++)
        {
            Book book = list.get(i);
    %>

    <%= list.toString() %>

    <% } %>

</body>
</html>
