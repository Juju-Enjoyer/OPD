<html>
<head>
    <title>HELLO,EVERYNYAN</title>
    <meta charset="utf-8"/>
    <style>    input[type="number"] {
            width: 50px
        }
    </style>
</head>
<body>

<div id="memory-test"></div>
<button id="start">Начать тест</button>
<button id="restart" style="display: none">Рестарт</button>
<form method="post" action="/AllMight/MemoryTest">
    <input id="wrong" name="ideal" style="display: none">
    <input id="misplace" name="misplace" style="display: none">
    <button id="save" type="submit" style="display: none">Подвести Итог</button>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>
<script>
    const restart = document.getElementById('restart');
    const save = document.getElementById('save');
    const wrong = document.getElementById('wrong');
    const misplace = document.getElementById('misplace');
    const start = document.getElementById('start');
    start.addEventListener('click', startTest)
    let missList = []
    let correctButMisplace = []
    var level = 1;
    var sequence = [];
    var userInput = [];
    var timerDuration = 10;
    var timerId = null;

    function startTest() {
        start.style.display = 'none';
        sequence = [];
        userInput = [];
        timerDuration = 10;
        level = 1;
        showSequence();
        startTimer();
    }

    function showSequence() {
        var sequenceContainer = document.getElementById("memory-test");
        sequenceContainer.innerHTML = "";
        var numbers = [];
        for (var i = 0; i < level; i++) {
            var number = Math.floor(Math.random() * 10);
            sequence.push(number);
            numbers.push(number);
        }
        var intervalId = setInterval(function () {
            if (sequenceContainer.innerText === "") {
                sequenceContainer.innerText = numbers.join(" ");
            } else {
                sequenceContainer.innerText = "";
            }
        }, 500);
        setTimeout(function () {
            clearInterval(intervalId);
            sequenceContainer.innerText = "";


        }, timerDuration * 500);
        timerDuration += 1;
    }

    function hideSequence(sequenceContainer) {
        sequenceContainer.innerText = "";
        setTimeout(showUserInput, 200);
    }

    function startTimer() {
        timerId = setTimeout(function () {
            showUserInput();
        }, timerDuration * 500);
    }

    function showUserInput() {
        var inputContainer = document.getElementById("memory-test");
        inputContainer.innerHTML = "";
        for (var i = 0; i < level; i++) {
            var input = document.createElement("input");
            input.type = "number";
            inputContainer.appendChild(input);
            userInput.push(input);
        }
        var submitButton = document.createElement("button");
        submitButton.textContent = "Проверить";
        submitButton.addEventListener("click", checkUserInput);
        inputContainer.appendChild(submitButton);
    }

    function checkUserInput() {
        var correctCount = 0;
        var misplacedCount = 0;
        for (var i = 0; i < level; i++) {
            var input = userInput[i];
            var userInputValue = parseInt(input.value);
            if (userInputValue === sequence[i]) {
                correctCount++;
            } else if (sequence.includes(userInputValue)) {
                misplacedCount++;
            }
            input.disabled = true;
        }
        missList.push(correctCount);
        correctButMisplace.push(misplacedCount);
        console.log(level, correctCount, misplacedCount);
        clearTimeout(timerId);
        if (level === 10) {
            restart.style.display = 'none';
            save.style.display = 'block';
        } else {
            userInput = [];
            sequence = [];
            level++;
            showSequence();
            startTimer();
        }
    }

    function restartTest() {
        location.reload();
    }

    function saveTest() {
        // Логика сохранения теста
        wrong.value = missList;
        misplace.value = correctButMisplace;
        setTimeout(restartTest, 100);
    }

    restart.addEventListener('click', restartTest)
    save.addEventListener('click', saveTest);
</script>
</body>
</html>