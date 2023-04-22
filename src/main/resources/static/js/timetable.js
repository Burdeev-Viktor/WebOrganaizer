 const openPopups = document.getElementsByClassName("open-popup");
 const closePopups = document.getElementsByClassName("close-popup");
 const shiftEducation = document.getElementById('shift');
 let bufTime;

 connectionPopupButtons(openPopups,closePopups);
 shiftEducation.addEventListener("click",function (){
     const firstTime = document.getElementById('time1');
     const secondTime = document.getElementById('time2');
     let shiftValue = shiftEducation.value;
     if (shiftValue ==="Первая"){
         if(bufTime !== ""){
             firstTime.options[firstTime.selectedIndex].value = bufTime;
         }
        firstTime.classList.remove('none');
        secondTime.classList.add('none');

        bufTime = secondTime.options[secondTime.selectedIndex].value
        secondTime.options[secondTime.selectedIndex].value = "";
     }else {
         if(bufTime !== ""){
             secondTime.options[secondTime.selectedIndex].value = bufTime;
         }
         firstTime.classList.add('none');
         secondTime.classList.remove('none');

         bufTime = firstTime.options[firstTime.selectedIndex].value
         firstTime.options[firstTime.selectedIndex].value = "";
     }
 });
 function clickDeleteTimetable(img) {
     let form = document.getElementById('formDel' + img.getAttribute('id').split('img').join(''));
     if(confirm("Вы действительно хотите удалить пару?")){
         form.submit();
     }

 }
function validationTimetable(id){
    let res = true;
    const teacher = document.getElementById('teacher'+id);
    const room = document.getElementById('room'+id);
    clearError(teacher);
    clearError(room);
    if(teacher.value.length === 0){
        teacher.value = teacher.getAttribute('Placeholder');
    }
    if(room.value.length === 0){
        room.value = room.getAttribute('Placeholder');
    }
    if(teacher.value.length <= 4){
        res = false;
        createError(teacher,"Не меннее 4 символов");
    }
    if(room.value.length <= 1){
        res = false;
        createError(room,"Не меннее 1 символов");
    }
    return res;
}
