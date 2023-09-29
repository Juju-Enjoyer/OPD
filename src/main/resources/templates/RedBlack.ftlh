<html> <head>
    <title>HELLO,EVERYNYAN</title>
    <meta charset="utf-8" />
    <style>
        .row {
            display: flex;
        }

        .cell {
            width: 50px;
            height: 50px;
            border: 1px solid black;
            text-align: center;
            line-height: 50px;
        }

        .black-red {
            background-color: black;
            color: red;
        }

        .red-black {
            background-color: red;
            color: black;
        }
        .green{
            background-color: green;
            color: green;
        }

    </style>
</head>
<body>
<div>
    <button id="start">Начать тест</button>
    <button id="restart" style="display: none">Рестарт</button>
    <form method="post" action="/TestLobby/RedBlackTest/saveResult">
        <input id="time" name="time" style="display: none">
        <input id="misses" name="miss" style="display: none">
        <button id="save" type="submit" style="display: none">Сохранить</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
    <form method="get" action="/TestLobby">
        <div>
            <button type="submit">К ТестЛобби </button>
        </div>

    </form>


</div>


<script>

    const start = document.getElementById('start');
    const restart = document.getElementById('restart');
    const save = document.getElementById('save');
    const time = document.getElementById('time');
    const misses = document.getElementById('misses');
    let result = [];
    let miss = 0;

    const numb = [
        {num: 25, c: 'black'},
        {num: 24, c: 'black'},
        {num: 23, c: 'black'},
        {num: 22, c: 'black'},
        {num: 21, c: 'black'},
        {num: 20, c: 'black'},
        {num: 19, c: 'black'},
        {num: 18, c: 'black'},
        {num: 17, c: 'black'},
        {num: 16, c: 'black'},
        {num: 15, c: 'black'},
        {num: 14, c: 'black'},
        {num: 13, c: 'black'},
        {num: 12, c: 'black'},
        {num: 11, c: 'black'},
        {num: 10, c: 'black'},
        {num: 9, c: 'black'},
        {num: 8, c: 'black'},
        {num: 7, c: 'black'},
        {num: 6, c: 'black'},
        {num: 5, c: 'black'},
        {num: 4, c: 'black'},
        {num: 3, c: 'black'},
        {num: 2, c: 'black'},
        {num: 1, c: 'black'},

        {num: 24, c: 'red'},
        {num: 23, c: 'red'},
        {num: 22, c: 'red'},
        {num: 21, c: 'red'},
        {num: 20, c: 'red'},
        {num: 19, c: 'red'},
        {num: 18, c: 'red'},
        {num: 17, c: 'red'},
        {num: 16, c: 'red'},
        {num: 15, c: 'red'},
        {num: 14, c: 'red'},
        {num: 13, c: 'red'},
        {num: 12, c: 'red'},
        {num: 11, c: 'red'},
        {num: 10, c: 'red'},
        {num: 9, c: 'red'},
        {num: 8, c: 'red'},
        {num: 7, c: 'red'},
        {num: 6, c: 'red'},
        {num: 5, c: 'red'},
        {num: 4, c: 'red'},
        {num: 3, c: 'red'},
        {num: 2, c: 'red'},
        {num: 1, c: 'red'}
    ]


    function startTest(){
        start.style.display = 'none';
        generateTable();
        setTimeout(finishTest, 300000)
    }

    function generateTable() {
        const table = document.createElement("table");
        // копируем массивы, чтобы не изменять оригинал
        shuffleArray(numb);
        let number = 0;
        for (let i = 0; i < 7; i++) {
            const row = document.createElement("tr");
            for (let j = 0; j < 7; j++) {
                const cell = document.createElement("td");
                const numNow = numb[number];
                const cellText = document.createTextNode(numNow.num);
                cell.appendChild(cellText);
                if (numNow.c === 'black') {
                    cell.classList.add('black-red');
                    cell.addEventListener("click", check);

                } else {
                    cell.classList.add('red-black');
                    cell.addEventListener("click", check);
                }
                row.appendChild(cell);
                number++;
            }
            table.appendChild(row);
        }
        document.body.appendChild(table);
    }






    function shuffleArray(array) {
        for (let i = array.length - 1; i > 0; i--) {
            const j = Math.floor(Math.random() * (i + 1));
            [array[i], array[j]] = [array[j], array[i]];
        }
    }


    let oldCellNum = null;
    let oldCellColor = null
    let countCorrectClicks =0;
    let ultraOldColor = null;
    let lastCell = null;
    let oldClick = 0;

    function check(){
        const currentNum = parseInt(this.textContent);
        const currentColor = this.classList.contains('red-black') ? 'red' : 'black';
        if(oldCellNum==null){
            if(currentNum===1 && currentColor==="red"){
                oldCellNum = currentNum;
                oldCellColor = currentColor;
                ultraOldColor = 'red';
                countCorrectClicks++;
                console.log("начинаем с 1 красной");
            }
            else if(currentNum===25 && currentColor==="black"){
                oldCellNum = currentNum;
                oldCellColor = currentColor;
                ultraOldColor = 'black';
                countCorrectClicks++;
                console.log("начинаем с 25 черной");
            }
            else{
                alert("начните либо с 1 красное либо с 25 черное!")
            }
            oldClick = Date.now();
            lastCell = this;
        }else{

            if(ultraOldColor==='red'){
                if(oldCellColor!==currentColor){
                    if(oldCellNum-1+currentNum===25){
                        console.log("cor",oldCellNum,oldCellColor,currentNum,currentColor);
                        oldCellNum = currentNum;
                        oldCellColor = currentColor;
                        countCorrectClicks++
                        console.log(countCorrectClicks);
                        removeCell(lastCell,this);
                        console.log(Date.now() - oldClick);
                        result.push(Date.now()-oldClick);
                        oldClick=Date.now();
                    }
                    else if (oldCellNum-1+currentNum===26){
                        console.log("cor",oldCellNum,oldCellColor,currentNum,currentColor);
                        oldCellNum = currentNum;
                        oldCellColor = currentColor;
                        countCorrectClicks++
                        console.log(countCorrectClicks)
                        removeCell(lastCell,this)
                        console.log(Date.now() - oldClick);
                        result.push(Date.now()-oldClick);
                        oldClick=Date.now();
                    }
                    else{
                        console.log("incor",oldCellNum,oldCellColor,currentNum,currentColor);
                        miss++;
                        return;
                    }
                }
                else if (countCorrectClicks===48 && currentNum === 1 && currentColor==="black"){
                    countCorrectClicks++
                    finishTest();
                }
                else{
                    console.log("incor",oldCellNum,oldCellColor,currentNum,currentColor);
                    miss++;
                    return;
                }
            }
            else {
                if(oldCellColor!==currentColor){
                    if(oldCellNum-1+currentNum===25){
                        console.log("cor",oldCellNum,oldCellColor,currentNum,currentColor);
                        oldCellNum = currentNum;
                        oldCellColor = currentColor;
                        countCorrectClicks++
                        console.log(countCorrectClicks)
                        removeCell(lastCell,this)
                        console.log(Date.now() - oldClick);
                        result.push(Date.now()-oldClick);
                        oldClick=Date.now();
                    }
                    else if (oldCellNum+currentNum===25){
                        console.log("cor",oldCellNum,oldCellColor,currentNum,currentColor);
                        oldCellNum = currentNum;
                        oldCellColor = currentColor;
                        countCorrectClicks++
                        console.log(countCorrectClicks)
                        removeCell(lastCell,this)
                        console.log(Date.now() - oldClick);
                        result.push(Date.now()-oldClick);
                        oldClick=Date.now();
                    }
                    else{
                        console.log("incor",oldCellNum,oldCellColor,currentNum,currentColor);
                        miss++;
                        return;
                    }
                }
                else{
                    console.log("incor",oldCellNum,oldCellColor,currentNum,currentColor);
                    miss++;
                    return;
                }
            }
        }
        if (countCorrectClicks===49){
            finishTest();
        }
    }

    function finishTest(){
        if (countCorrectClicks!==49){
           miss += (49 - countCorrectClicks);
        }
        const table = document. querySelector('table')
        document.body.removeChild(table);
        restart.style.display = 'block';
        save.style.display = 'block';

    }
    function saveTest(){
        misses.value = miss;
        time.value = result;
        setTimeout(location.reload,100)
    }
    function restartTest(){
        location.reload();
    }
    function removeCell(lc, nowCell){

        if(lc!==null){
            if(lc.classList.contains('black-red')){
                lc.classList.remove('black-red');
                lc.classList.add('green');
                nowCell.classList.remove('black-red');
                nowCell.classList.add('green');
            }else if(lc.classList.contains('red-black')){
                lc.classList.remove('black-red');
                lc.classList.add('green');
                nowCell.classList.remove('black-red');
                nowCell.classList.add('green');
            }
            lc.removeEventListener("click", check);
            nowCell.removeEventListener("click", check);
            lastCell = null;
        }
        else{
            lastCell = nowCell;
        }
    }

    /*table.addEventListener("click", function(event) {
        if (event.target.classList.contains("cell")) {
            let number = parseInt(event.target.innerHTML);
            if (number === numbers[currentRow][currentNumber]) {
                currentNumber++;
                if (currentNumber === 25) {
                    currentRow = currentRow === 0 ? 1 : 0;
                    currentNumber = 0;
                    event.target.classList.add("correct");
                    checkForWin();
                }
                generateTable();
            } else {
                errors++;
            }
        }
    });*/

    save.addEventListener('click',saveTest);
    restart.addEventListener('click',restartTest)
    start.addEventListener('click',startTest);
</script>
</body>
</html>
