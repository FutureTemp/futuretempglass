var displayedTask = "";

window.onload = function()
{
	tasks = document.getElementsByClassName("taskListPanel");
	for(var i = 0; i < tasks.length; i++)
	{
		tasks[i].setAttribute("onclick", "javascript: displayTask('"+ tasks[i].id.substring(5) + "');");
	}
	$("#deleteButton").click(function()
	{
		if(displayedTask != "")
		{
			deleteTask(displayedTask);
		}
	});
}

var displayTask = function(taskId)
{
	displayedTask = taskId;

	$(".selectedTaskTools").show();

	selectedTask = document.getElementById("selectedTask");

	taskPanels = $(".taskListPanel");
	taskPanels.removeClass("active");

	clickedTask = $("#TASK-" + taskId);
	selectedTask.innerHTML = clickedTask.html();
	clickedTask.addClass("active");
}

var submitNewTask = function()
{
	textArea = document.getElementById("newTaskTextArea");
	taskInfo = document.getElementById("taskInfo");
	taskInfo.value = textArea.value;
	form = document.getElementById("newTaskForm");
	form.submit();
}

var deleteTask = function(taskId)
{
	request = getRequest("DELETE", window.location.origin + "/tasks");
	request.onreadystatechange = function()
  	{
  		if (request.readyState==4 && request.status==200)
  		{
  			responseText = request.responseText;
  			console.log("Response text: " + responseText);
  			location.reload();
  		}
  	}
	request.send(taskId);
}