
function refresh() {
    setTimeout("refresh();", 5000);
    refreshUsers();
    refreshSalons();
}

var username;
var salon;

$( document ).ready(function() {



    $("#loginButton").click(function()
    {
        username = $("#login").val();

        $.ajax({
            url: "/goto/backoffice/newuser",
            type: "POST",
            data:"user="+ username,
            success: function(response){
                $("#usernameDiv").html("User name is : "+username);
                $("#loginDiv").hide();
                $("#menuDiv").show();
                $("#usernameDiv").show();
                $("#salonListDiv").show();
                $("#userListDiv").show();
                $("#logoutDiv").show();
                refreshSalons();
                refreshUsers();
            },
            error: function(error){
                console.log(error);
            }
        });

    });

    $("#logoutButton").click(function(){
        $('#login').val('');
        username = null;
        salon = null;
        $("#loginDiv").show();
        $("#menuDiv").hide();
        $("#usernameDiv").hide();
        $("#salonListDiv").hide();
        $("#userListDiv").hide();
        $("#chatDiv").hide();
        $("#messageDiv").hide();
        $("#logoutDiv").hide();
    });

    $("#salonButton").click(function()
    {
        salon = $("#salon").val();

        $.ajax({
            url: "/goto/backoffice/addsalon",
            type: "POST",
            data:"salon="+salon,
            success: function(data){
                refreshSalons();
                // loadChat(salon);
            },
            error: function(error){
                console.log(error);
            }
        });
    });

});

function loadChat(salonDiv) {
    $("#chatDiv").hide();
    var salon = salonDiv.id;
    $.ajax({
        url: "/goto/backoffice/salon/" + salon,
        type: "GET",
        dataType: "json",
        success: function (data) {
            var messageList = data;
            $("#messagesList").html(messageList);

        },
        error: function (error) {
            console.log(error);
        }
    });
    $("#chatDiv").show();
    $("#messageDiv").show();
}

function refreshSalons() {
    $.ajax({
        url: "/goto/backoffice/salons",
        type: "GET",
        dataType:"json",
        success: function(data){
            var salonList = data;
            var salonListFormatted = "";
            salonList.forEach(function(salon) {
                salonListFormatted = salonListFormatted+"<div id=\""+salon+"\">"+salon+": <button onclick=\"loadChat("+salon+")\">Go</button></div><br>";
            });
            $("#salonList").html(salonListFormatted);
        },
        error: function(error){
            console.log(error);
        }
    });
}

function refreshUsers() {
    $.ajax({
        url: "/goto/backoffice/users",
        type: "GET",
        dataType:"json",
        success: function(data){
            var userList = data;
            var userListFormatted = "";
            userList.forEach(function(user) {
                userListFormatted = userListFormatted+"<p>"+user+"<br></p>";
            });
            $("#userList").html(userListFormatted);
        },
        error: function(error){
            console.log(error);
        }
    });
}
