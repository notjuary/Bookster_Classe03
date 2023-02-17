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

  <script>

    function validateFormInsert() {
      let isbn = document.getElementById('isbn').value;
      let title = document.getElementById('title').value;
      let author = document.getElementById('author').value;
      let numberPage = document.getElementById('numberPage').value;

      if (isbn.length !== 13 && isbn.length !== 10) {
        alert('Errore lungehzza iban');
        return false;
      } else if (!/^[0-9]+$/.test(isbn)) {
        alert('Errore formato isbn');
        return false;
      }

      if (title.length > 100 || title.length < 1) {
        alert('Errore lugnhezza titolo');
        return false;
      } else if (!/^[a-zA-Z\s]+$/.test(title)) {
        alert('Errore formato titolo');
        return false;
      }

      if (author.length > 30 || author.length < 1) {
        alert('Errore lunghezza titolo');
        return false;
      } else if (!/^[a-zA-Z\s0-9]+$/.test(author)) {
        alert('Errore formato titolo');
        return false;
      }

      if (numberPage <= 0) {
        alert('Errore numero pagine');
        return false;
      } else if (!/^[0-9]+$/.test(numberPage.toString())) {
        alert('Errore formato pagine');
        return false;
      }
    }

    function sendInsert() {
      if (validateFormInsert()) {
        document.getElementById("myForm").action = "InsertBookServlet";
        document.getElementById("myForm").submit();
      }
    }

  </script>
</head>
<body>

<%@ include file="header.jsp"%>

<div class="path">
  <a href="index.jsp" class="underlineEffect underlineEffect--pp">Home</a>
  <span class="material-symbols-outlined">chevron_right</span>
  <p style="color: var(--color-900); font-weight: 500">Inserimento manuale</p>
</div>

<main>
  <form onsubmit="sendInsert()" method="post" id="formAddBook">
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
