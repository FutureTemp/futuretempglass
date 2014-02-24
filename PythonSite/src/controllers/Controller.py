from src.Resources import getFile

class Controller(object):
    
    @staticmethod
    def getHTMLFile(path):
        try:
            htmlFile = getFile("res/html/" + path, "r")
        except Exception as e:
            print e
            return None
        return htmlFile
    
    def onGET(self, handler):
        pass
    
    def onPOST(self, handler):
        pass