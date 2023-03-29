const settingSwitch = document.getElementById("settingSwitch");
const divSelectDay = document.getElementById("divSelectDay");
const quest = document.getElementById("quest");

settingSwitch.addEventListener("click",hideSelectorDay)

hideSelectorDay();
function hideSelectorDay() {
    let value = settingSwitch.options[settingSwitch.selectedIndex].value;
    if(value !== "Каждый день"){
        divSelectDay.classList.remove('active');
    }else {
        divSelectDay.classList.add('active');
    }
}

