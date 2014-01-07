import urllib2

url = "http://localhost:8080/logout"
req = urllib2.Request(url)
response = urllib2.urlopen(req)
message = response.read()
response.close()
print message