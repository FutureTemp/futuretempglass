window.onload = function()
{
	try
	{
		window.onload();
    var serverlocation = $.cookie("futuretemp.serverlocation");
    if(!serverlocation == undefined)
    {
      $("#id").val(serverlocation);
    }
	}
	catch(e)
	{}
}

var login = function(username, password)
{
	hideError();
  $.cookie("futuretemp.serverlocation", $("#ip").val(), { path: '/' });
  tokenRequest = getRequest("GET", window.serverlocation + "/token?username=" + username);
  tokenRequest.onreadystatechange = function()
  {
    if (tokenRequest.readyState==4 && tokenRequest.status==200)
    {
      var token = null;
      var responseText = tokenRequest.responseText;
      try
      {
        token = JSON.parse(responseText);
        console.log(token);
      }
      catch(e)
      {
        showError(responseText);
        return;
      }
      loginRequest = getRequest("POST", window.serverlocation + "/login");
      loginRequest.onreadystatechange = function()
      {
      	if (loginRequest.readyState==4 && loginRequest.status==200)
      	{
      		responseText = loginRequest.responseText;
          console.log(responseText);
      		if(responseText == "Login Successful")
      		{
      			window.location = "tasks.html";
      		}
      		else
      		{
      			showError(responseText);
      		}
        }
    	}
      loginRequest.send("token=" + token.token + "&password=" + password);
    }
  }
  tokenRequest.send();
}

var showError = function(message)
{
  error = $("#error");
  error.show();
  error.html(message);
}

var hideError = function()
{
  error = $("#error");
  error.html("");
  error.hide();
}