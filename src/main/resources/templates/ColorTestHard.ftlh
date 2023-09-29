<body>
<h1>ColorTestHard</h1>
<button id="start">Start</button>
<canvas id="image" width="200" height="200"></canvas>

<button id="color1" style="background: red ; display: none">RED</button>
<button id="color2" style="background: blue ; display: none">BLUE</button>
<button id="color3" style="background: greenyellow ; display: none">GREEN</button>
<p id="popa"></p>
<form method="post" action="/TestLobby/ColorTestHard/saveResult">
    <div id="content">
        <input id="result" name="result" style="display: none">
        <input id="resultTime" name="resultTime" style="display: none">
        <input name="testId" value="4" style="display: none">
        <button id="save" style="display: none">Сохранить</button>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <script src="../static/ColorTestHard.js"></script>
</form>
<form method="get" action="/TestLobby">
    <div>
        <button type="submit">К ТестЛобби </button>
    </div>

</form>
<#list results as result>
    <p>айди теста = ${result.testId} рейтинг теста = ${result.test_pass_rate} кто проходил ${result.user.email}</p>
</#list>
</body>