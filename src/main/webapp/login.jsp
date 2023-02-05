<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel = "icon" href="resources/img/Book.svg" type="image/x-icon">
  <title>Bookster: Accedi</title>
  <link rel="stylesheet" href="style/style.css">
  <link rel="stylesheet" href="style/loginSignup.css">
</head>
<body>
  <%@ include file="header.jsp"%>

  <main class="containerFlexLoginSignup">
    <div class="containerLogin">
      <form action="LoginServlet" method="post" id="formLoginSignup">
        <div class="email margin-top">
          <label for="email">Email<br></label>
          <input type="email" id="email" class="inputSpace" name="email" placeholder="Inserisci l'email">
        </div>

        <div class="password margin-top">
          <label for="password">Password<br></label>
          <input type="password" id="password" class="inputSpace" name="password" placeholder="Inserisci la password">
        </div>

        <div class="loginSignup">
          <input type="submit" class="button" value="Accedi">
        </div>
        <div class="registerLink">
          <span class="regText">Nuovo utente?<br><a href="signup.jsp" class="underlineEffect">Crea un account</a></span>
        </div>
      </form>
    </div>
  </main>

  <%@ include file="footer.jsp"%>
</body>
</html>
