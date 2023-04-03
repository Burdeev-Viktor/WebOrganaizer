const settingSwitch = document.getElementById("settingSwitch");
const divSelectDay = document.getElementById("divSelectDay");
const switchRUpdate = document.getElementById("switchR-update");
const aNeedWork = document.getElementById("aNeedWork");
const needWork = document.getElementById("needWork");
aNeedWork.addEventListener("click", function (){
    let type = needWork.getAttribute("type");
    const divNeedWork = document.getElementById("divNeedWork");
    if(type === "number"){
        divNeedWork.classList.add("active");
        needWork.setAttribute("type","hidden");
    } else {
        divNeedWork.classList.remove("active");
        needWork.setAttribute("type","number");
    }
})



settingSwitch.addEventListener("click",hideSelectorDay);
switchRUpdate.addEventListener("click", function (){
    let divSettingSwitch = document.getElementById("divSettingSwitch");
    let divSelectDay = document.getElementById("divSelectDay");
    let divTime = document.getElementById("divTime");
    if(switchRUpdate)
        if(!switchRUpdate.checked){
            divSettingSwitch.classList.add('active');
            divSelectDay.classList.add('active');
            divTime.classList.add('active');
        }else {
            divSettingSwitch.classList.remove('active');
            divSelectDay.classList.remove('active');
            divTime.classList.remove('active');
        }
})

hideSelectorDay();
function validationRemLabUpdate(){
    const quest = document.getElementById("questL");
    let res = true;
    if(quest.value === ""){
        createError(quest,"Введите задание");
        res = false;
    }else if(quest.value.length <= 4){
        createError(quest,"Не меннее 4 символов");
        res = false;
    }
    if(needWork.value === ""){
        createError(needWork,"Поле не должно быть пустым");
        res = false;
    }else if(needWork.value < 0){
        createError(needWork,"Не может быть отрицательным");
        res = false;
    }else if(needWork.value > 20){
        createError(needWork,"Не болие 20");
        res = false;
    }

    return res;
}
function validationRemUpdate(){
    const quest = document.getElementById("quest");
    const date = document.getElementById("date");
    clearError(date);
    clearError(quest);
    let res = true;
    if(quest.value === ""){
        console.warn("Введите задание");
        createError(quest,"Введите задание");
        res = false;
    }else if(quest.value.length <= 4){
        console.warn("Не меннее 4 символов");
        createError(quest,"Не меннее 4 символов");
        res = false;
    }
    if(date.value ===""){
        console.warn("Введите дату здачи")
        createError(date,"Введите дату здачи")
        res = false
    }
    return res;
}
function hideSelectorDay() {
    let value = settingSwitch.options[settingSwitch.selectedIndex].value;
    if(value !== "Каждый день"){
        divSelectDay.classList.remove('active');
    }else {
        divSelectDay.classList.add('active');
    }
}
function createError (node,text){

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

