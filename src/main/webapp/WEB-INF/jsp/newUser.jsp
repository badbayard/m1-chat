<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Ajout d'utilisateurs</title>
    <link href="<c:url value="/assets/styles.css" />" rel="stylesheet">
</head>
<body id="back">
<h2>Cette page permet d'ajouter facilement des utilisateurs</h2>

<a href="/index.html">Retour vers l'index</a>


<c:if test="${user == null}" >
    <p>Veuillez ajouter un utilisateur</p>
</c:if>
<c:if test="${user != null}">
    <p>Utilisateur <c:out value="${user}" /> ajouté</p>
</c:if>


<form method="post" action="/goto/backoffice/users">
    <label for="user">Nom d'utilisateur à ajouter: </label>
    <input type="text" name="user" id="user" required />

    <input type="submit" value="Ajouter">

</form>
</body>
</html>
