<!DOCTYPE HTML>
<html>

<body>

<div>
    <canvas id="testCanvas" width="1070" height="600"></canvas>
    <div>
        <button id="startButton">Начать тест</button>
    </div>
    
    <div>
        <form method="post" action="/AllMight/moveTestHard">
            <div>
                <input id="hit" name="hit" style="display: none">
                <input id="miss" name="miss" style="display: none">
                <input id="click" name="click" style="display: none">
                <input id="time1" name="time1" style="display: none">
                <input id="time2" name="time2" style="display: none">
                <input id="time3" name="time3" style="display: none">
                <button id="save" type="submit" style="display: none">Следующий тест</button>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        </form>
    </div>

    <script>
        const hit = document.getElementById('hit');
        const miss = document.getElementById('miss');
        const click = document.getElementById('click');
        const time1 = document.getElementById('time1');
        const time2 = document.getElementById('time2');
        const time3 = document.getElementById('time3');
        let hitScore = [];
        let missScore = [];
        let clickScore = [];

        const save = document.getElementById('save');
        const startButton = document.getElementById("startButton");
        const canvas = document.getElementById('testCanvas');
        const ctx = canvas.getContext('2d');

        let testRunning = false;
        let presetTime = 30000;
        let speed = 0.025 ;
        let acceleration = 0.005;
        let maxspeed = 0.5;

        let circles = [
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
            },
            {
                centerX: canvas.width / 2,
                centerY: canvas.height / 2,
                radius: 100,
                angle: 0,
                targetAngle: Math.PI * 1.5,
                speed: speed,
                hits: 0,
                misses: 0,
                clicks: 0,
                reactionTimes: [],
                hitThreshold: maxspeed,
                targetSetTime: Date.now(),
            },
            {
                centerX: (3 * canvas.width) / 4,
                centerY: canvas.height / 2,
                radius: 100,
                angle: 0,
                targetAngle: Math.PI * 2,
                speed: speed * 2,
                hits: 0,
                misses: 0,
                clicks: 0,
                reactionTimes: [],
                hitThreshold: maxspeed,
                targetSetTime: Date.now(),
            },
        ];

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
            startButton.style.display = 'none';
            if (startButton.textContent === "Рестарт") {
                restartTest();
            } else {
                circles.forEach((circle)=>{
                    circle.targetSetTime = Date.now()
                })
                testRunning = true;
                let startTime = Date.now();

                let testInterval = setInterval(() => {
                    if (!testRunning) {
                        clearInterval(testInterval);
                    } else {
                        ctx.clearRect(0, 0, canvas.width, canvas.height);
                        circles.forEach((circle) => {
                            circle.angle += circle.speed;
                            if (circle.angle > 2 * Math.PI) {
                                circle.angle -= 2 * Math.PI;
                            }
                            drawCircle(circle);
                        });
                    }

                    if (Date.now() - startTime >= presetTime) {
                        testRunning = false;
                        finishTest();
                    }
                }, 10);
            }
        }

        document.getElementById('startButton').addEventListener('click', startTest);

        window.addEventListener('keydown', function (event) {
            const circleKey = event.key;
            if (circleKey >= '1' && circleKey <= '3') {
                let circleIndex = parseInt(circleKey) - 1;
                let circle = circles[circleIndex];
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
            }
        });

        function finishTest(){
            startButton.style.display = 'none';
            save.style.display = 'block';
            circles.forEach((circle)=>{
                hitScore.push(circle.hits);
                missScore.push(circle.misses);
                clickScore.push(circle.clicks);
            });
            console.log(hitScore,missScore,clickScore)
            hit.value = hitScore;
            miss.value = missScore;
            click.value = clickScore;
            time1.value = circles[0].reactionTimes;
            time2.value = circles[1].reactionTimes;
            time3.value = circles[2].reactionTimes;

        }

        function restartTest(){
            startButton.style.display = 'block';
            startButton.textContent = "Начать тест";
            save.style.display = 'none';

            circles=[{
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
            },
                {
                    centerX: canvas.width / 2,
                    centerY: canvas.height / 2,
                    radius: 100,
                    angle: 0,
                    targetAngle: Math.PI * 1.5,
                    speed: speed,
                    hits: 0,
                    misses: 0,
                    clicks: 0,
                    reactionTimes: [],
                    hitThreshold: maxspeed,
                    targetSetTime: Date.now(),
                },
                {
                    centerX: (3 * canvas.width) / 4,
                    centerY: canvas.height / 2,
                    radius: 100,
                    angle: 0,
                    targetAngle: Math.PI * 2,
                    speed: speed * 2,
                    hits: 0,
                    misses: 0,
                    clicks: 0,
                    reactionTimes: [],
                    hitThreshold: maxspeed,
                    targetSetTime: Date.now(),
                }];
            hitScore = [];
            missScore = [];
            clickScore = [];
        }
        function saveResults(){
            startButton.style.display = 'block';
            save.style.display = 'none';
            startButton.textContent = 'Начать тест';
            hitButton.style.display = 'none';
            circles.forEach((circle)=>{
                circle.angle = 0;
                circle.targetAngle = Math.PI;
            })
        }
        circles.forEach((circle)=>{
            drawCircle(circle);
        })
    </script>
</div>

</body>


</html>