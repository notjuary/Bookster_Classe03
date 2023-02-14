<%@ page import="DataTier.Bookster.Book.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="DataTier.Bookster.Libreria.Libreria" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel = "icon" href="resources/img/Book.svg" type="image/x-icon">
    <title>Bookster: Libreria</title>

    <link rel="stylesheet" href="style/style.css">
    <link rel="stylesheet" href="style/library.css">
</head>
<body>

<%@ include file="header.jsp"%>

<main>
    <div class="container">
        <div class="containerLibrary">
            <h3 style="text-align: center; text-transform: uppercase;">Libreria</h3>
        </div>

        <div  class="containerFavorite">
            <h3 style="text-align: center; text-transform: uppercase;">Preferiti</h3>
            <%
                List<Book> libraryFavourite = (List<Book>) session.getAttribute("libraryFavourite");
                if(libraryFavourite != null) {
                    for(Book b : libraryFavourite) {
            %>
                <div class="book">
                    <div class="thumbnail">
                        <img src="<%=b.getPath()%>">
                    </div>
                    <div class="info">
                        <p><%=b.getTitle()%></p>
                        <p><%=b.getAuthor()%></p>
                        <p><%=b.getPages()%></p>
                    </div>
                </div>
            <%
                    }
                }
            %>
        </div>

        <div class="button--addBok" onclick="window.location.href='insert.jsp'">Clicca qui per inserire un libro manualmente</div>
    </div>
</main>

<%@ include file="footer.jsp"%>

</body>
</html>