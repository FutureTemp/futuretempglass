import urllib2
import json
import hashlib

username = "francesco"
password = "12345"

m = hashlib.sha256()
m.update(username + password)
hash = m.hexdigest().upper()

url = "http://localhost:8080/token?username=" + username
req = urllib2.Request(url)
response = urllib2.urlopen(req)
token = response.read()
response.close()
print token
token = json.loads(token)
token = token["token"]

m = hashlib.sha256()
m.update(token + hash)
auth = m.hexdigest()
auth = auth.upper()

url = "http://localhost:8080/login"
req = urllib2.Request(url)
req.add_header("Authentication", auth)
response = urllib2.urlopen(req)
message = response.read()
response.close()
print message