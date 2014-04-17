if (!window.location.origin) {
    window.location.origin = window.location.protocol + "//"
	    + window.location.host;
}

var getRequest = function(method, url) {
    var xhr = new XMLHttpRequest();
    if ("withCredentials" in xhr) {

	// Check if the XMLHttpRequest object has a "withCredentials" property.
	// "withCredentials" only exists on XMLHTTPRequest2 objects.
	xhr.open(method, url, true);

    } else if (typeof XDomainRequest != "undefined") {

	// Otherwise, check if XDomainRequest.
	// XDomainRequest only exists in IE, and is IE's way of making CORS
	// requests.
	xhr = new XDomainRequest();
	xhr.open(method, url);

    } else {

	// Otherwise, CORS is not supported by the browser.
	xhr = null;
	alert("Browser is not supported");
    }

    if (xhr != null) {
	xhr.onreadystatechange = function() {
	    if (xhr.readyState == 4 && xhr.status == 200) {
		xhr.onResponse();
	    }
	}
    }

    return xhr;
}

var getBaseUrl = function() {
    return window.location.protocol + "//" + window.location.host;
}

var logout = function() {
    logoutRequest = getRequest("GET", window.serverlocation + "/logout");
    logoutRequest.onreadystatechange = function() {
	if (logoutRequest.readyState == 4 && logoutRequest.status == 200) {
	    console.log(logoutRequest.responseText);
	}
    }
    logoutRequest.send();
}

var getUrlParameter = function(paramName) {
    var urlParamString = window.location.search.substring(1);
    var urlParams = urlParamString.split('&');
    for (var i = 0; i < urlParams.length; i++) {
	var pair = urlParams[i].split('=');
	if (pair[0] == paramName) {
	    return pair[1];
	}
    }
    return null;
}

var getCurrentUser = function() {
    var request = getRequest("GET", getBaseUrl() + "/users?current=true");
    request.onResponse = function() {
	if (onCurrentUserFetched != undefined) {
	    var responseText = request.responseText;
	    var user = JSON.parse(responseText);
	    onCurrentUserFetched(user);
	}
    }
    request.send();
}
