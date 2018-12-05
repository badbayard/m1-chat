<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message Backlog</title>
    <link href="<c:url value="/assets/styles.css" />" rel="stylesheet">
</head>
<body id="back">

<h2>Message's  n°<c:out value="${nbId}"/> from <c:out value="${salon}" /></h2>

<c:set var="message" value="${applicationScope.gM.getMessageFromSalon(salon, nbId)}" />
<b><c:out value="${message.pseudo}" /></b>
<c:out value="${message.texte}" />

<a href="/index.html">Go back to index</a>
</body>
</html>
