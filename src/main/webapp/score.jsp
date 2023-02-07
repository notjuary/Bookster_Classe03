<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script>
        let timer = null;
        let timerRunning = false;
        let elapsedTime = 0;

        function startTimer() {
            if (!timerRunning) {
                timerRunning = true;
                timer = setInterval(function() {
                    elapsedTime++;
                    document.getElementById("time").innerHTML = elapsedTime;
                }, 1000);
            }
        }

        function pauseTimer() {
            if (timerRunning) {
                clearInterval(timer);
                timerRunning = false;
            }
        }

        function stopTimer() {
            if (timerRunning) {
                clearInterval(timer);
                timerRunning = false;
            }

            if (confirm("Are you sure you want to stop the timer?")) {
                let pages = parseInt(prompt("Enter the number of pages read:"));
                if (elapsedTime > pages * 10) {
                    alert("True");
                    window.location.href = "ScoreServlet?seconds=" + elapsedTime + "&pages=" + pages;
                } else {
                    alert("False");
                    elapsedTime = 0;
                    document.getElementById("time").innerHTML = elapsedTime;
                }
            } else {
                startTimer();
            }
        }
    </script>
</head>
<body>
<div id="time">0</div>
<button onclick="startTimer()">Start</button>
<button onclick="pauseTimer()">Pause</button>
<button onclick="stopTimer()">Stop</button>
</body>
</html>