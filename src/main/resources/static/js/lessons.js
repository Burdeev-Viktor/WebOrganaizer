const openPopups = document.getElementsByClassName("open-popup");
const closePopups = document.getElementsByClassName("close-popup");

connectionPopupButtons(openPopups,closePopups);
function clickDelete(img){
    const form = img.parentElement;
    let flag = confirm("Вы действительно хотите удалить передмет?");
    if(flag){
        form.submit();
    }
}
function validation(id){
    let res = true;
    let name;
    if(id ===0){
        name = document.getElementById('name');
    }else {
        name = document.getElementById('name' + id);
        if (name.value === ""){
            name.value = name.getAttribute('placeholder');
        }
    }
    clearError(name);
    if (name.value === ""){
        createError(name,"Поле должно быть заполненно");
        res = false;
    }else if(name.value.length < 3){
        createError(name,"Не мение 3 символов");
        res = false;
    }
    return res;
}

