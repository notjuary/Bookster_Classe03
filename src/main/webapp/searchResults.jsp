<%@ page import="Model.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel = "icon" href="resources/img/Book.svg" type="image/x-icon">
  <title>Bookster: <%=request.getParameter("searchBar")%></title>

  <link rel="stylesheet" href="style/style.css">
  <link rel="stylesheet" href="style/catalog.css">
</head>
<body>
  <%@ include file="header.jsp"%>

  <main class="section">
    <div id="productContainerID" class="productContainer">
    <%
      ArrayList<Book> list = (ArrayList<Book>) request.getAttribute("books");
      request.getSession().setAttribute("bookList", list);

      for (int i = 0; i < list.size(); i++)
      {

        Book book = list.get(i);
    %>
      <div class="card">
        <a href="BookInformationServlet?libro=<%=i%>">
          <div class="image">
            <img src="<%=book.getPath()%>" alt="1<%=book.getTitle()%>">
          </div>
        </a>
        <div class="info">
          <div class="title"><span><%=book.getTitle()%></span><%=book.getAuthor()%></div>
        </div>
        <div class="buttons" id="buttons">
          <button class="favourite">Preferiti</button>
          <button class="addToLib">Aggiungi alla libreria</button>
        </div>
      </div>
    <% } %>

  </main>

  <%@ include file="footer.jsp"%>
</body>
</html>
