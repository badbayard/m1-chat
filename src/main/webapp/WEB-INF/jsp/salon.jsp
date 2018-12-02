<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>The (back)Office - Salon</title>
    <link href="<c:url value="/assets/styles.css" />" rel="stylesheet">
</head>
<body id="back">

<h1>Welcome to The (back)Office - Salon Backlogs</h1>

<h2>Here we have a salon called : ${salon}</h2>
<p>And this is its backlog</p>
<div>
<c:forEach items="${applicationScope.gM.getSalon(salon)}" var="message" >
    <b><c:out value="${message.pseudo}" /></b> : <c:out value="${message.texte}" />
    <br>
    <br>
</c:forEach>
</div>

<a href="/index.html">Go back to index</a>
</body>
</html>
