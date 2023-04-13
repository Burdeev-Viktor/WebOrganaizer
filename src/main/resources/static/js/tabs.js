const tabsBtn   = document.querySelectorAll(".tabs-nav-btn");
const tabsItems = document.querySelectorAll(".tabs-item");


switchR.click();
tabsBtn.forEach(tabListener);
function tabListener(item) {
    item.addEventListener("click", function() {
        let tabsContent = document.getElementById('tabs-content');
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
            if(currentBtn.getAttribute('data-tab') === "#lab"){
                tabsContent.classList.remove('task');
                tabsContent.classList.add('lab');
            }else {
                tabsContent.classList.remove('lab');
                tabsContent.classList.add('task');
            }
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
        createError(lessonName,"Выберите предмет");
        res = false;
    }
    if(quest.value === ""){
        createError(quest,"Введите задание");
        res = false;
    }else if(quest.value.length <= 4){
        createError(quest,"Не меннее 4 символов");
        res = false;
    }
    if(Number(needWork.value) === 0){
        createError(needWork,"Введите кол-во задач");
        res = false;
    }else if(Number(needWork.value) < 0 || Number(needWork.value) > 50){
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
        createError(lessonName,"Выберите предмет");
        res = false;
    }
    validateQuestDate(quest,date,res);
    if (switchR.checked){
        if(settingSwitch.options[settingSwitch.selectedIndex].value === "Каждую неделю"){
            if(days.options[days.selectedIndex].value ===""){
                createError(days,"Выберите день");
                res = false;
            }
        }
    }

    return res;
}

