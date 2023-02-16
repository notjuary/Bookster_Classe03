<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel = "icon" href="resources/img/Book.svg" type="image/x-icon">
  <title>Bookster: Sessione</title>

  <link rel="stylesheet" href="style/style.css">
  <link rel="stylesheet" href="style/sessione.css">
</head>
<body>

<%@ include file="header.jsp"%>

<div class="path">
  <a href="index.jsp" class="underlineEffect underlineEffect--pp">Home</a>
  <span class="material-symbols-outlined">chevron_right</span>
  <p style="color: var(--color-900); font-weight: 500">Sessione</p>
</div>

<div id="current_date"></div>

<main>
  <div class="section--timer">
    <div id="time">Premi start!</div>
    <div class="button--control">
      <div class="start" onclick="startTimer()"><span class="material-symbols-outlined">play_circle</span><span >Start</span></div>
      <div class="stop" onclick="stopTimer()"><span class="material-symbols-outlined">block</span><span>Stop</span></div>
      <div class="pause" onclick="pauseTimer()"><span class="material-symbols-outlined">pause_circle</span><span>Pause</span></div>
    </div>
  </div>
</main>

<%@ include file="footer.jsp"%>

</body>
<script src="resources/javascript/timer.js"></script>
<script>
  date = new Date();
  year = date.getFullYear();
  month = date.getMonth() + 1;
  day = date.getDate();
  document.getElementById("current_date").innerHTML = month + "/" + day + "/" + year;
</script>
</html>
