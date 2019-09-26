<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<body>
<h2>Hello World!</h2>

<a href="testRequestMapping">testRequestMapping</a>
<br><br>

<a href="testParams?username=Tom&age=15">testParams</a>
<br><br>

<a href="testAntPath/xyz">testAntPath</a>
<br><br>

<a href="testPathVariable/455">testPathVariable</a>
<br><br>

<form action="testMethod" method="post">
    <input type="submit" value="testMethod"/>
</form>
<br><br>

<form action="testRestPut" method="post">
    <input type="hidden" name="_method" value="PUT"/>
    <input type="submit" value="Test REST PUT"/>
</form>
<br><br>

<a href="testRequestParam?name=Tom&age=18">testRequestParam</a>
<br><br>

<a href="testRequestHeader">testRequestHeader</a>
<br><br>

<a href="testCookieValue">testCookieValue</a>
<br><br>

<p>
    <form action="testPOJO" method="post">
        <p>username:<input type="text" name="name" value="Tom"></p>
        <p>password:<input type="password" name="password">
        <p>email:<input type="text" name="email" value="abc@example.com"></p>
        <p>age:<input type="text" name="age" value="20"></p>
        <p>province:<input type="text" name="address.province" value="四川"></p>
        <p>city:<input type="text" name="address.city" value="成都"></p>
        <p><input type="submit" value="提交"> </p>
    </form>
</p>
<br><br>

<a href="testServletAPI">testServletAPI</a>
<br><br>

<a href="testModelAndView">testModelAndView</a>
<br><br>

<a href="testMap">testMap</a>
<br><br>

<a href="testSessionAttributes">testSessionAttributes</a>
<br><br>

</body>
</html>
