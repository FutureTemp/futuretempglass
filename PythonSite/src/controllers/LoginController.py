from src.controllers.Controller import Controller
from src.rest import LoginAccessor

class LoginController(Controller):
    
    htmlPath = "login.html"
    
    def onGET(self, handler):
        Controller.onGET(self, handler)
        htmlFile = Controller.getHTMLFile(LoginController.htmlPath)
        if htmlFile == None:
            handler.wfile.write("Could not find html file " + LoginController.htmlPath)
        handler.wfile.write(htmlFile.read())
    
    def onPOST(self, handler):
        Controller.onPOST(self, handler)
        parameters = self.getPOSTParameters(handler)
        result = LoginAccessor.login(parameters["username"], parameters["password"])
        if result == True:
            """
            handler.send_response(301)
            handler.send_header("Location", "/tasks")
            return"""
            handler.wfile.write("Login Successful")
        else:
            handler.wfile.write(result)