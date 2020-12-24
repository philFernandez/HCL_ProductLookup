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

    table,
    th,
    td {
        border: 1px solid black;
    }
</style>

<body>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <h1>Search Result</h1>
        <table>
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
</body>

</html>