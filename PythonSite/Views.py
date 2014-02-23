class View(object):
	def __init__(self, htmlPath):
		self._htmlPath = htmlPath
	def getHTML(self):
		try:
			pageFile = open("views" + self._htmlPath, "r")
		except:
			return "File could not be read at " + self._htmlPath
		return pageFile.read()

views = {
	"loginPage" : View("/login.html"),
	"tasksPage" : View("/tasks.html")
}
