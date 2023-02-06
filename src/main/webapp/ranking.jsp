<%@ page import="java.util.List" %>
<%@ page import="Model.Lettore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <% List<Lettore> lettori = (List<Lettore>) request.getAttribute("ranking"); %>

    <% for (Lettore lettore: lettori) { %>
        <%= lettore.toString() + "\n" %>
    <% } %>

</body>
</html>
