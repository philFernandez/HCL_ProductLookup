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


    table.result,
    table.result th,
    table.result td {
        border: 1px solid black;
    }
</style>

<body>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:choose>
            <c:when test="${not empty dbResults}">
                <h1>Search Result</h1>
                <table class="result">
                    <tr>
                        <th>Item</th>
                        <th>Price</th>
                        <th>Qty</th>
                    </tr>
                    <tr>
                        <c:forEach items="${dbResults}" var="item">
                            <td>${item}</td>
                        </c:forEach>
                    </tr>
                </table>
            </c:when>
            <c:otherwise>
                <h1>No Product Found</h1>
            </c:otherwise>
        </c:choose>
        <%@include file="_search.html" %>
</body>

</html>