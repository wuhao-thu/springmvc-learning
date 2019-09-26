<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>

<p>
    <a href="emps">List All Employees</a>
</p>

<p>
    <form action="testConversionServiceConverter" method="POST">
        <p>Employee: <input type="text" name="employee" value="Tom-tom@example.com-0-1"></p>
        <p><input type="submit" value="提交"/></p>
    </form>
</p>

<p>
    <a href="testJson">testJson</a>
</p>

<p>
    <form action="testHttpMessageConverter" method="POST" enctype="multipart/form-data">
        File: <input type="file" name="file"/>
        Desc: <input type="text" name="desc"/>
        <input type="submit" value="上传"/>
    </form>
</p>

<p>
    <a href="testResponseEntity">testResponseEntity</a>
</p>

<p>
    <a href="testRequestEntity">testResponseEntity</a>
</p>

<p><a href="i18n">i18n</a></p>

<p><a href="i18n?locale=zh_CN">i18n_中文</a></p>

<p><a href="i18n?locale=en_US">i18n_English</a></p>

<form action="testFileUpload" method="post" enctype="multipart/form-data">
    File: <input type="file" name="file"/>
    Desc: <input type="text" name="desc"/>
    <input type="submit" value="上传"/>
</form>

</body>
</html>
