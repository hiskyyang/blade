function save() {
    if (!isValid()) {
        return false;
    }

    var data = constructData();
    var action = "" == $("#userId").val() ? "user/add" : "user/update";
    post(action, data, window.opener.location, true);
}

function constructData() {
    return {
        userId: $("#userId").val(),
        name: $("#name").val(),
        password: $("#password").val(),
        team: $("#team").val(),
        level: $("#level").val(),
        role: $('input[name="role"]:checked').val(),
        firstLog: $('input[name="firstLog"]:checked').val()
    };
}

function deleteUser(userId) {
    var flag = confirm("确定删除吗？");
    if (flag == false) {
        return;
    }

    data = {userId: userId};

    post("user/delete", data, window.location, false);
}

function isValid() {
    if (isEmpty("name", "请输入账号！")) {
        return false;
    }

    if (isEmpty("password", "请输入密码！")) {
        return false;
    }

    if (isEmpty("team", "请选择分组！")) {
        return false;
    }


    if (isEmpty("level", "请选择级别！")) {
        return false;
    }

    if (isRadioEmpty("role", "请选择角色！")) {
        return false;
    }

    if (isRadioEmpty("firstLog", "请选择是否初次登录！")) {
        return false;
    }

    return true;
}