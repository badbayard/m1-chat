
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>The Office - Chat</title>
    <link href="<c:url value="/assets/styles.css" />" rel="stylesheet">
</head>
<body id="main">
<h1>This is The Office's chat</h1>
<p><b>Salon : ${sessionScope.nom_salon}</b></p>
<iframe src="messages.jsp" name="messages" style="width: 600px; height: 300px;"></iframe>

<form method="post" action="NouveauMessage">
    <p>
        Ecrivez votre message :
        <input type="text" name="message">
        <input type="submit" value="Envoyer">
    </p>
</form>
<p><a href="Deconnection" name="Deconnection">Déconnexion</a></p>
</body>
</html>
