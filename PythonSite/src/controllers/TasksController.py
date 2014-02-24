from src.controllers.Controller import Controller
from src.utils import HttpUtils
import json

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
            taskElements.append(TasksListElement(task))
        taskListContent = ""
        for taskElement in taskElements:
            taskListContent += taskElement.getHTML()
        handler.wfile.write(htmlFile.read().replace("$taskListContent", taskListContent))

class TasksListElement(object):
    
    def __init__(self, taskDictionary):
        self.taskId = taskDictionary["taskId"]
        self.title = taskDictionary["title"]
        self.assignee = taskDictionary["assignee"]
        self.description = taskDictionary["description"]
    
    def getHTML(self):
        html = "<div id='TASK-" + self.taskId + "' class='taskListElement'>"
        html += "Task ID: " + self.taskId
        html += "<br>Title: " + self.title
        html += "<br>Assignee: " + self.assignee
        html += "<br>Description: " + self.description 
        html += "</div>"
        return html