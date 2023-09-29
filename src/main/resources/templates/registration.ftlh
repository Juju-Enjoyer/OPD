<!DOCTYPE html>

<html>
<head>
    <title>HELLO,EVERYNYAN</title>

</head>
<body>
<h1>Регистрация</h1>
<form action="/registration" method="post">
    Ваша имя: <input type="text" name="name"><br><br>
    Ваща фамилия: <input type="text" name="surname"><br><br>
    Почта: <input type="email" name="email"><br><br>
    Your password: <input type="password" name="password"><br><br>
    Расскажите о себе: <input type="text" name="aboutUser"><br><br>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <input type="submit" value="Вход"/>
</form>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>
</body>
</html>