const image = document.getElementById("image");
const stopButton = document.getElementById('stop');
const startButton = document.getElementById('start-test');
const continueButton = document.getElementById("continuation")
const result = document.getElementById('result');
const testContainer = document.getElementById('test-container');
const saveButton = document.getElementById('save-results');
const res = document.getElementById("res")
let startTime
let results = [];
let finalRes = [];
function getRandomColor() {
    const letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}
function showImage() {
    image.style.display = 'block';
    const context = image.getContext("2d");

    // Установка красного цвета заливки
    context.fillStyle = getRandomColor();

    // Рисование круга
    context.beginPath();
    context.arc(100, 100, 50, 0, 2 * Math.PI);
    context.fill();
    startTime = Date.now();
}

function hideImage() {
    image.style.display = 'none';
    const endTime = Date.now();
    return endTime - startTime;
}

function handleClick() {
    if (image.style.display !== 'none') {
        const time = hideImage();
        results.push(time);
        stopButton.style.display = "none";
        startButton.style.display = "none";
        continueButton.style.display = "block";
        result.style.display = "block"
        result.textContent = `Вы нажали через ${time} миллисекунд`;
    } else {
        alert("Подождите появления изображения");
    }
}

function continueFunction() {
    result.style.display = "none";
    if (results.length !== 10) {
        startTest();
    } else if (results.length === 10) {
        continueButton.style.display = "none";
        finishTest();
    }
}

function startTest() {
    testContainer.style.display = 'block';
    startButton.style.display = 'none';
    continueButton.style.display = 'none';
    stopButton.style.display = 'block';
    saveButton.style.display = 'none';
    const delay =Math.floor(Math.random() * 5000) + 1000;
    setTimeout(() => {
        showImage();
    }, delay);
}

function finishTest() {
    result.style.display = "block";
    const resultList = document.createElement('ul');
    results.forEach((time) => {
        const resultItem = document.createElement('li');
        resultItem.textContent = `Вы нажали через ${time} миллисекунд`;
        resultList.appendChild(resultItem);
    });
    result.innerHTML = ""
    result.appendChild(resultList);
    finalRes = results;
    startButton.style.display = 'block';
    startButton.textContent = 'Начать заново';
    if (startButton.textContent === 'Начать заново') {
        results = [];
    }
    saveButton.style.display = 'block';
    saveButton.addEventListener('click', saveResults);
}

function saveResults() {
    res.value = JSON.stringify(finalRes);
    saveButton.style.display = "none";
    startButton.textContent = "Начать тест";
    result.style.display = "none";
}

continueButton.addEventListener('click', continueFunction);
stopButton.addEventListener('click', handleClick);
startButton.addEventListener('click', startTest);