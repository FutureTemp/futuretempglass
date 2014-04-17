var numberOfNotifications = undefined;

var onUnreadNotificationsFetched = function(notifications) {
    var html = "";
    for (var i = 0; i < notifications.length; i++) {
	if (i != 0) {
	    html += '<li class="divider"></li>';
	}
	var notificationElement = new NotificationListElement(notifications[i]);
	html += notificationElement.getHTML();
    }
    $("#notificationsList").html(html);
    if (numberOfNotifications != undefined
	    && numberOfNotifications < notifications.length) {
	setTimeout(onNewNotification, 0);
    }
    numberOfNotifications = notifications.length;
    if (numberOfNotifications == 0) {
	$("#notificationsBadge").html("");
    } else {
	$("#notificationsBadge").html(numberOfNotifications);
    }
}

var notificationAlertActive = false;
var onNewNotification = function() {
    if (!notificationAlertActive) {
	notificationAlertActive = true;
	alert("New Notification");
	notificationAlertActive = false;
    }
}

var getUnreadNotifications = function() {
    var request = getRequest("GET", getBaseUrl() + "/notifications?read=false");
    request.onResponse = function() {
	if (onUnreadNotificationsFetched != undefined) {
	    var notifications = JSON.parse(request.responseText);
	    onUnreadNotificationsFetched(notifications);
	}
    }
    request.send();
}

var NotificationListElement = function(info) {
    this.notificationId = info.notificationId;
    this.type = info.type;
    this.message = info.message;
    this.user = info.user;
    this.read = info.read;

    this.getHTML = function() {
	var html = "<li id=\"NOTIFICATION-" + this.notificationId
		+ "\"><a href='javascript:readNotification(\""
		+ this.notificationId + "\");'>";
	if (!this.read) {
	    html += "<strong>";
	}
	html += this.message;
	if (!this.read) {
	    html += "</strong>";
	}
	html += "</a></li>";
	return html;
    }
}

var readNotification = function(notificationId) {
    var request = getRequest("POST", getBaseUrl() + "/notifications");
    var action = {};
    action.action = "read";
    action.notificationId = notificationId;
    request.onResponse = function() {
	try {
	    if (action.action == request.responseText) {
		var element = $("#NOTIFICATION-" + notificationId);
		var divider = element.next();
		if (divider.length == 0) {
		    divider = element.prev();
		}
		if (divider.length != 0) {
		    divider.remove();
		}
		element.remove();
		numberOfNotifications--;
		if (numberOfNotifications == 0) {
		    $("#notificationsBadge").html("");
		} else {
		    $("#notificationsBadge").html(numberOfNotifications);
		}
	    }
	} catch (e) {
	}
    }
    request.send(JSON.stringify(action));
}

getUnreadNotifications();
setInterval(getUnreadNotifications, 10000);
