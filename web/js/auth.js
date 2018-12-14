function login() {
    if (isEmpty("name", "请输入账号！")) {
		return;
	}

    if (isEmpty("password", "请输入密码！")) {
        return;
    }

	$("#myForm").submit();
}
