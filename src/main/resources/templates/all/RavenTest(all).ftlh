
<html>
<head>
    <title>HELLO,EVERYNYAN</title>
    <meta charset="utf-8" />
</head>
<body>
<img id="puzzle-image" style="display: none" src="">
<div id="choices-container" style="display: none"></div>
<div id="timer" style="display: none"></div>
<div>
    <button id="start">Начать Тест</button>
</div>
<div>
    <button id="restart" style="display: none">Рестарт</button>
</div>
<div>
    <form method="post" action="/AllMight/RavenTest">
        <div>
            <input id="aRes" name="aRes" style="display: none">
            <input id="bRes" name="bRes" style="display: none">
            <input id="cRes" name="cRes" style="display: none">
            <input id="dRes" name="dRes" style="display: none">
            <input id="eRes" name="eRes" style="display: none">
            <button id="save" type="submit" style="display: none">Следующий тест</button>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

    </form>
</div>
<div>

</div>
<script>
    const questions = [
        {
            image: "../static/photoRaven/a1(q).jpg",
            choices: [
                { image: "../static/photoRaven/a1(a1).jpg", answer: false },
                { image: "../static/photoRaven/a1(a2).jpg", answer: false },
                { image: "../static/photoRaven/a1(a3).jpg", answer: false },
                { image: "../static/photoRaven/a1(a4).jpg", answer: true },
                { image: "../static/photoRaven/a1(a5).jpg", answer: false },
                { image: "../static/photoRaven/a1(a6).jpg", answer: false }
            ],
            type: "A"
        },
        {
            image: "../static/photoRaven/a2(q).jpg",
            choices: [
                { image: "../static/photoRaven/a1(a1).jpg", answer: true },
                { image: "../static/photoRaven/a1(a2).jpg", answer: false },
                { image: "../static/photoRaven/a1(a3).jpg", answer: false },
                { image: "../static/photoRaven/a1(a4).jpg", answer: false },
                { image: "../static/photoRaven/a1(a5).jpg", answer: false },
                { image: "../static/photoRaven/a1(a6).jpg", answer: false }
            ],
            type: "A"
        }]
    const start = document.getElementById('start');
    const restart = document.getElementById('restart');
    const save = document.getElementById('save');

    const aRes = document.getElementById('aRes');
    const bRes = document.getElementById('bRes');
    const cRes = document.getElementById('cRes');
    const dRes = document.getElementById('dRes');
    const eRes = document.getElementById('eRes');

    const puzzleImage = document.querySelector("#puzzle-image");
    const choicesContainer = document.querySelector("#choices-container");
    let intervalId;

    let startTime = 0;
    let a = [];
    let b = [];
    let c = [];
    let d = [];
    let e = [];


    function startTimer(duration, display) {
        var timer = duration, minutes, seconds;
        intervalId = setInterval(function () {
            minutes = parseInt(timer / 60, 10);
            seconds = parseInt(timer % 60, 10);

            minutes = minutes < 10 ? "0" + minutes : minutes;
            seconds = seconds < 10 ? "0" + seconds : seconds;

            display.textContent = minutes + ":" + seconds;

            if (--timer < 0) {
                timer = duration;clearInterval(intervalId);
                display.textContent = "Таймер завершился!";
                finishTest();

            }


        }, 1000);

    }

    /*window.onload = function () {
        var twentyMinutes = 60 * 20,
            display = document.querySelector('#timer');
        startTimer(twentyMinutes, display);
    };*/


    function startTest() {
        shuffleArray(questions);
        start.style.display = 'none';
        puzzleImage.style.display = 'block';
        choicesContainer.style.display = 'block';
        timer.style.display = 'block'
        startTime = Date.now();
        startTimer(1*60, document.querySelector('#timer'));
        showNextQuestion();
    }

    function showQuestion(question) {
        // Показать главное изображение
        puzzleImage.src = question.image;

        // Очистить контейнер с вариантами ответов
        choicesContainer.innerHTML = "";
        shuffleArray(question.choices);
        // Добавить варианты ответов в контейнер
        question.choices.forEach((choice) => {
            const choiceImage = document.createElement("img");
            choiceImage.src = choice.image;
            choiceImage.addEventListener("click", () => {
                if (choice.answer) {
                    if(question.type === "A"){
                        a.push(1);
                    }
                    else if(question.type === "B"){
                        b.push(1);
                    }
                    else if(question.type === "C"){
                        c.push(1);
                    }
                    else if(question.type === "D"){
                        d.push(1);
                    }
                    else if(question.type === "E"){
                        e.push(1);
                    }
                    console.log("Правильно!");
                } else {
                    if(question.type === "A"){
                        a.push(0);
                    }
                    else if(question.type === "B"){
                        b.push(0);
                    }
                    else if(question.type === "C"){
                        c.push(0);
                    }
                    else if(question.type === "D"){
                        d.push(0);
                    }
                    else if(question.type === "E"){
                        e.push(0);
                    }
                    console.log("Неправильно!");
                }
                // Показать следующий вопрос
                showNextQuestion();
            });
            choicesContainer.appendChild(choiceImage);
        });
    }

    function showNextQuestion() {
        // Получить следующий вопрос из массива
        const nextQuestion = questions.shift();

        // Если вопросы закончились, вывести сообщение об окончании игры
        if (!nextQuestion) {
            finishTest();
            return;
        }

        // Показать следующий вопрос
        showQuestion(nextQuestion);
    }

    function finishTest(){
        clearInterval(intervalId);
        if(!questions){
            console.log("Игра окончена!")
        }
        else{
            console.log("не успел")
            noTime(questions)
        }
        restart.style.display = 'none';
        save.style.display = 'block';
        puzzleImage.style.display = 'none';
        choicesContainer.style.display = 'none';



    }
    function shuffleArray(array) {
        for (let i = array.length - 1; i > 0; i--) {
            const j = Math.floor(Math.random() * (i + 1));
            [array[i], array[j]] = [array[j], array[i]];
        }
    }
    function noTime(array) {
        array.forEach(q =>{
            if(q.type === "A"){
                a.push(0);
            }
            else if(q.type === "B"){
                b.push(0);
            }
            else if(q.type === "C"){
                c.push(0);
            }
            else if(q.type === "D"){
                d.push(0);
            }
            else if(q.type === "E"){
                e.push(0);
            }
        })

    }
    function saveTest() {
        aRes.value = a;
        bRes.value = b;
        cRes.value = c;
        dRes.value = d;
        eRes.value = e;

        setTimeout(location.reload(),100);

    }

    save.addEventListener('click',saveTest);
    start.addEventListener("click", startTest);
    restart.addEventListener('click', function() {
        location.reload();
    });
</script>
</body>
</html>