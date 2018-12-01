<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contenu du message</title>
    <link href="<c:url value="/assets/styles.css" />" rel="stylesheet">
</head>
<body id="back">
<h2>Contenu du messsage <c:out value="${nbId}"/> du salon <c:out value="${salon}" /></h2>



<c:set var="message" value="${applicationScope.gM.getMessageFromSalon(salon, nbId)}" />
<b><c:out value="${message.pseudo}" /></b>
<c:out value="${message.texte}" />



</body>
</html>
