<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="it-IT">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Bookster: Registrati</title>
  <link rel="stylesheet" href="style/loginSignup.css">
  <link rel="stylesheet" href="style/style.css">
</head>
<body>

<%@ include file="header.jsp"%>

<main class="containerFlexLoginSignup">
  <div class="containerSignup">
    <form action="RegistrazioneServlet" method="post" id="myForm">
      <div class="nome">
        <label for="firstName">Nome<br></label>
        <input type="text" id="firstName" class="inputSpace" name="nome" placeholder="Inserisci il nome">
      </div>

      <div class="cognome margin-top">
        <label for="lastName">Cognome<br></label>
        <input type="text" id="lastName" class="inputSpace" name="cognome" placeholder="Inserisci il cognome">
      </div>

      <div class="username margin-top">
        <label for="username">Username<br></label>
        <input type="text" id="username" class="inputSpace" name="username" placeholder="Inserisci un username">
      </div>

      <div class="email margin-top">
        <label for="email">Email<br></label>
        <input type="email" id="email" class="inputSpace" name="email" placeholder="Inserisci l'email">
      </div>

      <div class="password margin-top">
        <label for="password">Password<br></label>
        <input type="password" id="password" class="inputSpace" name="password" placeholder="Inserisci la password">
      </div>

      <div class="password margin-top">
        <label for="confirmPassword">Conferma password<br></label>
        <input type="password" id="confirmPassword" class="inputSpace" name="password" placeholder="Reinserisci la password">
      </div>

      <div class="birthday margin-top">
        <label for="birthday">Data di nascita<br></label>
        <input type="date" id="birthday" class="inputSpace" name="birthday" placeholder="Inserisci la data di nascita">
      </div>

      <div class="password margin-top">
        <label for="phone">Telefono<br></label>
        <input type="number" id="phone" class="inputSpace" name="phone" placeholder="Inserisci il numero di telefono">
      </div>

      <div class="loginSignup">
        <input type="button" class="button" value="Registrati" onclick="validateForm()">
      </div>
    </form>
  </div>
</main>

<%@ include file="footer.jsp"%>

</body>
<script src="resources/javascript/validateForm.js"></script>
</html>
