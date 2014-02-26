from src.rest import RESTProperties
from src.utils import HttpUtils
import json

loginURL = RESTProperties.getBaseURL() + "/login"
tokenURL = RESTProperties.getBaseURL() + "/token"
logoutURL = RESTProperties.getBaseURL() + "/logout"

def login(username, password):
    url = tokenURL + "?username=" + username
    response = HttpUtils.doGetRequest(url, None, None)
    token = None
    try:
        token = json.loads(response)
        token = str(token["token"])
    except:
        return response
    parameters = {
                  "token" : token,
                  "password" : password
    }
    url = loginURL
    response = HttpUtils.doGetRequest(url, parameters, None)
    if response == "Login Successful":
        return True
    return response

def logout():
    url = logoutURL
    response = HttpUtils.doGetRequest(url, None, None)
    return response