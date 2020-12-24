<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Results</title>
</head>
<style>
    li {
        font-size: larger;
        color: teal;
    }
</style>

<body>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <h1>Search Result</h1>
        <ul>
            <c:forEach items="${dbResults}" var="item">
                <li>${item}</li>
            </c:forEach>
        </ul>
</body>

</html>