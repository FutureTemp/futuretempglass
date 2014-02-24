import urllib2

def dictToUrlParam(parameters):
    urlParams = ""
    for key in parameters:
        urlParams += key + "=" + parameters[key]
        urlParams += "&"
    urlParams = urlParams[0:len(urlParams) - 1]

def doGetRequest(url, parameters, headerData):
    if not parameters == None:
        url += dictToUrlParam(parameters)
    req = urllib2.Request(url)
    if not headerData == None:
        for key in headerData:
            req.add_header(key, headerData[key])
    response = urllib2.urlopen(url)
    return response.read()
    
