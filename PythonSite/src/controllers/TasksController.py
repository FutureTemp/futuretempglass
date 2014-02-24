from src.controllers.Controller import Controller

class TasksController(Controller):
    
    htmlPath = "tasks.html"
    
    def onGET(self, handler):
        htmlFile = Controller.getHTMLFile(TasksController.htmlPath)
        if(htmlFile == None):
            handler.wfile.write("Could not find html file " + TasksController.htmlPath + "\n")
        handler.wfile.write(htmlFile.read())