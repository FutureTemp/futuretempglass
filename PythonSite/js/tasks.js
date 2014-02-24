window.onload = function()
{
	tasks = document.getElementsByClassName("taskListElement");
	for(var i = 0; i < tasks.length; i++)
	{
		tasks[i].setAttribute("onclick", "javascript: displayTask('"+ tasks[i].id.substring(5) + "');");
	}
}

var displayTask = function(taskId)
{
	selectedTask = document.getElementById("selectedTask");
	selectedTask.innerHTML = document.getElementById("TASK-" + taskId).innerHTML;
}