<%@ page import="Model.Lettore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HomePage</title>

    <%
        String name;
        Lettore lettore = (Lettore) request.getSession().getAttribute("lettore");
        if (lettore == null)
            name = "Accesso";
        else
            name = lettore.getNome();
    %>

</head>
<body>

<h1><%= name %></h1>
<a href="login.html">Login</a>
<br>
<a href="registrazione.html">Registrazione</a>
<br>
<a href="${pageContext.request.contextPath}/SearchServlet">Avvia</a>

</body>
</html>
