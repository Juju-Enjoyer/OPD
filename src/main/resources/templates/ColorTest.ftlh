<body>
<h1>Color Test</h1>

<button id="start-test">Начать тест</button>
<button id="stop" style="display: none;">Стоп</button>
<button id="continuation" style="display: none">Следующая попытка</button>
<div id="test-container" style="display: none;">
    <canvas id="image" width="200" height="200"></canvas>
    <p id="result" ></p>
</div>
<form method="post" action="/TestLobby/ColorTest/saveResult" >
    <div>
        <input name="result" style="display: none" id="res" value="">
        <input name="testId" style="display: none" value="3">
        <button id="save-results" type="submit"  style="display: none;">Сохранить</button>
    </div>
    <script src="../static/ColorTest.js"></script>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
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