from src.controllers.Controller import Controller

class LoginController(Controller):
    
    htmlPath = "login.html"
    
    def onGET(self, handler):
        htmlFile = Controller.getHTMLFile(LoginController.htmlPath)
        if htmlFile == None:
            handler.wfile.write("Could not find html file " + LoginController.htmlPath)
        handler.wfile.write(htmlFile.read())
    
    def onPOST(self, handler):
        pass