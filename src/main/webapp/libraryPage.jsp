<%@ page import="Model.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Libreria" %><%--
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

            <% List<Book> list=null;
      if(request.getSession().getAttribute("libraryList")==null){ %>
        <div class="card">
                <div class="buttons" id="buttons">
                    <button class="addToLib" name="addToLib" onclick="window.location.href='login.jsp'">Crea libreria</button>
                </div>
        </div>
     <% }else if(request.getSession().getAttribute("libraryList")!=null && request.getSession().getAttribute("libreria")!=null){
          Libreria libreria= (Libreria) request.getSession().getAttribute("libreria"); %>
                <div class="card">
                    <div class="info">
                        <div class="title"><span><%=libreria.getTitolo()%></span>
                            <span>Numero libri:<%=libreria.getNumeroLibri()%></span></div>
                    </div>

                </div>
      <% list = (List<Book>) request.getSession().getAttribute("libraryList");


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
    </div>
            <% }
    }
%>

</main>
<hr width="100%" />
<main class="section">
    <div id="productContainerID" class="productContainer">

            <%
      if(request.getSession().getAttribute("libraryFavourite")==null){ %>
        <div class="card">
            <div class="buttons" id="buttons">
                <button class="addToLib" name="addToLib" onclick="window.location.href='login.jsp'">Crea libreria</button>
            </div>
        </div>
                <%} else if(request.getSession().getAttribute("libraryFavourite")!=null && request.getSession().getAttribute("libreria")!=null){ %>
                <div class="card">
                        <div class="info">
                        <div class="title"><span>Libri preferiti</span>
                    </div>
                   <% List<Book> listFavorite = (List<Book>) request.getSession().getAttribute("libraryFavourite");


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
                        </div>
                <% }
    }
%>

</main>

<%@ include file="footer.jsp"%>
</body>
</html>
