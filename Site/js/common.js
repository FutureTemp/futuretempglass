if (!window.location.origin)
{
  window.location.origin = window.location.protocol+"//"+window.location.host;
}

var getRequest = function(method, url)
{
	var xhr = new XMLHttpRequest();
	if ("withCredentials" in xhr) 
  	{

    	// Check if the XMLHttpRequest object has a "withCredentials" property.
    	// "withCredentials" only exists on XMLHTTPRequest2 objects.
    	xhr.open(method, url, true);

  	} 
  	else if (typeof XDomainRequest != "undefined") 
  	{

    	// Otherwise, check if XDomainRequest.
    	// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
   		xhr = new XDomainRequest();
    	xhr.open(method, url);

  	} 
  	else 
  	{

    	// Otherwise, CORS is not supported by the browser.
    	xhr = null;
      alert("Browser is not supported");
  	}

  	return xhr;
}

var logout = function()
{
	logoutRequest = getRequest("GET", window.serverlocation + "/logout");
	logoutRequest.onreadystatechange = function()
	{
		if(logoutRequest.readyState==4 && logoutRequest.status==200)
		{
			console.log(logoutRequest.responseText);
		}
	}
	logoutRequest.send();
}