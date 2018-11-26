<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
             content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
       <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Movie Index</title>
    <link href="/res/css/style.css" type="text/css" rel="stylesheet" />

</head>
<body>
     <h1><c:out value="${titolo}" /></h1>
        <%-- <h1>${titolo}</h1> --%>
         <ol>
            <c:forEach var="persona" items="${vincitori}">
                <li><c:out value="${persona}" /></li>
            </c:forEach>
        </ol>

        <a href="/site/elenco-film"> ELENCO FILM </a>

        <%-- Altri tag/proprietà di esempio di JSP/JSTL --%>
        <ol>
            <%-- definizione di variabile in JSTL --%>
        <c:set var="count" value="${0}" scope="page" />
         <c:forEach var="persona" items="${vincitori}" varStatus="cont" begin="0" end="${vincitori.size()}" >
            <!-- in your loops -->
            <c:if test="${vincitori[count].length()>7}">
            <li><c:out value="${vincitori[count]}" /> - ( ${cont.count} )</li>
            </c:if>
            <c:set var="count" value="${count + 1}" scope="page"/>
        </c:forEach>
        </ol>

</body>
</html>