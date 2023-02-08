<%@ page import="Model.Lettore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    <link rel="stylesheet" href="style/style.css">
    <link rel="stylesheet" href="style/header.css">
    <script>
        function validateFormSearch() {

            let selectionInput = document.getElementById('selectionInput').value;
            let searchInput = document.getElementById('searchInput').value;

            if (!["isbn", "author", "title"].includes(selectionInput)) {
                alert('Errore selection input');
                return false;
            }

            switch (selectionInput) {
                case "isbn":
                    if (!/^\d{10}$|^\d{13}$/.test(searchInput)) {
                        alert("Errore isbn")
                        return false;
                    }
                    break;
                case "author":
                    if (!/^[a-zA-Z\s]{1,100}$/.test(searchInput)) {
                        alert("Errore autore")
                        return false;
                    }
                    break;
                case "title":
                    if (!/^[a-zA-Z\s0-9]{1,30}$/.test(searchInput)) {
                        alert("Errore titolo")
                        return false;
                    }
                    break;
                default:
                    return false;
            }

            return true;
        }

        function sendRedirect() {
            if (validateFormSearch())
                document.getElementById("myForm").submit();
        }
    </script>
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
            <button type="button" onclick="validateFormSearch()" class="searchButton">
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
        <a href="login.jsp">
                <%
                } else{
            %>
            <a href="profile.jsp">
                <%
                    }
                %>
                <div class="userSection">
                    <%
                        if(lettore != null) {
                    %>
                    <h3 class="userName">Ciao <%=lettore.getUsername()+"!"%> </h3>
                    <%
                        }
                    %>
                    <span class="material-symbols-outlined user">account_circle</span>
                </div>
            </a>

            <a href="libraryPage.jsp"> <span class="material-symbols-outlined book">book</span> </a>
        </div>
    </header>

    <!-- Navigation Bar -->
    <nav class="navigationBar">
        <a href="sessione.jsp" class="underlineEffect">Sessione</a>
        <a href="RankingServlet" class="underlineEffect">Classifica</a>
        <a href="news.jsp" class="underlineEffect">Novità</a>
        <a href="consigliati.jsp" class="underlineEffect">Consigliati</a>
    </nav>

</body>
</html>
