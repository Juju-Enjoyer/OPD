<!DOCTYPE html>

<html >
<head>
    <title>HELLO,EVERYNYAN</title>

</head>
<body>
<h1>Кто ты воин?</h1>
<form action="login" method="post">
    Почта: <input type="email" name="username"><br><br>
    Your password: <input type="password" name="password"><br><br>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <input type="submit" value="Вход"/>
</form>
<a href="registration">Зарегистрироваться</a>
</body>
</html>