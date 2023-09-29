<html>
<head>
    <title>HELLO,EVERYNYAN</title>

</head>
<body>

<div class="navi">
    <h1> <#if user.email ??>
        <form action="/logout" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input type="submit" value="выход">
        </form>
        <#else >
        <a href="login"> Войти</a></h1>
    <hr>
    </#if>
    <form action="/TestLobby" method="get">
        <button type="submit"><h1>Тестирование</h1></button>
    </form>

</div>
<#list blocks as block>
    <div>
        <p>${block.title} - ${block.description} | <a href="/block/info/${block.id}">Подробнее о специалисте...</a></p>
    </div>
</#list>

<form action="AllMight" method="get">
    <button type="submit"><h1>UltraTest</h1></button>
</form>


</body>
</html>