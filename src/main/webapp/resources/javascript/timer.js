let timer = null;
let timerRunning = false;
let elapsedTime = 0;

function startTimer() {
    if (!timerRunning) {
        timerRunning = true;
        timer = setInterval(function() {
            elapsedTime++;
            let hours = Math.floor(elapsedTime / 3600);
            let minutes = Math.floor((elapsedTime % 3600) / 60);
            let seconds = elapsedTime % 60;
            document.getElementById("time").innerHTML = hours + ":" + minutes + ":" + seconds;
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
            document.getElementById("time").innerHTML = "0:0:0";
        }
    } else {
        startTimer();
    }
}