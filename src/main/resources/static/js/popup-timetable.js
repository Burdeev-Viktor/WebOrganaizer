 const openPopups = document.getElementsByClassName("open-popup");
 const closePopups = document.getElementsByClassName("close-popup");
 const shiftEducation = document.getElementById('shift');

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
 shiftEducation.addEventListener("click",function (){
     const firstTime = document.getElementById('time1');
     const secondTime = document.getElementById('time2');
     let shiftValue = shiftEducation.value;
     if (shiftValue ==="Первая"){
        firstTime.classList.remove('none');
        secondTime.classList.add('none');
     }else {
         firstTime.classList.add('none');
         secondTime.classList.remove('none');
     }
 });
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