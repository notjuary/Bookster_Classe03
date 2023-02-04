<%@ page import="Model.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Francesco Barlotti
  Date: 04/02/2023
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
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
            <p1>Your library</p1>
            <%
      List<Book> list=null;

      if(request.getSession().getAttribute("libraryList")!=null){
      list = (List<Book>) request.getSession().getAttribute("libraryList");


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

        </div>
            <% }
    }
%>

</main>
<hr width="100%" />
<main class="section">
    <div id="productFavouriteContainerID" class="productContainer">

        <p1>Your favourites</p1>
            <%
      if(request.getSession().getAttribute("libraryFavourite")!=null){
        List<Book> listFavorite = (List<Book>) request.getSession().getAttribute("libraryFavourite");


      for (int i = 0; i < listFavorite.size(); i++)
      {

        Book bookFavourite = listFavorite.get(i);
    %>
        <div class="card">
            <a href="BookInformationServlet?libro=<%=i%>">
                <div class="image">
                    <img src="<%=bookFavourite.getPath()%>" alt="1<%=bookFavourite.getTitle()%>">
                </div>
            </a>
            <div class="info">
                <div class="title"><span><%=bookFavourite.getTitle()%></span><%=bookFavourite.getAuthor()%></div>
            </div>

        </div>
            <% }
    }
%>

</main>

<%@ include file="footer.jsp"%>
</body>
</html>
