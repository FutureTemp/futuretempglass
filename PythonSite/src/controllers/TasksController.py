from src.controllers.Controller import Controller
from src.htmlobjects.TaskListPanel import TaskListPanel
from src.rest import TasksAccessor
from src.utils import HttpUtils


class TasksController(Controller):
    
    htmlPath = "tasks.html"
    
    def onGET(self, handler):
        htmlFile = Controller.getHTMLFile(TasksController.htmlPath)
        if(htmlFile == None):
            handler.wfile.write("Could not find html file " + TasksController.htmlPath + "\n")
        tasks = TasksAccessor.getAllTasks()
        taskElements = []
        for task in tasks:
            element = TaskListPanel(task["taskId"], task["title"], task["assignee"], task["description"])
            taskElements.append(element)
        taskListContent = ""
        for taskElement in taskElements:
            taskListContent += taskElement.toHTML()
        handler.wfile.write(htmlFile.read().replace("$taskListPanels", taskListContent))
    
    def onPOST(self, handler):
        taskInfo = self.getPOSTParameters(handler)
        print taskInfo
        response = TasksAccessor.addTask(taskInfo)
        handler.wfile.write(response)

    def onDELETE(self, handler):
        Controller.onDELETE(self, handler)
        taskId = self.getRequestBody(handler)
        response = TasksAccessor.removeTask(taskId)
        handler.wfile.write(response)