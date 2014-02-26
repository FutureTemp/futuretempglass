from src.controllers.Controller import Controller
from src.rest import LoginAccessor

class LogoutController(Controller):
    
    def onGET(self, handler):
        Controller.onGET(self, handler)
        response = LoginAccessor.logout()
        handler.wfile.write(response)