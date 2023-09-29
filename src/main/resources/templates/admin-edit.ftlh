<H1>Смена ролей для ${user.email}</H1>
<form action="/admin/edit" method="post">
    <#list roles as role>
        <div>
            <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}> ${role}</label>
        </div>
    </#list>
    <INPUT type="hidden" value="${user.id}" name="userId">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <button type="submit">Сохранить</button>
</form>