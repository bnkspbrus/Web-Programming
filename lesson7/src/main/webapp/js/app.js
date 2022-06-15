window.notify = function(message) {
    $.notify(message, {
        position: "right bottom",
        className: "success"
    });
}

window.enterOrRegisterSubmit = function(action) {
    return function() {
        const login = $(this).find("input[name='login']").val();
        const password = $(this).find("input[name='password']").val();
        const $error = $(this).find(".error");

        post("", {action, login, password}, function(response) {
            // alert(response)
            if (response["error"]) {
                $error.text(response["error"]);
            }
        }, "json");

        return false;
    }
}

window.post = function(url, data, success, dataType) {
    $.post(url, data, function(response) {
        success(response);
        if (response["redirect"]) {
            location.href = response["redirect"];
        }
    }, dataType);
}