<html>
<head>
    <title>HELLO, EVERYNYAN</title>
    <meta charset="utf-8" />

</head>
<body>

<img id="image" src="" alt="Фотография" />
<p id="caption"></p>
<p id="maybeU"> </p>
<form action="/" method="get">
    <button type="submit">Возрат к истокам</button>
</form>
<script>
    window.onload = function() {
        var prof = "${prof}";
        let feb = prof[0];
        let soft = prof[1]
        let sis = prof[2];
        if (feb > soft && feb>sis) {
            document.getElementById("image").src = "feb.jpg";
            document.getElementById("caption").textContent = "Веб-дизайн - отрасль веб-разработки и разновидность дизайна, в задачи которой входит проектирование пользовательских веб-интерфейсов для сайтов или веб-приложений.Веб-дизайнеры:проектируют логическую структуру веб-страниц;продумывают наиболее удобные решения подачи информации;занимаются художественным оформлением веб-проекта.";
            document.getElementById('maybeU').textContent ='Разработчиков программного обеспечения '+ soft + '\n' + "Системный администратор " + sis ;

        } else if (soft>feb && soft> sis) {
            document.getElementById("image").src = "soft.jpg";
            document.getElementById("caption").textContent = "Разработчиков программного обеспечения - это создание функциональных приложений и приложений для веб-сайтов, компьютеров, смартфонов и других электронных устройств. В зависимости от задачи, используются разные языки программирования, в основном это Python, Java, JavaScript.";
            document.getElementById('maybeU').textContent = "Веб-дизайн "+ feb + '\n' + "Системный администратор " + sis ;
        } else if (sis>feb &&sis>soft) {
            document.getElementById("image").src = "sis.jpg";
            document.getElementById("caption").textContent = "Системный администратор - должностные обязанности которого подразумевают обеспечение штатной работы парка компьютерной техники, сети и программного обеспечения.";
            document.getElementById('maybeU').textContent = "Веб-дизайн "+ feb + '\n' + 'Разработчиков программного обеспечения '+ soft;
        }
    };
</script>
</body>
</html>