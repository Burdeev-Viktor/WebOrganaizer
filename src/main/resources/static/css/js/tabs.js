console.log("hi from js");
const tabsBtn   = document.querySelectorAll(".tabs__nav-btn");
const tabsItems = document.querySelectorAll(".tabs__item");
const switchR = document.getElementById("switchR");
const settingSwitch = document.getElementById("settingSwitch");
const formQuest = document.getElementById("formQuest");

// formQuest.addEventListener("onsubmit",function (){
//     console.log("fail");
//
// })
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
        console.log("Выберите предмет")
        createError(lessonName,"Выберите предмет")
        res = false;
    }
    if(quest.value === ""){
        console.log("Введите задание")
        createError(quest,"Введите задание")
        res = false;
    }else if(quest.value.length <= 4){
        console.log("Не меннее 4 символов")
        createError(quest,"Не меннее 4 символов")
        res = false;
    }
    if(date.value ===""){
        console.log("Введите дату здачи")
        createError(date,"Введите дату здачи")
        res = false
    }
    if (switchR.checked){
        if(settingSwitch.options[settingSwitch.selectedIndex].value === "Каждую неделю"){
            if(days.options[days.selectedIndex].value ===""){
                console.log("Выберите день")
                createError(days,"Выберите день")
                res = false
            }
        }
    }

    return res
}


switchR.click();
tabsBtn.forEach(tabListener);
settingSwitch.addEventListener("click",settingSwitchListener);
switchR.addEventListener("click",switchRListener);
settingSwitchListener();
function switchRListener(){
    let hide3 = document.getElementById("hide3");
    let hide2 = document.getElementById("hide2");
    let hide1 = document.getElementById("hide1");
    if(!switchR.checked){
        hide1.classList.add('active');
        hide2.classList.add('active');
        hide3.classList.add('active');
    }else {
        hide1.classList.remove('active');
        hide2.classList.remove('active');
        hide3.classList.remove('active');
    }
}
function settingSwitchListener (){
    const value = settingSwitch.options[settingSwitch.selectedIndex].value;
    let hide2 = document.getElementById("hide2");
    if(value !== "Каждый день"){
        hide2.classList.remove('active');
    }else {
        hide2.classList.add('active');
    }
}

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
document.querySelector('.tabs__nav-btn').click();

