var displayedTask = "";

window.onload = function()
{
	tasks = document.getElementsByClassName("taskListPanel");
	for(var i = 0; i < tasks.length; i++)
	{
		tasks[i].setAttribute("onclick", "javascript: displayTask(this.dataset.taskId);");
	}
	$("#deleteButton").click(deleteSelectedTask);
	$("#editButton").click(editSelectedTask);
}

var deleteSelectedTask = function()
{
	if(displayedTask != "")
	{
		deleteTask(displayedTask);
	}
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

// action should be ADD or EDIT
var submitTaskForm = function()
{
	$("#taskForm").ajaxSubmit({url:"", dataType: "text", success: function(data)
	{
		task = JSON.parse(data);
		taskElement = $("#TASK-"+task.taskId);
		// Check if element exists
		if(taskElement.length)
		{
			taskElement.data("title", task.title);
			taskElement.data("assignee", task.assignee);
			taskElement.data("description", task.description);
			$("#TASK-" + task.taskId + "-title").html(task.title);
			$("#TASK-" + task.taskId + "-assignee").html(task.assignee);
			$("#TASK-" + task.taskId + "-description").html(task.description);
			displayTask(displayedTask);
		}
		else
		{
			existingElement = $("#tasksList").children(":first");
			// If no element exists yet
			if(!existingElement.length)
			{
				location.reload();
				return;
			}
			taskElement = existingElement.clone(true);
			taskElement.attr("id", "TASK-" + task.taskId);
			$("#tasksList").append(taskElement);
			taskElement.attr("data-task-id", task.taskId);
			taskElement.data("task-id", task.taskId);
			taskElement.attr("data-title", task.title);
			taskElement.data("title", task.title);
			taskElement.attr("data-assignee", task.assignee);
			taskElement.data("assignee", task.assignee);
			taskElement.attr("data-description", task.description);
			taskElement.data("description", task.description);
			oldTaskId = existingElement.data("taskId");
			taskElement.find("#TASK-" + oldTaskId + "-title").html(task.title);
			taskElement.find("#TASK-" + oldTaskId + "-title").attr("id", "TASK-" + task.taskId + "-title");
			taskElement.find("#TASK-" + oldTaskId + "-assignee").html(task.assignee);
			taskElement.find("#TASK-" + oldTaskId + "-assignee").attr("id", "TASK-" + task.taskId + "-assignee");
			taskElement.find("#TASK-" + oldTaskId + "-description").html(task.description);
			taskElement.find("#TASK-" + oldTaskId + "-description").attr("id", "TASK-" + task.taskId + "-description");
			taskElement.removeClass("active");
		}
	}});
	$("#editTaskModal").modal("hide");
}

var deleteTask = function(taskId)
{
	request = getRequest("DELETE", window.location.origin + "/tasks");
	request.onreadystatechange = function()
  	{
  		if (request.readyState==4 && request.status==200)
  		{
  			responseText = request.responseText;
  			if(responseText == "Deleted")
  			{
  				$("#TASK-" + taskId).remove();
  				clearDisplayedTask();
  			}
  		}
  	}
	request.send(taskId);
}

var openCreateNewTaskModal = function()
{
	$("#editTaskModalTitle").html("Create New Task");
	$("#modalTaskId").val("");
	$("#modalTaskTitle").val("");
	$("#modalTaskAssignee").val("");
	$("#modalTaskDescription").val("");
	$("#editTaskModal").modal("show");
	$("#modalTaskTitle").focus();
}

var editSelectedTask = function()
{
	openEditTaskModal(displayedTask);
}

var openEditTaskModal = function(taskId)
{
	$("#editTaskModalTitle").html("Edit Task");
	task = $("#TASK-" + taskId);
	$("#modalTaskId").val(task.data("task-id"));
	$("#modalTaskTitle").val(task.data("title"));
	$("#modalTaskAssignee").val(task.data("assignee"));
	$("#modalTaskDescription").val(task.data("description"));
	$("#editTaskModal").modal("show");
	$("#modalTaskTitle").focus();
}

var clearDisplayedTask = function()
{
	$("#selectedTask").empty();
	$(".selectedTaskTools").hide();
}