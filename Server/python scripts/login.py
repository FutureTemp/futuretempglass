import urllib2
import json

username = raw_input("Username: ")
password = raw_input("Password: ")
username = str(username)
password = str(password)

url = "http://localhost:8080/token?username=" + username
req = urllib2.Request(url)
response = urllib2.urlopen(req)
token = response.read()
response.close()
token = json.loads(token)
token = token["token"]

url = "http://localhost:8080/login"
req = urllib2.Request(url)
req.add_header("token", token)
req.add_header("password", password)
response = urllib2.urlopen(req)
message = response.read()
response.close()
