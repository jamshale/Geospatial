var clientList = [];


$(document).ready(function(){
    $.post({
        url: "/clients",
        success: initiateClientList,
    })
})

function initiateClientList(clients){
    clientList = clients;
    console.log(clientList);
}