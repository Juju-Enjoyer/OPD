const sound = document.getElementById('sound');
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

function playSound() {
    sound.play();
    startTime = Date.now();
}

function stopSound() {
    sound.pause();
    sound.currentTime = 0;
    const endTime = Date.now();
    return endTime - startTime;
}

function handleClick() {

    if(sound.currentTime!==0){
        const time = stopSound()
        results.push(time);
        stopButton.style.display = "none";
        startButton.style.display = "none";
        continueButton.style.display = "block";
        result.style.display = "block";
        result.textContent = `Вы нажали через ${time} миллисекунд`;
    }
    else {
        alert("Подождите нажала воспроизведения песни");
    }

}
function continueFunction(){
     result.style.display = "none";
if (results.length!==10){
    startTest();
}
else if(results.length === 10 ){
    continueButton.style.display = "none"
    finishTest()
}

}

function startTest() {
    testContainer.style.display = 'block';
    startButton.style.display = 'none';
    continueButton.style.display = 'none'
    stopButton.style.display = 'block';
    const delay = Math.floor(Math.random() * 5000) + 1000;
    setTimeout(() => {
        playSound();
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
    result.appendChild(resultList);
    finalRes = results;
    startButton.style.display = 'block';
    startButton.textContent = 'Начать заново';
    if(startButton.textContent ==='Начать заново' ){
        results=[];
    }
    saveButton.style.display = 'block';
    saveButton.addEventListener('click', saveResults);
}

    function saveResults() {
        res.value = JSON.stringify(finalRes);
        saveButton.style.display = "none"
        startButton.textContent = "Начать тест"
        result.style.display = "none"


}


continueButton.addEventListener('click', continueFunction)
stopButton.addEventListener('click', handleClick);
startButton.addEventListener('click', startTest);