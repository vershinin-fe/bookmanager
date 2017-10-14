<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<html>
<head>
  <title>New Book</title>

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

  <script>
      <%@include file="/resources/validate_new_form.js"%>
      <%@include file="/resources/validate_fields.js"%>
  </script>

</head>
<body>
<h1>Add a Book</h1>

<c:url var="addAction" value="/books/add"/>

<form:form id="addForm" onsubmit="return validateForm()" action="${addAction}" modelAttribute="newBook">
  <table>
    <tr>
      <td>
        <form:label path="title">
          <spring:message text="Title"/>
        </form:label>
      </td>
      <td>
        <form:input id="title" path="title"/>
      </td>
      <td>
        <label id="titleError">
        </label>
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="description">
          <spring:message text="Description"/>
        </form:label>
      </td>
      <td>
        <form:input path="description"/>
      </td>
      <td>
        <label id="descriptionError">
        </label>
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="author">
          <spring:message text="Author"/>
        </form:label>
      </td>
      <td>
        <form:input path="author"/>
      </td>
      <td>
        <label id="authorError">
        </label>
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="isbn">
          <spring:message text="ISBN"/>
        </form:label>
      </td>
      <td>
        <form:input path="isbn"/>
      </td>
      <td>
        <label id="isbnError">
        </label>
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="printYear">
          <spring:message text="Print Year"/>
        </form:label>
      </td>
      <td>
        <form:input path="printYear"/>
      </td>
      <td>
        <label id="yearError">
        </label>
      </td>
    </tr>
    <tr>
      <td colspan="2">
          <input type="submit" value="<spring:message text="Add Book"/>"/>
      </td>
    </tr>
  </table>
</form:form>
</body>
</html>