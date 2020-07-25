function parseJson(form) {
    var data = form.serializeArray();

    var returnJson = {};
    for (idx = 0; idx < data.length; idx++) {
        returnJson[data[idx].name] = data[idx].value
    }

    var json = JSON.stringify(returnJson);
    return json;
}

function fetchGet() {
    if (typeof success !== "function") {
        success = function() { };
    }
    if (typeof error !== "function") {
        error = function() { };
    }

    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(res => res.json())
        .then(success)
        .catch(error);
}

function fetchPost(url, body, success, error) {
    if (typeof success !== "function") {
        success = function() { };
    }
    if (typeof error !== "function") {
        error = function() { };
    }

    fetch(url, {
        method: 'POST',
        body: body,
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(res => res.json())
        .then(success)
        .catch(error);
}

function fetchDelete(url, data, success, error) {
    if (typeof success !== "function") {
        success = function() { };
    }
    if (typeof error !== "function") {
        error = function() { };
    }

    fetch(url, {
        method: 'DELETE',
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(success)
}

$(function() {
    var index_url = $("#index_url").attr("href");
    $("#headerNavi a.nav-link[href='" + index_url + "']").addClass("active");
})
