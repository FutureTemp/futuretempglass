from src.htmlobjects.HTMLObject import HTMLObject
from src import Resources

class TaskListPanel(HTMLObject):
    
    pathToTemplate = "res/html-fragments/TaskListPanel.html"
    
    def __init__(self, taskId, title, assignee, description):
        self.taskId = taskId
        self.title = title
        self.assignee = assignee
        self.description = description
        
    def toHTML(self):
        htmlFile = Resources.getFile(TaskListPanel.pathToTemplate, "r")
        html = htmlFile.read();
        html = html.replace("$taskId", self.taskId)
        html = html.replace("$title", self.title)
        html = html.replace("$assignee", self.assignee)
        html = html.replace("$description", self.description)
        return html