from src.Resources import getFile

class Controller(object):

    def getRequestBody(self, handler):
        return handler.rfile.read(int(handler.headers.getheader('Content-Length')))
    
    def getPOSTParameters(self, handler):
        requestBody = self.getRequestBody(handler)
        pairs = requestBody.split("&")
        parameters = {}
        for pair in pairs:
            parameter = pair.split("=")
            parameters[parameter[0]] = parameter[1]
        return parameters
        
    def onGET(self, handler):
        pass
    
    def onPOST(self, handler):
        pass
     
    @staticmethod
    def getHTMLFile(path):
        try:
            htmlFile = getFile("res/html/" + path, "r")
        except Exception as e:
            print e
            return None
        return htmlFile