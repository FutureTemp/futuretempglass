window.onload = function()
{
	try
	{
		window.onload()
	}
	catch(e)
	{}
}

var login = function(username, password)
{
	hideError();
  loginRequest = getRequest("POST", window.location.origin + "/login");
  loginRequest.onreadystatechange = function()
  {
  	if (loginRequest.readyState==4 && loginRequest.status==200)
  	{
  		responseText = loginRequest.responseText;
      console.log(responseText);
  		if(responseText == "Login Successful")
  		{
  			window.location = "tasks";
  		}
  		else
  		{
  			showError(responseText);
  		}
    }
	}
  loginRequest.send("username=" + username + "&password=" + password);
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