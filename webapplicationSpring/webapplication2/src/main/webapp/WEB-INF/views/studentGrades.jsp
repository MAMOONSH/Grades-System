<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Grades</title>
</head>
<br>
<%@ include file="common/header.jspf"%>
<br>
<body align="center">
<H1>Your courses</H1>
<div align="center">
    <table border="2">
        <caption>Your courses are</caption>
        <thead>
        <tr align="center">
            <th>Course ID</th>
            <th>course name</th>
            <th>grade</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${grades}" var="course">
            <tr align="center">
                <td>${course.id}</td>
                <td><a href="/courseGrades?courseID=${course.id}">${course.courseName}</a></td>
                <td>${course.grade}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>