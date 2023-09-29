<head>
    <title>HELLO,EVERYNYAN</title>
    <meta charset="utf-8" />
</head>
<body>

<h1>Number Test</h1>

<p id="a"></p>
<p id="b"></p>
<p id="popa"></p>

<button id="start">Начать тест</button>
<button id="chet" style="display: none">Сумма четная</button>
<button id="neChet" style="display: none">Сумма нечетная</button>
<form method="post" action="/AllMight/NumberTest">
    <div>
        <input id="result" name="result" style="display: none">
        <input id="resultTime" name="resTime" style="display: none">
        <button id="save" style="display: none">Следующий тест</button>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <script src="../../static/testNumber.js"></script>
</form>
<script>
    setInterval( function checkButtonText() {
        const button = document.getElementById("start");
        if (button.textContent==="Начать заново") {
            button.style.display='none';
        }
    },10);
</script>
</body>
