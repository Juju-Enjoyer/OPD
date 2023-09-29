const startButton = document.getElementById("start");
const ColorButton1 = document.getElementById("color1");
const ColorButton2 = document.getElementById("color2");
const ColorButton3 = document.getElementById("color3");
const result = document.getElementById("result");
const resultTime = document.getElementById("resultTime");
const image = document.getElementById("image");
const popa = document.getElementById("popa");
const context = image.getContext("2d");
let startTime;
let endTime;
let answer = [];
let answerTime = [];
let randomNumber= 0;
let results = [];
const saveButton = document.getElementById('save');

function startTest(){
    if (startButton.textContent === 'Начать заново') {
        popa.style.display = "none"
        saveButton.style.display = "none"
    }
    startButton.style.display = "none";
    ColorButton1.style.display = "block";
    ColorButton2.style.display = "block";
    ColorButton3.style.display = "block";
    setTimeout(showImage, Math.floor(Math.random() * 5000) + 1000)

}
function endTest() {
    endTime = Date.now();
    return endTime - startTime;
}
function removeColor(){
    context.fillStyle = "white";

    // Рисование круга
    context.beginPath();
    context.arc(100, 100, 50, 0, 2 * Math.PI);
    context.fill();
    startTime = Date.now();
}
function showImage() {
    image.style.display = 'block';

    // Установка красного цвета заливки
    context.fillStyle = getRandomColor();

    // Рисование круга
    context.beginPath();
    context.arc(100, 100, 50, 0, 2 * Math.PI);
    context.fill();
    startTime = Date.now();
}
function getRandomColor(){
    randomNumber = Math.floor(Math.random() * 3) + 1;
    if(randomNumber===1){
        return "red";
    } else if (randomNumber===2){
        return "blue";
    }else if (randomNumber===3){
        return "greenyellow"
    }
}
function ColorRed(){
    const time = endTest();
    if(randomNumber===1){
        answer.push(true);
        results.push({result: true, time: time});
    }else{
        answer.push(false);
        results.push({result: false, time: time});
    }
    answerTime.push(time);
    removeColor();
    if (answerTime.length === 10) {
        finishTest()
    }
    else {
        startTest();
    }

}
function ColorBlue(){
    const time = endTest();
    if(randomNumber===2){
        answer.push(true);
        results.push({result: true, time: time});
    }else{
        answer.push(false);
        results.push({result: false, time: time});
    }
    answerTime.push(time);
    removeColor();
    if (answerTime.length === 10) {
        finishTest()
    }
    else {
        startTest();
    }

}
function ColorGreen(){
    const time = endTest();
    if(randomNumber===3){
        answer.push(true);
        results.push({result: true, time: time});
    }else{
        answer.push(false);
        results.push({result: false, time: time});
    }
    answerTime.push(time);
    removeColor();
    if (answerTime.length === 10) {
        finishTest()
    }
    else {
        startTest();
    }

}

function finishTest(){
    popa.style.display = 'block'
    const resultList = document.createElement('ul');
    results.forEach((item) => {
        const resultItem = document.createElement('li');
        resultItem.textContent = `Правильно ${item.result} Вы нажали через ${item.time} миллисекунд`;
        resultList.appendChild(resultItem);
    });
    popa.innerHTML = "";
    popa.appendChild(resultList);
    startButton.textContent = 'Начать заново'
    startButton.style.display = "block"
    ColorButton1.style.display = "none"
    ColorButton2.style.display = "none"
    ColorButton3.style.display = "none"
    result.value = answer;
    resultTime.value = answerTime;
    saveButton.style.display = "block"
        answer = [];
        answerTime = [];
        results = [];

}

startButton.addEventListener('click',startTest);
ColorButton1.addEventListener('click',ColorRed);
ColorButton2.addEventListener('click',ColorBlue);
ColorButton3.addEventListener('click',ColorGreen);