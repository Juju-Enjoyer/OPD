<#--
//начало теста на звук
<div id="ST" <#if resultST?has_content && resultST??>style="display: none;"</#if>>
    <button id="start-test">Начать тест</button>
<button id="stop" style="display: none;">Стоп</button>
<button id="continuation" style="display: none">Следующая попытка</button>
<div id="test-container" style="display: none;">
    <audio id="sound" src="../../static/turkish-march-mozart-rondo-alla-turca.mp3"></audio>
    <p id="result" ></p>
</div>
<form method="post" action="/AllMight/ST" >
    <div>
        <input name="result" style="display: none" id="res" value="">
        <input name="testId" style="display: none" value="1">
        <button id="save-results" type="submit"  style="display: none;">Следующий Тест</button>
    </div>
    <script src="../../static/newST.js"></script>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>
</div>
<div id="Math" >

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


</div>



<script>
     setInterval( function checkButtonText() {
        const button = document.getElementById("start-test");
        if (button.textContent==="Начать заново") {
            button.remove();
        }
    },10);
</script>-->

<div id="SoundTest">
    <form action="/AllMight/SoundTest" method="get">
        <button id="1" type="submit">Первый тест</button>
    </form>

</div>


<script>



</script>