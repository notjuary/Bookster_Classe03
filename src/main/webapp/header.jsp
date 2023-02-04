<%@ page import="Model.Lettore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    <link rel="stylesheet" href="style/style.css">
    <link rel="stylesheet" href="style/header.css">
    <script src="resources/javascript/validateSearch.js"></script>
</head>
<body>
<header class="header">
    <div class="utility">
        <form class="searchBox" action="SearchServlet" method="get" id="myForm">
            <select id="selectionInput" name="selectionInput">
                <option value="title" selected="title">Titolo</option>
                <option value="author">Autore</option>
                <option value="isbn">ISBN</option>
            </select>
            <input type="text" id="searchInput" name="searchBar">
            <button type="button" onclick="validateForm()" class="searchButton">
                <span class="material-symbols-outlined search">search</span>
            </button>
        </form>
    </div>

    <div class="logoSection">
        <a href="index.jsp"><div id="logo"></div></a>
    </div>

    <div class="userArea">
        <%
            Lettore lettore = (Lettore) request.getSession().getAttribute("lettore");


            if(request.getSession() == null || lettore == null) {
        %>
        <a href="login.html">
                <%
                } else{
            %>
            <a href="ServletAccount">
                <%
                    }
                %>
                <div class="userSection">
                    <%
                        if(lettore != null) {
                    %>

                    <h3 class="userName">Ciao <%=lettore.getUsername()+"!"%> </h3>

                    <div class="lecterCircle"><%=lettore.getUsername().substring(0,1).toUpperCase()%></div>

                    <%
                        }
                    %>
                    <span class="material-symbols-outlined user">account_circle</span>
                </div>
            </a>

            <a href=""> <span class="material-symbols-outlined book">book</span> </a>
    </div>
</header>

<!-- Navigation Bar -->
<nav class="navigationBar">
    <a href="sales.jsp" class="underlineEffect">Sessione</a>
    <a href="catalog.jsp" class="underlineEffect">Catalogo</a>
    <a href="news.jsp" class="underlineEffect">Novità</a>
    <a href="partnership.jsp" class="underlineEffect">Consigliati</a>
</nav>

</body>
</html>
