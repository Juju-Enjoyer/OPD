<!DOCTYPE>
<html>
<head>
    <title>HELLO,EVERYNYAN</title>
</head>
<body>
<h1>Админам привет, остальным соболезную</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Актив</th>
        <th>Почта</th>
        <th>Роли</th>
        <th>Сменить Роль</th>
    </tr>
    <#list allUsers as u>
        <tr>
            <th>${u.id}</th>
            <th>${u.name}</th>
            <th>${u.surname}</th>
            <th><#if u.active>true<#else>false</#if></th>
            <th>${u.email}</th>
            <th><#list u.roles as role>${role}<#sep>,  </#list> </th>
            <th><a href="/admin/edit/${u.id}">РОЛИ</a></th>
<th>
    <form <#if u.active>action="/admin/ban/${u.id}"<#else>action="/admin/unban/${u.id}"</#if> method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input type="submit" value=<#if u.active>"Кара божья"<#else>"Божье дарование"</#if>>
    </form>
</th>
        </tr>
    <#else >
        <H3>Пустовато, неправдали ?</H3>
    </#list>
</table>


</body>
<html>