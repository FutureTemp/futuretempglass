from Views import views

class Page(object):
    def __init__(self, path, view):
        self._path = path
        self._view = view
    def getPath(self):
        return self._path
    def getView(self):
    	return self._view
    def getHTML(self):
    	return self.getView().getHTML()

pages = {
    "/login" : Page("/login", views["loginPage"]),
    "/tasks" : Page("/tasks", views["tasksPage"])
}

def getPageHTML(path):
	page = None
	try:
		page = pages[path]
	except:
		return getFile(path)
	return page.getHTML()

def getFile(path):
    try:
        extension = path[path.find(".") + 1: len(path)]
        path = extension + path
    except:
        pass
    try:
        f = open(path, "rb")
        return f.read()
    except:
        return "Could not read file at " + path