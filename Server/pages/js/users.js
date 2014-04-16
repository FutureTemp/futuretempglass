window.onload = function() {
    fetchAccounts();
}

var fetchAccounts = function() {
    var request = getRequest("GET", getBaseUrl() + "/users");
    
    request.onResponse = function() {
	var responseText = request.responseText;
	var accounts = JSON.parse(responseText);
	var tableBody = $("#accountsTable");
	for(var i = 0; i < accounts.length; i++){
	    var component = new AccountRow(accounts[i]);
	    tableBody.append(component.getHTML());
	}
    }
    
    request.send();
}

var AccountRow = function(info) {

    this.username = info.username;
    this.firstName = info.firstName;
    this.lastName = info.lastName;
    this.admin = info.admin;
    this.hashedPassword = info.hashedPassword;

    this.getHTML = function() {
	return '<tr>\
	<td><a href="user?user=' + this.username + '&action=edit">' + this.username + '</a></td>\
	<td>' + this.firstName + '</td>\
	<td>' + this.lastName + '</td>\
	<td>' + this.admin + '</td></tr>';
    }
}