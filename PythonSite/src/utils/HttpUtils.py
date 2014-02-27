import urllib2
import httplib

def dictToUrlParam(parameters):
    urlParams = ""
    for key in parameters:
        urlParams += key + "=" + parameters[key]
        urlParams += "&"
    urlParams = urlParams[0:len(urlParams) - 1]
    return urlParams

def doGetRequest(url, parameters, headerData):
    if not parameters == None:
        url += "?" + dictToUrlParam(parameters)
    req = urllib2.Request(url)
    if not headerData == None:
        for key in headerData:
            req.add_header(key, headerData[key])
    response = urllib2.urlopen(url)
    return response.read()

def doPostRequest(url, data, headerData):
    req = urllib2.Request(url, data)
    if not headerData == None:
        for key in headerData:
            req.add_header(key, headerData[key])
    response = urllib2.urlopen(req)
    return response.read()
    
def doDeleteRequest(url, data, headerData):
    req = urllib2.Request(url, data)
    req.get_method = lambda: "DELETE"
    if not headerData == None:
        for key in headerData:
            req.add_header(key, headerData[key])
    response = urllib2.urlopen(req)
    return response.read()