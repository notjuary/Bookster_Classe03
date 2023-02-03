<%@ page import="Model.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel = "icon" href="resources/img/Book.svg" type="image/x-icon">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <%
        Book book = (Book) request.getAttribute("book");
    %>

    <title>Bookster: <%=book.getTitle()%></title>

    <link rel="stylesheet" href="style/style.css">
    <link rel="stylesheet" href="style/bookPage.css">
</head>
<body>
    <%@ include file="header.jsp"%>

    <div class="path">
        <a href="index.jsp" class="underlineEffect underlineEffect--pp">Home</a>
        <p> <i class='fas fa-angle-right'></i> </p>
        <a href="#" class="underlineEffect underlineEffect--pp">Catalogo</a>
        <p> <i class='fas fa-angle-right'></i> </p>
        <a href="#" class="underlineEffect underlineEffect--pp"><%=book.getCategory()%></a>
        <p> <i class='fas fa-angle-right'></i> </p>
        <p style="color: var(--color-900);"><%=book.getTitle()%></p>
    </div>
    <main>
        <div class="gridContainer">
            <div class="containerImage">
                <img src="<%=book.getPath()%>" alt="<%=book.getTitle()%>">
            </div>

            <div class="containerTitle">
                <div class="orangeUnderlineDiv">
                    <h1><%=book.getTitle()%></h1>
                </div>
            </div>

            <div class="containerDescription">
                <p><%=book.getPages()%></p>
                <p><%=book.getAuthor()%></p>
                <p><%=book.getPublisher()%></p>
                <p><%=book.getDescription()%></p>
                <p><%=book.getYear()%></p>
            </div>

            <div class="containerButtons">
                <form action="LibraryServlet" method="get">
                    <button name="addToLib" value="addToLib" onclick="window.location.href='LibraryServlet?title=<%=book.getTitle()%>'" class="addToCart--productpPage">
                    <span class="material-symbols-outlined">shopping_bag</span>
                    <p>Aggiungi alla libreria</p>
                </button>
                <span class="material-symbols-outlined favourite" onclick="">favorite</span>
                </form>
            </div>
        </div>
    </main>

    <%@ include file="footer.jsp"%>
</body>
</html>
