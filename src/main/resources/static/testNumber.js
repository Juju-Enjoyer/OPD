const startButton = document.getElementById("start")
const chetButton = document.getElementById("chet")
const neChetButton = document.getElementById("neChet")
let sum = 0;
let startTime;
let endTime;
let answer = [];
let answerTime = [];
const a = document.getElementById("a");
const b = document.getElementById("b");
const result = document.getElementById("result");
const resTime = document.getElementById("resultTime");
let results = [];
const popa = document.getElementById("popa")
const saveButton = document.getElementById("save")

function startTest() {
    if (startButton.textContent === 'Начать заново') {
        popa.style.display = "none"
        saveButton.style.display = "none"

    }
    startButton.textContent = "Следующая попытка"
    startButton.style.display = "none"
    chetButton.style.display = "block"
    neChetButton.style.display = "block"
    startTime = Date.now();
    randomSum();
}

function randomSum() {
    const aNum = randomNumber();
    a.textContent = aNum;
    const bNum = randomNumber();
    b.textContent = bNum;
    sum = aNum + bNum;
}


function chetButtonFunction() {
    const time = endTest();
    if (sum % 2 === 0) {
        answer.push(true);
        results.push({result: true, time: time});
    } else {
        answer.push(false);
        results.push({result: false, time: time});
    }
    answerTime.push(time);
    if (answerTime.length === 10) {
        finishTest()
    }
    startButton.style.display = "block"
    chetButton.style.display = "none"
    neChetButton.style.display = "none"

}

function neChetButtonFunction() {
    const time = endTest();
    if (sum % 2 === 1) {
        answer.push(true);
        results.push({result: true, time: time});
    } else {
        answer.push(false);
        results.push({result: false, time: time})
    }
    answerTime.push(time);
    if (answerTime.length === 10) {
        finishTest()
    }
    startButton.style.display = "block"
    chetButton.style.display = "none"
    neChetButton.style.display = "none"
}

function randomNumber() {
    return Math.floor(Math.random() * (20 - 1 + 1)) + 1;
}

function endTest() {
    endTime = Date.now();
    return endTime - startTime;
}

function finishTest() {
    popa.style.display = 'block'
    const resultList = document.createElement('ul');
    results.forEach((item) => {
        const resultItem = document.createElement('li');
        resultItem.textContent = `Правильно ${item.result} Вы нажали через ${item.time} миллисекунд`;
        resultList.appendChild(resultItem);
    });
    popa.innerHTML = ''
    popa.appendChild(resultList);
    startButton.style.display = "block"
    chetButton.style.display = "none"
    neChetButton.style.display = "none"
    result.value = answer;
    resTime.value = answerTime;
    saveButton.style.display = "block"
    startButton.textContent = 'Начать заново'
    if (startButton.textContent === 'Начать заново') {
        answer = [];
        answerTime = [];
        results = [];
    }

}

startButton.addEventListener("click", startTest);
chetButton.addEventListener('click', chetButtonFunction);
neChetButton.addEventListener('click', neChetButtonFunction);


