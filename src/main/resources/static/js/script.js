function createError (node,text){
    if(!node.classList.contains('error')) {
        let parent = node.parentNode;
        let errorLabel = document.createElement("label");
        errorLabel.classList.add("error-label");
        errorLabel.textContent = text;
        node.classList.add("error");
        parent.append(errorLabel);
    }
}
function clearError(node){
    if(node.classList.contains('error')){
        node.classList.remove("error");
        node.parentNode.querySelector(".error-label").remove();
    }
}
function connectionPopupButtons(openPopups, closePopups){
    for (let index = 0; index < openPopups.length; ++index){
        console.log(openPopups[index].getAttribute('Popup-id'));
        openPopups[index].addEventListener("click",function (){
            document.getElementById(this.getAttribute('Popup-id')).classList.remove('none');
        });
    }
    for (let index = 0; index < closePopups.length; ++index){
        closePopups[index].addEventListener("click",function (){
            document.getElementById(this.getAttribute('Popup-id')).classList.add('none');
        });
    }
}
function validateQuestDate(quest,date,res){
    if(quest.value === ""){
        createError(quest,"Введите задание");
        res = false;
    }else if(quest.value.length <= 4){
        createError(quest,"Не меннее 4 символов");
        res = false;
    }
    if(date.value ===""){
        createError(date,"Введите дату здачи")
        res = false;
    }
    return res;
}
