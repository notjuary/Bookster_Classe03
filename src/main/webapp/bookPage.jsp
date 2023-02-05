<%@ page import="Model.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel = "icon" href="resources/img/Book.svg" type="image/x-icon">

    <%
        Book book = (Book) request.getAttribute("book");
        String isbn = (String) request.getSession().getAttribute("isbn");

        // Da cancellare
        System.out.println(isbn);
    %>

    <title>Bookster: <%=book.getTitle()%></title>
    <link rel="stylesheet" href="style/bookPage.css">
</head>
<body>
    <%@ include file="header.jsp"%>

    <%-- Percorso del libro --%>
    <div class="path">
        <a href="index.jsp" class="underlineEffect underlineEffect--pp">Home</a>
        <span class="material-symbols-outlined">chevron_right</span>
        <a href="#" class="underlineEffect underlineEffect--pp">Catalogo</a>
        <span class="material-symbols-outlined">chevron_right</span>
        <% if(book.getCategory().compareToIgnoreCase("N/A") != 0) {%>
        <a href="#" class="underlineEffect underlineEffect--pp"><%=book.getCategory()%></a>
        <span class="material-symbols-outlined">chevron_right</span>
        <%
            }
        %>
        <% if(book.getTitle().length() > 60) {
            String newTitle = book.getTitle().substring(0, 60) + "...";%>
        <p style="color: var(--color-900); font-weight: 500"><%=newTitle%></p>
        <%
            } else {
        %>
        <p style="color: var(--color-900); font-weight: 500"><%=book.getTitle()%></p>
        <%
            }
        %>
    </div>

    <main>
        <div class="gridContainer">
            <div class="containerImage">
                <img src="<%=book.getPath()%>" alt="<%=book.getTitle()%>">
            </div>

            <div class="containerTitle">
                <div class="underlineDiv">
                    <h1><%=book.getTitle()%></h1>
                </div>
            </div>

            <div class="containerInfo">
                <p><span>Pagine: </span><%=book.getPages()%></p>
                <p><span>Autore: </span><%=book.getAuthor()%></p>
                <p><span>Editore: </span><%=book.getPublisher()%></p>
                <p><span>Data pubblicazione: </span><%=book.getYear()%></p>
            </div>


            <% if(book.getDescription().compareToIgnoreCase("N/A") != 0) {%>
            <div class="containerDescription">
                <div class="descriptionSection">
                    Descrizione
                </div>
                <div>
                    <%=book.getDescription()%>
                </div>
            </div>
            <%
                }
            %>


            <div class="containerButtons">
                <button name="addToLib" value="addToLib" class="addToLib" onclick="window.location.href='LibraryBookInfoServlet?action=libreria'">
                    <span class="material-symbols-outlined">menu_book</span> Aggiungi alla libreria
                </button>
                <span class="material-symbols-outlined favourite" onclick="window.location.href='LibraryBookInfoServlet?action=preferiti'">favorite</span>
                </form>
            </div>
        </div>
    </main>

    <%@ include file="footer.jsp"%>
</body>
</html>
