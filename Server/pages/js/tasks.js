var displayedTask = "";
var tasks = [];
var currentUser = {};

window.onload = function() {
    refreshTasks();
    setInterval(refreshTasks, 10000);
    $("#deleteButton").click(deleteSelectedTask);
    $("#editButton").click(editSelectedTask);
    $("#completeButton").click(completeSelectedTask);
    getCurrentUser();
}

var onCurrentUserFetched = function(user) {
    currentUser = user;
    $("#userButton").html(user.firstName + " " + user.lastName);
    $("#userButton").attr("name", user.username);
    if (user.admin) {
	$(".admin-option").show();
    }
}

var deleteSelectedTask = function() {
    if (displayedTask != "") {
	deleteTask(displayedTask);
    }
}

var completeSelectedTask = function() {
    if (displayedTask != "") {
	completeTask(displayedTask);
    }
}

var displayTask = function(taskId) {
    displayedTask = taskId;

    var task = null;
    for (var i = 0; i < tasks.length; i++) {
	if (taskId == tasks[i].taskId) {
	    task = tasks[i];
	}
    }

    $(".selectedTaskTools").show();

    selectedTask = document.getElementById("selectedTask");

    taskPanels = $(".taskListPanel");
    taskPanels.removeClass("active");

    clickedTask = $("#TASK-" + taskId);
    selectedTask.innerHTML = clickedTask.html();
    clickedTask.addClass("active");

    $(".completeTaskTool").hide();
    $(".editTaskTool").hide();
    $(".deleteTaskTool").hide();

    // For assignee's
    if (task.assignee == currentUser.username && !task.complete) {
	$(".completeTaskTool").show();
    }

    // For creators
    if (task.creator == currentUser.username) {
	$(".editTaskTool").show();
	$(".deleteTaskTool").show();
    }

    // For admins
    if (currentUser.admin) {
	$(".editTaskTool").show();
	$(".deleteTaskTool").show();
    }
}

var refreshTasks = function() {
    var tasksRequest = getRequest("GET", getBaseUrl() + "/tasks");
    tasksRequest.onResponse = function() {
	var activeTaskId = $("#tasksList").find(".active").data("task-id");
	var tasksInfo = JSON.parse(tasksRequest.responseText);
	tasks = tasksInfo;
	sortTasks(tasks);
	var html = "";
	for (var i = 0; i < tasks.length; i++) {
	    var task = new TaskListPanel(tasks[i]);
	    html += task.getHTML();
	}
	$("#tasksList").html(html);
	if (activeTaskId != undefined) {
	    displayTask(activeTaskId);
	}
    }
    tasksRequest.send();
}

var sortTasks = function(tasks) {
    var compareTasks = function(a, b) {
	if (a.complete ^ b.complete) {
	    return a.complete ? 1 : -1;
	}
	return 0;
    }
    tasks.sort(compareTasks);
}

var submitTaskForm = function() {
    var task = {};
    task["taskId"] = $("#modalTaskId").val();
    task["title"] = $("#modalTaskTitle").val();
    task["assignee"] = $("#modalTaskAssignee").val();
    task["description"] = $("#modalTaskDescription").val();
    var action = $("#editTaskModal").data("action");
    var taskRequest = getRequest("POST", getBaseUrl() + "/tasks?action="
	    + action);
    taskRequest.onResponse = function() {
	var task = JSON.parse(taskRequest.responseText);
	taskElement = $("#TASK-" + task.taskId);
	var newTask = new TaskListPanel(task);
	// Check if element exists
	if (taskElement.length) {
	    taskElement.replaceWith(newTask.getHTML());
	    for (var i = 0; i < tasks.length; i++) {
		if (tasks[i].taskId == task.taskId) {
		    tasks[i] = task;
		    break;
		}
	    }
	    displayTask(displayedTask);
	} else {
	    tasks.push(task);
	    $("#tasksList").append(newTask.getHTML());
	}
    }
    taskRequest.send(JSON.stringify(task));
    $("#editTaskModal").modal("hide");
}

var deleteTask = function(taskId) {
    var request = getRequest("DELETE", getBaseUrl() + "/tasks");
    request.onResponse = function() {
	var responseText = request.responseText;
	if (responseText == "Deleted") {
	    $("#TASK-" + taskId).remove();
	    clearDisplayedTask();
	}
    }
    request.send(taskId);
}

var completeTask = function(taskId) {
    var request = getRequest("POST", getBaseUrl() + "/tasks?action=complete");
    var task = {};
    task.taskId = taskId;
    request.onResponse = function() {
	var responseText = request.responseText;
	var task = JSON.parse(responseText);

	for (var i = 0; i < tasks.length; i++) {
	    if (tasks[i].taskId == task.taskId) {
		tasks[i] = task;
		break;
	    }
	}

	var taskElement = new TaskListPanel(task);
	var oldElement = $("#TASK-" + task.taskId);
	oldElement.replaceWith(taskElement.getHTML());
	displayTask(taskId);
    }
    request.send(JSON.stringify(task));
}

var openCreateNewTaskModal = function() {
    $("#editTaskModalTitle").html("Create New Task");
    $("#modalTaskId").val("");
    $("#modalTaskTitle").val("");
    $("#modalTaskAssignee").val("");
    $("#modalTaskDescription").val("");
    $("#editTaskModal").data("action", "add");
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
    $("#editTaskModal").data("action", "edit");
    $("#editTaskModal").modal("show");
    $("#modalTaskTitle").focus();
}

var clearDisplayedTask = function() {
    $("#selectedTask").empty();
    $(".completeTaskTool").hide();
    $(".editTaskTool").hide();
    $(".deleteTaskTool").hide();
    $(".selectedTaskTools").hide();
}

var TaskListPanel = function(info) {

    this.taskId = info.taskId;
    this.title = info.title;
    this.creator = info.creator;
    this.assignee = info.assignee;
    this.description = info.description;
    this.complete = info.complete;

    this.getHTML = function() {
	var html = '<a href="#" onclick="javascript: displayTask(this.dataset.taskId);" id="TASK-$taskId" data-task-id="$taskId" data-title="$title" data-assignee="$assignee" data-description="$description" class="list-group-item taskListPanel">\
	<p><strong id="TASK-$taskId-title">';
	if (this.complete) {
	    html += "<del>";
	}
	html += '$title';
	if (this.complete) {
	    html += "</del>";
	}
	html += '</strong></p>\
	<p>Creator: <span i="TASK-$taskId-creator">$creator</span></p>\
	<p>Assignee: <span id="TASK-$taskId-assignee">$assignee</span></p>\
	<p id="TASK-$taskId-description">$description</p>\
	</a>';

	html = html.replace(/\$taskId/g, this.taskId).replace(/\$title/g,
		this.title).replace(/\$creator/g, this.creator).replace(
		/\$assignee/g, this.assignee).replace(/\$description/g,
		this.description);
	return html;
    }
}

var goToEditCurrentUser = function() {
    var username = $("#userButton").attr("name");
    window.location = getBaseUrl() + "/pages/user?user=" + username
	    + "&action=edit";
}