<head>
    <title>HELLO,EVERYNYAN</title>
    <meta charset="utf-8" />
</head>
<body>
<h1>ColorTestHard</h1>
<button id="start">Start</button>
<canvas id="image" width="200" height="200"></canvas>

<button id="color1" style="background: red ; display: none">RED</button>
<button id="color2" style="background: blue ; display: none">BLUE</button>
<button id="color3" style="background: greenyellow ; display: none">GREEN</button>
<p id="popa"></p>
<form method="post" action="/AllMight/ColorTestHard">
    <div id="content">
        <input id="result" name="result" style="display: none">
        <input id="resultTime" name="resultTime" style="display: none">
        <input name="testId" value="4" style="display: none">
        <button id="save" style="display: none">Следующий тест</button>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <script src="../../static/ColorTestHard.js"></script>
</form>

</body>