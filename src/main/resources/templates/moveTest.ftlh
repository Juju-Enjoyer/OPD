<!DOCTYPE HTML>
<html>

<body>

<div>
    <canvas id="testCanvas" width="1070" height="600"></canvas>
        <div>
            <button id="startButton">Начать тест</button>
        </div>
        <div left="267.5px">
            <button id="aaaaaWOMAAAAAN" style="display: none" >Клик</button>
        </div>
        <div>
            <form method="post" action="/TestLobby/moveTest/saveResult">
                <div>
                <input id="hit" name="hit" style="display: none">
                <input id="miss" name="miss" style="display: none">
                <input id="click" name="click" style="display: none">
                <input id="time" name="time" style="display: none">
                <button id="save" type="submit" style="display: none">Сохранить</button>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            </form>
        </div>
<p id="info"> </p>
    <script>
        const info = document.getElementById('info')
        const hit = document.getElementById('hit');
        const miss = document.getElementById('miss');
        const click = document.getElementById('click');
        const time = document.getElementById('time');


        const save = document.getElementById('save');
        const startButton = document.getElementById("startButton");
        const hitButton = document.getElementById("aaaaaWOMAAAAAN");
        const canvas = document.getElementById("testCanvas");
        const ctx = canvas.getContext("2d");


        let testRunning = false;
        let presetTime = 30000 ;
        let speed = 0.030 ;
        let acceleration = 0.005;
        let maxspeed = 0.5;

        let circle =
            {
                centerX: canvas.width / 4,
                centerY: canvas.height / 2,
                radius: 100,
                angle: 0,
                targetAngle: Math.PI,
                speed: speed / 2,
                hits: 0,
                misses: 0,
                clicks: 0,
                reactionTimes: [],
                hitThreshold: maxspeed,
                targetSetTime: Date.now(),
            };
        function drawCircle(circle) {
            let x = circle.centerX + circle.radius * Math.cos(circle.angle);
            let y = circle.centerY + circle.radius * Math.sin(circle.angle);
            let targetX = circle.centerX + circle.radius * Math.cos(circle.targetAngle);
            let targetY = circle.centerY + circle.radius * Math.sin(circle.targetAngle);

            ctx.beginPath();
            ctx.arc(circle.centerX, circle.centerY, circle.radius, 0, 2 * Math.PI);
            ctx.strokeStyle = 'blue';
            ctx.stroke();
            ctx.closePath();

            ctx.beginPath();
            ctx.arc(x, y, 20, 0, 2 * Math.PI);
            ctx.fillStyle = 'black';
            ctx.fill();
            ctx.closePath();

            ctx.beginPath();
            ctx.arc(targetX, targetY, 20, 0, 2 * Math.PI);
            ctx.fillStyle = 'red';
            ctx.fill();
            ctx.closePath();
        }
        function startTest() {
            setInterval(function (){
                info.textContent = info.textContent + '\\\n'+ "попадания: " + circle.hits + " клики: " + circle.clicks;
            },60000)
            hitButton.style.display = "block";
            startButton.style.display = 'none';
            if (startButton.textContent === "Рестарт") {
                restartTest();
            } else {
                circle.targetSetTime = Date.now();
                testRunning = true;
                let startTime = Date.now();

                let testInterval = setInterval(() => {
                    if (!testRunning) {
                        clearInterval(testInterval);
                    } else {
                        ctx.clearRect(0, 0, canvas.width, canvas.height);
                        circle.angle += circle.speed;
                        if (circle.angle > 2 * Math.PI) {
                            circle.angle -= 2 * Math.PI;
                        }
                        drawCircle(circle);
                    }


                    if (Date.now() - startTime >= presetTime) {
                        testRunning = false;
                        finishTest();

                    }

                }, 10);
            }
        }
        function finishTest(){
            startButton.style.display = 'Block';
            save.style.display = 'block';
            startButton.textContent = 'Рестарт';
            hitButton.style.display = 'none';
            hit.value = circle.hits;
            miss.value = circle.misses;
            click.value = circle.clicks;
            time.value = circle.reactionTimes;


        }
        function restartTest(){
            startButton.style.display = 'block';
            startButton.textContent = "Начать тест";
            save.style.display = 'none';
            hitButton.style.display = 'none';
                circle.angle = 0;
                circle.targetAngle = Math.PI;
                circle.speed = speed / 2;
                circle.hits = 0;
                circle.misses = 0;
                circle.clicks = 0;
                circle.reactionTimes = [];
                circle.hitThreshold= maxspeed;
                circle.targetSetTime = Date.now();
        }
        function saveResults(){
            startButton.style.display = 'block';
            save.style.display = 'none';
            startButton.textContent = 'Начать тест';
            hitButton.style.display = 'none';
            circle.angle = 0;
            circle.targetAngle = Math.PI;
        }


            startButton.addEventListener('click',startTest);

        hitButton.addEventListener('click',function(){
            let reactionTime = Date.now() - circle.targetSetTime;
            circle.reactionTimes.push(reactionTime);
            circle.clicks++;
            if (Math.abs(circle.angle - circle.targetAngle) > circle.hitThreshold) {
                circle.misses++;
            } else {
                circle.hits++;
                circle.targetAngle = Math.random() * 2 * Math.PI;
                circle.targetSetTime = Date.now();

                circle.speed += acceleration;
                circle.speed = Math.min(circle.speed, maxspeed);
            }
        })
        save.addEventListener('click',saveResults)

        drawCircle(circle);


    </script>
</div>

</body>


</html>