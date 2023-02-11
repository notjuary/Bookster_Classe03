<%@ page import="java.util.List" %>
<%@ page import="DataTier.Bookster.Lettore.Lettore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel = "icon" href="resources/img/Book.svg" type="image/x-icon">
  <title>Bookster: Classifica</title>

  <link rel="stylesheet" href="style/style.css">
  <link rel="stylesheet" href="style/classifica.css">
</head>
<body>
  <%@ include file="header.jsp"%>

  <div class="path">
    <a href="index.jsp" class="underlineEffect underlineEffect--pp">Home</a>
    <span class="material-symbols-outlined">chevron_right</span>
    <p style="color: var(--color-900); font-weight: 500">Classifica</p>
  </div>

  <main>

    <%
      List<Lettore> ranking = (List<Lettore>) request.getAttribute("ranking");
      int counter = 1;
      if(ranking.size() > 0) {
        for(Lettore l : ranking) {
    %>
    <div class="user--ranking">
      <div class="ranking--icon">
        <%
          if(counter == 1) {
        %>
        <img src="resources/img/Medals/Medaglia_1.png" alt="1">
        <%
          }
          if(counter == 2) {
        %>
        <img src="resources/img/Medals/Medaglia_2.png" alt="2">
        <%
          }
          if(counter == 3) {
        %>
        <img src="resources/img/Medals/Medaglia_3.png" alt="3">
        <%
          }
        %>
      </div>
      <div class="ranking--name"><%=l.getUsername()%></div>
      <div class="ranking--score"><%=l.getPunteggio()%></div>
    </div>
    <%
          counter++;
        }
    } else {
    %>
      <h1>Classifica vuota</h1>
    <%
      }
    %>
  </main>

  <%@ include file="footer.jsp"%>
</body>
</html>
