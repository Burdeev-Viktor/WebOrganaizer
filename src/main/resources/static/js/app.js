const switchR = document.getElementById("switchR");
const settingSwitch = document.getElementById("settingSwitch");
settingSwitch.addEventListener("click",settingSwitchListener);
switchR.addEventListener("click",switchRListener);
settingSwitchListener();
switchRListener();
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
    let value = settingSwitch.options[settingSwitch.selectedIndex].value;
    let hide2 = document.getElementById("hide2");
    if(value !== "Каждый день"){
        hide2.classList.remove('active');
    }else {
        hide2.classList.add('active');
    }
}






