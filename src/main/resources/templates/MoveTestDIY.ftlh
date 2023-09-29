
<!DOCTYPE html>
<html>
<head>
    <title>Moving Circles</title>
    <style>
        .circle {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            position: absolute;
            left: 50%;
            top: 40%;

        }

        .circleBlue {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            position: absolute;
            top: 40%;

        }
    </style>
</head>
<body>
<div id="circle1" class="circle" style="background-color: red;"></div>
<div id="circle2" class="circleBlue" style="background-color: blue;"></div>
<button id="startBtn">
    Start
</button>
<button id="stop" style="display: none">
    Stop
</button>
<p id="result"></p>
<p id="popa"></p>
<form action="/TestLobby/MoveTest/saveResult" method="post">
    <div>
        <input id="resultsForSave" name="resultsForSave" style="display: none">
        <input name="testId" style="display: none" value="5">
        <button type="submit" id="save" style="display: none;">Save</button>
    </div>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>
</body>
<script>
    var startBtn = document.getElementById('startBtn');
    var circle2 = document.getElementById("circle2");
    var circle1 = document.getElementById('circle1')
    var stopBtn = document.getElementById('stop');
    var result = document.getElementById('result');
    var save = document.getElementById('save');
    var resultsForSave = document.getElementById('resultsForSave')
    var timer;
    var startTime;
    var stopTime;
    var results = [];
    var popa = document.getElementById('popa');


    function startAnimation() {
        if (startBtn.textContent === 'Начать заново') {
            popa.style.display = 'none';
            save.style.display = 'block';
            popa.innerHTML = "";
            startBtn.textContent = 'Start';
            save.style.display = 'none'
            results=[];
        } else if (startBtn.textContent === 'следующая попытка') {
            startBtn.textContent = 'Start';
        } else {
            startBtn.style.display = 'none';
            stopBtn.style.display = 'block';
            startTime = Date.now(); // запомнить время начала

            timer = setInterval(function () {
                // сколько времени прошло с начала анимации?
                var timePassed = Date.now() - startTime;

                if (timePassed >= 3000) {
                    stopAnimation();
                    return;
                }

                // отрисовать анимацию на момент timePassed, прошедший с начала анимации
                draw(timePassed);
            }, 1);
        }
    }

    function stopAnimation() {
        result.style.display = 'block';
        stopTime = Date.now();
        clearInterval(timer);
        var reactionTime = (stopTime - startTime) - 1370;
        if (reactionTime > 0) {
            result.textContent = 'вы опаздали на ' + reactionTime + 'mc';
            results.push(reactionTime);
        } else {
            result.textContent = 'вы раньше на ' + reactionTime + 'mc';
            results.push(reactionTime);
        }

        if (results.length === 10) {
            startBtn.style.display = 'block';
            startBtn.textContent = 'Начать заново';
            stopBtn.style.display = 'none';
            finishTest();
        } else {
            startBtn.textContent = 'следующая попытка';
            startBtn.style.display = 'block';
            stopBtn.style.display = 'none';
        }
    }

    // в то время как timePassed идёт от 0 до 2000
    // left изменяет значение от 0px до 800px
    function draw(timePassed) {
        circle2.style.left = timePassed / 2 + 'px';
    }

    function finishTest() {
        popa.style.display = 'block'
        result.style.display = 'none'
        const resultList = document.createElement('ul');
        results.forEach((item) => {
            const resultItem = document.createElement('li');
            resultItem.textContent = 'вашва реакция ' + item;
            resultList.appendChild(resultItem);
        });
        popa.appendChild(resultList);
        save.style.display = 'block'
        resultsForSave.value = results;

    }

    startBtn.addEventListener('click', startAnimation);
    stopBtn.addEventListener('click', stopAnimation);
</script>

</html>
