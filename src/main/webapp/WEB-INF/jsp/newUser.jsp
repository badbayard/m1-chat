<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Adding User</title>
    <link href="<c:url value="/assets/styles.css" />" rel="stylesheet">
</head>
<body id="back">

<h2>Here you can add some users</h2>
<%--faudrait ajouter un popup pour indiquer que l'utilisateur a ete ajouté--%>
<br>
<form method="post" action="/goto/backoffice/users">
    <label for="user">User's name</label>
    <input type="text" name="user" id="user" required />
    <input type="submit" value="Add">
</form>
<br>
<a href="/index.html">Go back to index</a>

</body>
</html>
