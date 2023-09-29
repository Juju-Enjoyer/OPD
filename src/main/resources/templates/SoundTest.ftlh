<body>
<h1>Sound Test</h1>

<button id="start-test">Начать тест</button>
<button id="stop" style="display: none;">Стоп</button>
<button id="continuation" style="display: none">Следующая попытка</button>
<div id="test-container" style="display: none;">
    <audio id="sound" src="../static/turkish-march-mozart-rondo-alla-turca.mp3"></audio>
    <p id="result" ></p>
</div>
<form method="post" action="/TestLobby/SoundTest/saveResult" >
    <div>
        <input name="result" style="display: none" id="res" value="">
        <input name="testId" style="display: none" value="1">
        <button id="save-results" type="submit"  style="display: none;">Сохранить</button>
    </div>
    <script src="../static/newST.js"></script>
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