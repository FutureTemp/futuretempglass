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

var submitNewTask = function()
{
	textArea = document.getElementById("newTaskTextArea");
	taskInfo = document.getElementById("taskInfo");
	taskInfo.value = textArea.value;
	form = document.getElementById("newTaskForm");
	form.submit();
}