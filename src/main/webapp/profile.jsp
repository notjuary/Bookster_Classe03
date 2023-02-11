<%@ page import="DataTier.Bookster.Lettore.Lettore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel = "icon" href="resources/img/Book.svg" type="image/x-icon">
  <title>Bookster: Profilo personale</title>

  <link rel="stylesheet" href="style/style.css">
  <link rel="stylesheet" href="style/profile.css">
</head>
<body>
  <%@ include file="header.jsp"%>

  <main>
    <p><%=lettore.getNome()%></p>
    <p><%=lettore.getCognome()%></p>
    <p><%=lettore.getDdn()%></p>
    <p><%=lettore.getEmail()%></p>
  </main>

  <%@ include file="footer.jsp"%>

</body>
</html>
