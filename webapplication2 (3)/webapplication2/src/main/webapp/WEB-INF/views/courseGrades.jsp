<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Class Grades</title>
</head>
<%@ include file="common/header.jspf"%>
<body align="center">





<div align="center">
    <table border="2">
        <caption>Class Grades</caption>

        <thead>
        <tr align="center">
            <th>Student Name</th>
            <th>Course Name</th>
            <th>Grade</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${classGrades}" var="classGrade">
            <tr align="center">
                <td>${classGrade.studentName}</td>
                <td>${classGrade.courseName}</td>
                <td>${classGrade.grade}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<br>
<br>
<div align="center">
    <table border="2">
        <caption>Class Grades</caption>

        <thead>
        <tr align="center">
            <th>Average</th>
            <th>Median</th>
            <th>Max</th>
            <th>Min</th>
        </tr>
        </thead>

        <tbody>
            <tr align="center">
                <td>${statistics.average}</td>
                <td>${statistics.median}</td>
                <td>${statistics.max}</td>
                <td>${statistics.min}</td>
            </tr>
        </tbody>
    </table>
</div>
<br>
<lable><a href="/studentGrades">Return to your grades</a></lable>
</body>
</html>
