let columns = document.getElementsByClassName('colum');
setHeight();
function setHeight(){
    for (let i = 0; i < columns.length; i++) { // выведет 0, затем 1, затем 2
        columns[i].style.height = columns[i].getAttribute('percent') + '%';
        columns[i].style.background= columns[i].getAttribute('colorColum');
    }
}