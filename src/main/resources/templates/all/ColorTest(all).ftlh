<head>
    <title>HELLO,EVERYNYAN</title>
    <meta charset="utf-8" />
</head>
<body>
<h1>Color Test</h1>

<button id="start-test">Начать тест</button>
<button id="stop" style="display: none;">Стоп</button>
<button id="continuation" style="display: none">Следующая попытка</button>
<div id="test-container" style="display: none;">
    <canvas id="image" width="200" height="200"></canvas>
    <p id="result" ></p>
</div>
<form method="post" action="/AllMight/ColorTest" >
    <div>
        <input name="result" style="display: none" id="res" value="">
        <input name="testId" style="display: none" value="3">
        <button id="save-results" type="submit"  style="display: none;">Следующий тест</button>
    </div>
    <script src="../../static/ColorTest.js"></script>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>

<script>
    setInterval( function checkButtonText() {
        const button = document.getElementById("start-test");
        if (button.textContent==="Начать заново") {
            button.style.display='none';
        }
    },10);
</script>
</body>