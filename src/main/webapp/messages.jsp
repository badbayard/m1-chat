<%@page import="java.lang.String"%>
<%@page import="java.util.ArrayList"%>
<%@page import="tp_mif03.Model.Message"%>
<%@page import="tp_mif03.Model.GestionMessages"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="refresh" content="5"/>
</head>
<body>
<c:forEach items="${applicationScope.gM.getSalon(sessionScope.nom_salon)}" var="message" >
    <b><c:out value="${message.pseudo}" /></b> : <c:out value="${message.texte}" />
    <br/><br/>
</c:forEach>
</body>
</html>