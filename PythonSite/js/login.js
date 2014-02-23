window.onload = function()
{
	try
	{
		window.onload()
	}
	catch(e)
	{}
	window.errorElement = document.getElementById("error");
}

var login = function(username, password)
{
	window.errorElement.innerHTML = "";
	var xmlhttp = getRequest("GET", window.baseUrl + "token?username=" + username);
	if(xmlhttp == null)
	{
		alert("Browser is not supported");
	}
	var onTokenResponse = function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
  		{
  			responseText = xmlhttp.responseText;
  			try
  			{
  				token = JSON.parse(responseText);
  				token = token.token;
  				loginRequest = getRequest("POST", window.baseUrl + "login");
  				loginRequest.onreadystatechange = function()
  				{
  					if (loginRequest.readyState==4 && loginRequest.status==200)
  					{
  						responseText = loginRequest.responseText;
  						if(responseText == "Login Successful")
  						{
  							window.location = "tasks";
  						}
  						else
  						{
  							window.errorElement.innerHTML = responseText;
  						}
  					}
  				}
  				loginRequest.send("token=" + token + "&password=" + password);
  			}
  			catch(e)
			{
  				window.errorElement.innerHTML = "Login Failed";
  			}
  		}
	}
  	xmlhttp.onreadystatechange = onTokenResponse;
  	xmlhttp.send();
}