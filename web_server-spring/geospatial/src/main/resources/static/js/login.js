$(document).ready(function(){

    // Display Sign-in on errors

    var finalCheck = false;
    var check = $('.error-check-name').text()
    if(check != ""){
        finalCheck = true;
    }
    check = $('.error-check-pass').text()
    if(check != ""){
        finalCheck = true;
    }
    check = $('.error-check-match').text()
    if(check != ""){
        finalCheck = true;
    }
    if(finalCheck == true){
        $('#registerModal').modal('show');
    }


})