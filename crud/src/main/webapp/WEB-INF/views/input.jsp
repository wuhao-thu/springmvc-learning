<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>input</title>
</head>
<body>
<form:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute="employee">
    <!-- 如果 id 为空（添加），才可以编辑 lastName -->
    <c:if test="${employee.id == null}">
        <p>LastName:<form:input path="lastName"/> <form:errors path="lastName"/></p>
    </c:if>
    <!-- 如果 id 不为空（修改），应该发送 PUT 请求 -->
    <c:if test="${employee.id != null}">
        <form:hidden path="id"/>
        <input type="hidden" name="_method" value="PUT">
    </c:if>
    <p>Email:<form:input path="email"/> <form:errors path="email"/></p>
    <%
        Map<String, String> genders = new HashMap<>();
        genders.put("1", "Male");
        genders.put("0", "Female");
        request.setAttribute("genders", genders);
    %>
    <p>Gender:<form:radiobuttons path="gender" items="${genders}"/></p>
    <p>Department:<form:select path="department.id" items="${departments}" itemLabel="departmentName" itemValue="id"></form:select></p>
    <p>Birthday: <form:input path="birth"/> <form:errors path="birth"/></p>
    <p>
        <input type="submit" value="submit">
    </p>
</form:form>
</body>
</html>
