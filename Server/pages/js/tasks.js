var displayedTask = "";

window.onload = function() {
    refreshTasks();
    setInterval(refreshTasks, 10000);
    $("#deleteButton").click(deleteSelectedTask);
    $("#editButton").click(editSelectedTask);
    getCurrentUser();
}

var onCurrentUserFetched = function(user) {
    $("#userButton").html(user.firstName + " " + user.lastName);
    $("#userButton").attr("name", user.username);
    if(user.admin){
	$(".admin-option").show();
    }
}

var deleteSelectedTask = function() {
    if (displayedTask != "") {
	deleteTask(displayedTask);
    }
}

var displayTask = function(taskId) {
    displayedTask = taskId;

    $(".selectedTaskTools").show();

    selectedTask = document.getElementById("selectedTask");

    taskPanels = $(".taskListPanel");
    taskPanels.removeClass("active");

    clickedTask = $("#TASK-" + taskId);
    selectedTask.innerHTML = clickedTask.html();
    clickedTask.addClass("active");
}

var refreshTasks = function() {
    var tasksRequest = getRequest("GET", getBaseUrl() + "/tasks");
    tasksRequest.onResponse = function() {
	var activeTaskId = $("#tasksList").find(".active").data("task-id");
	var tasksInfo = JSON.parse(tasksRequest.responseText);
	var html = "";
	for (var i = 0; i < tasksInfo.length; i++) {
	    var task = new TaskListPanel(tasksInfo[i]);
	    html += task.getHTML();
	}
	$("#tasksList").html(html);
	if(activeTaskId != undefined){
	    displayTask(activeTaskId);
	}
    }
    tasksRequest.send();
}

var submitTaskForm = function() {
    var task = {};
    task["taskId"] = $("#modalTaskId").val();
    task["title"] = $("#modalTaskTitle").val();
    task["assignee"] = $("#modalTaskAssignee").val();
    task["description"] = $("#modalTaskDescription").val();
    var taskRequest = getRequest("POST", getBaseUrl() + "/tasks");
    taskRequest.onResponse = function() {
	var task = JSON.parse(taskRequest.responseText);
	taskElement = $("#TASK-" + task.taskId);
	// Check if element exists
	if (taskElement.length) {
	    taskElement.data("title", task.title);
	    taskElement.data("assignee", task.assignee);
	    taskElement.data("description", task.description);
	    $("#TASK-" + task.taskId + "-title").html(task.title);
	    $("#TASK-" + task.taskId + "-assignee").html(task.assignee);
	    $("#TASK-" + task.taskId + "-description").html(task.description);
	    displayTask(displayedTask);
	} else {
	    var newTask = new TaskListPanel(task);
	    $("#tasksList").append(newTask.getHTML());
	}
    }
    taskRequest.send(JSON.stringify(task));
    $("#editTaskModal").modal("hide");
}

var deleteTask = function(taskId) {
    var request = getRequest("DELETE", getBaseUrl() + "/tasks");
    request.onResponse = function() {
	responseText = request.responseText;
	if (responseText == "Deleted") {
	    $("#TASK-" + taskId).remove();
	    clearDisplayedTask();
	}
    }
    request.send(taskId);
}

var openCreateNewTaskModal = function() {
    $("#editTaskModalTitle").html("Create New Task");
    $("#modalTaskId").val("");
    $("#modalTaskTitle").val("");
    $("#modalTaskAssignee").val("");
    $("#modalTaskDescription").val("");
    $("#editTaskModal").modal("show");
    $("#modalTaskTitle").focus();
}

var editSelectedTask = function() {
    openEditTaskModal(displayedTask);
}

var openEditTaskModal = function(taskId) {
    $("#editTaskModalTitle").html("Edit Task");
    task = $("#TASK-" + taskId);
    $("#modalTaskId").val(task.data("task-id"));
    $("#modalTaskTitle").val(task.data("title"));
    $("#modalTaskAssignee").val(task.data("assignee"));
    $("#modalTaskDescription").val(task.data("description"));
    $("#editTaskModal").modal("show");
    $("#modalTaskTitle").focus();
}

var clearDisplayedTask = function() {
    $("#selectedTask").empty();
    $(".selectedTaskTools").hide();
}

var TaskListPanel = function(info) {

    this.taskId = info.taskId;
    this.title = info.title;
    this.assignee = info.assignee;
    this.description = info.description;
    
    this.getHTML = function() {
	return '<a href="#" onclick="javascript: displayTask(this.dataset.taskId);" id="TASK-$taskId" data-task-id="$taskId" data-title="$title" data-assignee="$assignee" data-description="$description" class="list-group-item taskListPanel">\
	<p><strong id="TASK-$taskId-title">$title</strong></p>\
	<p>Assignee: <span id="TASK-$taskId-assignee">$assignee</span></p>\
	<p id="TASK-$taskId-description">$description</p>\
	</a>'
		.replace(/\$taskId/g, this.taskId).replace(/\$title/g,
			this.title).replace(/\$assignee/g, this.assignee)
		.replace(/\$description/g, this.description);
    }
}

var goToEditCurrentUser = function() {
    var username = $("#userButton").attr("name");
    window.location = getBaseUrl() + "/pages/user?user=" + username + "&action=edit";
}