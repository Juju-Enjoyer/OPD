<body>

<h1>Number Test</h1>

<p id="a"></p>
<p id="b"></p>
<p id="popa"></p>

<button id="start">Начать тест</button>
<button id="chet" style="display: none">Сумма четная</button>
<button id="neChet" style="display: none">Сумма нечетная</button>
<form method="post" action="/TestLobby/NumberTest/saveResult">
    <div>
        <input id="result" name="result" style="display: none">
        <input id="resultTime" name="resTime" style="display: none">
        <button id="save" style="display: none">Сохранить результат</button>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <script src="../static/testNumber.js"></script>
</form>
<form method="get" action="/TestLobby">
    <div>
        <button type="submit">К ТестЛобби </button>
    </div>

</form>
<#list results as result>
    <p>айди теста = ${result.testId} рейтинг теста = ${result.test_pass_rate} кто проходил${result.user.email}</p>
</#list>
</body>
