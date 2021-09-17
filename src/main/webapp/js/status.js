function changeStatus(orderId,status) {
    const note = document.getElementById(orderId);
    const value = document.getElementById("status_"+orderId).value;
    if (value===status){
        note.style.display ="none";
    }else {
        note.style.display ="block";
    }
}