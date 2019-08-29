var global_current_lidar = ""
var global_host_name = ""

$(document).ready(function(){
    global_host_name = window.location.hostname;
})

function lidarLoad(item){
    $('#full-screen-btn').attr('disabled', false)
    $('#switcher-btn').attr('disabled', false)
    global_current_lidar = $(item).find("p").text()
    // set
    if(global_current_lidar == 'hanover'){
        $('#viewFrame').attr('src', `http://${global_host_name}:8085/data/hanover.html`)
    } else if(global_current_lidar == 'beer-caves') {
        $('#viewFrame').attr('src', `http://${global_host_name}:8085/data/beer-caves.html`)
    } else if(global_current_lidar == 'dublin') {
        $('#viewFrame').attr('src', `http://${global_host_name}:8085/data/dublin.html`)
    } else if(global_current_lidar == 'beer-caves') {
        $('#viewFrame').attr('src', `http://${global_host_name}:8085/data/beer-caves.html`)
    } else if(global_current_lidar == 'vanuatu-village') {
        $('#viewFrame').attr('src', `http://${global_host_name}:8085/data/vanuatu-village.html`)
    } else if(global_current_lidar == 'new-zealand') {
        $('#viewFrame').attr('src', `http://${global_host_name}:8085/data/new-zealand.html`)
    } else if(global_current_lidar == 'red-rocks') {
        $('#viewFrame').attr('src', `http://${global_host_name}:8085/data/view.html?r=http://${global_host_name}:8111/${global_current_lidar}"`)
    }  else if(global_current_lidar == 'golden-gate') {
        $('#viewFrame').attr('src', `http://usgs.entwine.io/data/view.html?r=https://s3-us-west-2.amazonaws.com/usgs-lidar-public/ARRA-CA_GoldenGate_2010`)
    } 
    else{
        $('#viewFrame').attr('src', `http://${global_host_name}:8085/data/view.html?r="http://${global_host_name}:8111/${global_current_lidar}"`)
    }
    //http://potree.entwine.io/data/view.html?r=https://geoentwine.azurewebsites.net/${global_current_lidar}
    //load
    $( '#viewFrame' ).attr( 'src', function ( i, val ) { return val; });
    //caption
}

function fullscreenviewer(b) {
    if(global_current_lidar == 'hanover'){
        var win = window.open(`http://${global_host_name}:8085/data/hanover.html`, '_blank')
    } else if(global_current_lidar == 'beer-caves'){
        var win = window.open(`http://${global_host_name}:8085/data/beer-caves.html`, '_blank')
    } else if(global_current_lidar == 'autzen'){
        var win = window.open(`http://${global_host_name}:8085/data/autzen.html`, '_blank')
    } else if(global_current_lidar == 'dublin'){
        var win = window.open(`http://${global_host_name}:8085/data/dublin.html`, '_blank')
    } else if(global_current_lidar == 'vanuatu-village'){
        var win = window.open(`http://${global_host_name}:8085/data/vanuatu-village.html`, '_blank')
    } else if(global_current_lidar == 'new-zealand'){
        var win = window.open(`http://${global_host_name}:8085/data/new-zealand.html`, '_blank')
    } else if(global_current_lidar == 'red-rocks'){
        var win = window.open(`http://${global_host_name}:8085/data/view.html?r=http://${global_host_name}:8111/${global_current_lidar}"`, '_blank')
    } else if(global_current_lidar == 'golden-gate'){
        var win = window.open(`http://usgs.entwine.io/data/view.html?r=https://s3-us-west-2.amazonaws.com/usgs-lidar-public/ARRA-CA_GoldenGate_2010`, '_blank')
    }else{
        var win = window.open(`http://${global_host_name}:8085/data/view.html?r="http://${global_host_name}:8111/${global_current_lidar}"`, '_blank')
    }
    win.focus();
}
  
function switcher(b){
    var this_text = $(b).text()
    if(this_text == 'LiDAR'){
        $('#map').hide()
        $('#viewFrame').show()
        $(b).text(function(){
            return 'Map'
        })
    } else if (this_text == 'Map'){
        $('#viewFrame').hide()
        $('#map').show()
        $(b).text(function(){
            return 'LiDAR'
        })
    }
}

$(window).on("unload", function() {
    return $('#viewFrame').removeData('src');
});
