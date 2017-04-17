function getTasks(limit, offset) {
    $.ajax({
        method: 'GET',
        url: '/tasks',
        data: {
            limit: limit,
            offset: offset
        }
    }).done(function (response) {
        return response;
    }).fail(function () {
        throw new Error("error on loading tasks");
    });
}

function getTask(id, func) {
    $.ajax({
        method: 'GET',
        url: '/tasks?id=' + id
    }).done(function () {
        func();
    }).fail(function () {
        console.log("error on loading task");
    });
}

function createTask(name, description, func) {
    $.ajax({
        method: 'POST',
        url: '/tasks',
        data: {
            name: name,
            description: description
        },
        contentType: "application/json;charset=UTF-8"
    }).done(function () {
        func();
    }).fail(function () {
        console.log("error on creation task");
    });
}

function updateTask(id, name, description, func) {
    $.ajax({
        method: 'PUT',
        url: '/tasks',
        data: {
            id: id,
            name: name,
            description: description
        }
    }).done(function () {
        func();
    }).fail(function () {
        console.log("error on updating task");
    });
}

function deleteTask(id, func) {
    $.ajax({
        method: 'DELETE',
        url: '/tasks',
        data: {
            id: id
        }
    }).done(function () {
        func();
    }).fail(function () {
        console.log("error on deleting task");
    });
}