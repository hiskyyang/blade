function deleteCode(codeId) {
    var flag = confirm("确定删除吗？");
    if (flag == false) {
        return;
    }

    data = {codeId: codeId};

    post("code/delete", data, window.location, false);
    window.location.reload();
}

function save() {
    if (!isValid()) {
        return false;
    }

    var data = constructData();
    var action = "" == $("#codeId").val() ? "code/add" : "code/update";
    post(action, data, window.opener.location, true);
}

function constructData() {
    return {
        codeId: $("#codeId").val(),
        type: $("#type").val(),
        code: $("#code").val(),
        value: $("#value").val(),
        sequence: $("#sequence").val()
    };
}

function isValid() {
    if (isEmpty("type", "请选择类型！")) {
        return false;
    }

    if (isEmpty("code", "请输入code！")) {
        return false;
    }

    if (isEmpty("value", "请输入value！")) {
        return false;
    }

    if (isEmpty("sequence", "请输入sequence！")) {
        return false;
    }

    return true;
}
