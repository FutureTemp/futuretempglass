from src.controllers.Controller import Controller
from src.utils import HttpUtils
import json
from src.htmlobjects.TaskListPanel import TaskListPanel

class TasksController(Controller):
    
    htmlPath = "tasks.html"
    
    def onGET(self, handler):
        htmlFile = Controller.getHTMLFile(TasksController.htmlPath)
        if(htmlFile == None):
            handler.wfile.write("Could not find html file " + TasksController.htmlPath + "\n")
        tasks = HttpUtils.doGetRequest("http://localhost:8080/tasks", None, None)
        tasks = json.loads(tasks)
        taskElements = []
        for task in tasks:
            element = TaskListPanel(task["taskId"], task["title"], task["assignee"], task["description"])
            taskElements.append(element)
        taskListContent = ""
        for taskElement in taskElements:
            taskListContent += taskElement.toHTML()
        handler.wfile.write(htmlFile.read().replace("$taskListPanels", taskListContent))
    
    def onPOST(self, handler):
        taskInfo = self.getRequestBody(handler)
        taskInfo = taskInfo[taskInfo.find("=") + 1: len(taskInfo)]
        response = HttpUtils.doPostRequest("http://localhost:8080/tasks", taskInfo, None)
        handler.wfile.write(response)