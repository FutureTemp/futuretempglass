import RESTProperties
from src.utils import HttpUtils
import json

tasksURL = RESTProperties.getBaseURL() + "/tasks"

def getAllTasks():
    url = tasksURL
    response = HttpUtils.doGetRequest(url, None, None)
    tasks = json.loads(response)
    return tasks

def removeTask(taskId):
    response = HttpUtils.doDeleteRequest(tasksURL, taskId, None)
    return response