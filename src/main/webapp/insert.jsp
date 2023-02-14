<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel = "icon" href="resources/img/Book.svg" type="image/x-icon">
  <title>Bookster: Inserimento manuale</title>

  <link rel="stylesheet" href="style/style.css">
  <link rel="stylesheet" href="style/insert.css">
</head>
<body>

<%@ include file="header.jsp"%>

<div class="path">
  <a href="index.jsp" class="underlineEffect underlineEffect--pp">Home</a>
  <span class="material-symbols-outlined">chevron_right</span>
  <p style="color: var(--color-900); font-weight: 500">Inserimento manuale</p>
</div>

<main>
  <form action="${pageContext.request.contextPath}/InsertBookServlet" method="get" id="formAddBook">
    <div>
      <label for="isbn">ISBN</label>
      <input type="text" id="isbn" name="isbn">
    </div>

    <div>
      <label for="title">Titolo</label>
      <input type="text" id="title" name="title">
    </div>

    <div>
      <label for="author">Autore</label>
      <input type="text" id="author" name="author">
    </div>

    <div>
      <label for="numberPage">Numero di pagine</label>
      <input type="text" id="numberPage" name="numberPage">
    </div>

    <div id="addBook">
      <input type="submit" value="Inserisci">
    </div>
  </form>
</main>

<%@ include file="footer.jsp"%>

</body>
</html>
