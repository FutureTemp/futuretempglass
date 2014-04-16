var action = null;
var EDIT = "edit";
var NEW = "new";

window.onload = function() {
    action = getUrlParameter("action");
    if (EDIT == action) {
	$("#usernameStatic").show();
	$("#username").hide();
	fetchUser();
    } else if (NEW == action) {
	$("#username").show();
	$("#usernameStatic").hide();
    }
    getCurrentUser();
}

var user = null;

var fetchUser = function() {
    var request = getRequest("GET", "/users?username="
	    + getUrlParameter("user"));

    request.onResponse = function() {
	var responseText = request.responseText;
	user = JSON.parse(responseText);
	if (user.admin) {
	    $("#admin").attr('checked', 'checked');
	}
	$("title").html(user.username);
	$("#usernameHeader").html(user.username);
	$("#username").val(user.username);
	$("#usernameStatic").html(user.username);
	$("#firstName").val(user.firstName);
	$("#lastName").val(user.lastName);
    }

    request.send();
}

var onCurrentUserFetched = function(currentUser) {
    if (currentUser.admin) {
	$("#adminCheckArea").show();
    }
}

var saveInformation = function() {
    $(".form-alert").hide();
    var account = {};
    if (NEW == action) {
	if (!$("#password").val()) {
	    $("#passwordAlert").show();
	    return;
	}
    }
    if ((NEW == action) || $("#password").val()) {
	var password = $("#password").val();
	if (password != $("#confirmPassword").val()) {
	    $("#passwordMatchAlert").show();
	    return;
	}
    }
    account.username = $("#username").val();
    account.firstName = $("#firstName").val();
    account.lastName = $("#lastName").val();
    account.password = $("#password").val();
    account.admin = $("#admin").prop('checked');
    console.log(account);
    var request = getRequest("POST", getBaseUrl() + "/users");
    request.onResponse = function() {
	$("#successAlert").show();
    }
    request.send(JSON.stringify(account));
}