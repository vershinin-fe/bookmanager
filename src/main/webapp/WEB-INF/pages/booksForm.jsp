<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Books Page</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>

<h1>Book List</h1>

<form:form action="/books/new" method="get">
    <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message text="Add Book"/>"/>
        </td>
    </tr>
</form:form>

<c:choose>
    <c:when test="${!empty listBooks}">
        <table class="tg">
            <tr>
                <th width="80">ID</th>
                <th width="120">Title</th>
                <th width="120">Description</th>
                <th width="120">Author</th>
                <th width="60">ISBN</th>
                <th width="60">Year</th>
                <th width="60">Read</th>
                <th width="60">Edit</th>
                <th width="60">Delete</th>
            </tr>
            <c:forEach items="${listBooks}" var="book">
                <tr>
                    <td align="center">${book.id}</td>
                    <td><a href="/bookdata/${book.id}">${book.title}</a></td>
                    <td width="25%">${book.description}</td>
                    <td>${book.author}</td>
                    <td align="center">${book.isbn}</td>
                    <td align="center">${book.printYear}</td>
                    <c:choose>
                        <c:when test="${book.readAlready == true}">
                            <td align="center">V</td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                    <td align="center"><a href="<c:url value='/edit/${book.id}'/>">Edit</a></td>
                    <td align="center"><a href="<c:url value='/remove/${book.id}'/>">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
        <c:forEach var = "i" begin = "1" end = "${paging.pagesCount}">
            <c:choose>
                <c:when test="${i != paging.currentPage + 1}">
                    <a href="<c:url value='/books/${i - 1}'/>">${i}</a>
                    </c:when>
                <c:otherwise>
                    ${i}
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <br>
    </c:when>
    <c:otherwise>
        <h1>Nothing to show</h1>
    </c:otherwise>
</c:choose>

<form action="/books/search" method="get">
    <td>
        <input name="searchstring" value="${searchString}"/>
    </td>
    <td colspan="2">
        <input type="submit" value="<spring:message text="Apply"/>"/>
    </td>
</form>

</body>
</html>
