<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/public.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/user/user.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/public.css">
    <title>用户管理</title>
</head>
<body>
    <jsp:include page="../head.jsp"/>

    <div class="panel">
        <div class="panel-title">用户管理</div>
        <div class="panel-searchForm">
            <form id="myForm" method="get" action="user/list">
                <table>
                    <tr>
                        <td>用户名:</td>
                        <td><input id="name" name="name" value="${user.name}"/></td>
                    </tr>
                    <tr>
                        <td>分组:</td>
                        <td>
                            <select id="team" name="team">
                                <option value="">请选择</option>
                                <c:forEach var="code" items="${teams}">
                                    <option value="${code.code}" ${user.team==code.code?"selected":""}>${code.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>级别:</td>
                        <td>
                            <select id="level" name="level">
                                <option value="">请选择</option>
                                <c:forEach var="code" items="${levels}">
                                    <option value="${code.code}" ${user.level==code.code?"selected":""}>${code.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>角色:</td>
                        <td><input type="radio" name="role" value="1" ${user.role == '1' ?"checked=checked":""} />用户
                            <input type="radio" name="role" value="0" ${user.role == '0' ?"checked=checked":""} />管理员
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="查询"/>&nbsp;&nbsp;&nbsp;&nbsp;
                            <c:if test="${sessionScope.user.role==0}">
                                <input type="button" value="添加" onclick="window.open('user/user','_blank', 'height=220, width=500, top=10, left=10')"/>
                            </c:if>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <div class="panel-dataGrid">
            <table width="100%">
                <tr>
                    <td class="panel-dataGrid-headerColumn" width="20%">账号</td>
                    <td class="panel-dataGrid-headerColumn" width="20%">分组</td>
                    <td class="panel-dataGrid-headerColumn" width="20%">级别</td>
                    <td class="panel-dataGrid-headerColumn" width="20%">角色</td>
                    <td class="panel-dataGrid-headerColumn" width="20%">操作</td>
                </tr>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td class="panel-dataGrid-dataColumn">${user.name}</td>
                        <td class="panel-dataGrid-dataColumn">${user.team}</td>
                        <td class="panel-dataGrid-dataColumn">${user.level}</td>
                        <td class="panel-dataGrid-dataColumn">${user.role=='admin'?"管理员":"用户"}</td>
                        <td class="panel-dataGrid-dataColumn">
                            <a href="javascript:void(0)" onclick="deleteUser(${user.userId})">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="javascript:void(0)" onclick="window.open('user/user?userId=${user.userId}', '_blank', 'height=220, width=500, top=10, left=10')">更新</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>
