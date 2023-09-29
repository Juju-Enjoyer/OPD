<!DOCTYPE html>

<html>
<head>
    <title>HELLO,EVERYNYAN</title>

</head>
<body>
<b>Разраб: </b>${block.title}<br>
<b>Описание: </b>${block.description}<br>
<h4>держи список ПВК</h4>

<#if (user.roles?size > 1)>

    <form action='/block/info/${block.id}/quality' method="post" modelAttribute="grades">
        <#list pvk as q  >
            <div>
                <input type="checkbox" name="id_quality" value="${q.id}" onchange="updateQualityGrade(this)">${q.name}
                <span id="avg_${q.id}">
            Среднее значение: ${gradeService.getAvgQuality(block.id, q.id)},
                Сколько раз выбрали: ${gradeService.getCountQuality(block.id, q.id)}
            </span>
                <input id="${q.id}"   type="range" min="1" max="10" style="display:none;"/>
            </div>
        </#list>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <button type="submit" value="submit" onclick="count=0;log.info('нажал')" >Отправить</button>
    </form>
    <#else >
        <#list pvk as q >
            <div>
                ${q.name}
                <span id="avg_${q.id}">
            Среднее значение: ${gradeService.getAvgQuality(block.id, q.id)},
                Сколько раз выбрали: ${gradeService.getCountQuality(block.id, q.id)}
            </span>
            </div>
        </#list>
</#if>



    <#list sortGrade as g>
        <div>
            <p>id="${g.id}" id_block="${g.blockId}" id_quality="${g.id_quality}" quality_grade="${g.quality_grade}" user="<#if g.user ??><#if g.user.email??>${g.user.email}</#if></#if>" </p>
        </div>
    </#list>
<form action="/" method="get">

    <button type="submit">Перейти на страницу Example</button>
</form>

</body>

<script>
    var count = 0;
    function updateQualityGrade(checkbox){
        var rangeInput = checkbox.nextElementSibling.nextElementSibling;
        if(checkbox.checked || count<=10){
            count++
            console.log(count)
            console.log(checkbox.value);
            rangeInput.style.display = 'block';
            console.log(rangeInput.style.display)
            rangeInput.name="quality_grade"
            console.log(rangeInput.name);
            rangeInput.addEventListener("change", function() {
                rangeInput.value = this.value;
                console.log(this.value)
            })
            console.log(rangeInput.value)

        }
        else{
            count--
            rangeInput.style.display = "none";
        }
    }


</script>

</html>
