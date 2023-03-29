const tabsBtn   = document.querySelectorAll(".tabs__nav-btn");
const tabsItems = document.querySelectorAll(".tabs__item");


switchR.click();
//if (document.querySelector('.tabs__nav-btn') !== null) document.querySelector('.tabs__nav-btn').click();
tabsBtn.forEach(tabListener);
function tabListener(item) {
    item.addEventListener("click", function() {
        let currentBtn = item;
        let tabId = currentBtn.getAttribute("data-tab");
        let currentTab = document.querySelector(tabId);

        if( ! currentBtn.classList.contains('active') ) {
            tabsBtn.forEach(function(item) {
                item.classList.remove('active');
            });

            tabsItems.forEach(function(item) {
                item.classList.remove('active');
            });

            currentBtn.classList.add('active');
            currentTab.classList.add('active');
        }
    });
}
function validationLab(){
    let res = true;
    const lessonName = document.getElementById("lessonNameL");
    const quest = document.getElementById("questL");
    const needWork = document.getElementById("needWork");
    clearError(lessonName);
    clearError(quest);
    clearError(needWork);
    if(lessonName.options[lessonName.selectedIndex].value === ""){
        console.warn("Выберите предмет");
        createError(lessonName,"Выберите предмет");
        res = false;
    }
    if(quest.value === ""){
        console.warn("Введите задание");
        createError(quest,"Введите задание");
        res = false;
    }else if(quest.value.length <= 4){
        console.warn("Не меннее 4 символов");
        createError(quest,"Не меннее 4 символов");
        res = false;
    }
    if(Number(needWork.value) === 0){
        console.warn("Введите кол-во задач");
        createError(needWork,"Введите кол-во задач");
        res = false;
    }else if(Number(needWork.value) < 0 || Number(needWork.value) > 50){
        console.warn("Введите коректное значение (0,50)");
        createError(needWork,"Введите коректное значение (0,50)");
        res = false;
    }

    return res;
}
function validationQuest(){
    let res = true;
    const lessonName = document.getElementById("lessonName");
    const quest = document.getElementById("quest");
    const date = document.getElementById("date");
    const days = document.getElementById("day");
    clearError(lessonName);
    clearError(quest);
    clearError(date);
    clearError(days);
    if(lessonName.options[lessonName.selectedIndex].value === ""){
        console.warn("Выберите предмет")
        createError(lessonName,"Выберите предмет")
        res = false;
    }
    if(quest.value === ""){
        console.warn("Введите задание")
        createError(quest,"Введите задание")
        res = false;
    }else if(quest.value.length <= 4){
        console.warn("Не меннее 4 символов")
        createError(quest,"Не меннее 4 символов")
        res = false;
    }
    if(date.value ===""){
        console.warn("Введите дату здачи")
        createError(date,"Введите дату здачи")
        res = false
    }
    if (switchR.checked){
        if(settingSwitch.options[settingSwitch.selectedIndex].value === "Каждую неделю"){
            if(days.options[days.selectedIndex].value ===""){
                console.warn("Выберите день")
                createError(days,"Выберите день")
                res = false
            }
        }
    }

    return res
}
function createError (node,text){
    console.log(node)
    if(!node.classList.contains('error')) {
        let parent = node.parentNode;
        let errorLabel = document.createElement("label");
        errorLabel.classList.add("error-label");
        errorLabel.textContent = text;
        node.classList.add("error");
        parent.append(errorLabel);
    } else {
        console.log('contains')
    }

}
function clearError(node){
    if(node.classList.contains('error')){
        node.classList.remove("error");
        node.parentNode.querySelector(".error-label").remove();
    }
}