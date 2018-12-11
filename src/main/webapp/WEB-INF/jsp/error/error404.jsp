<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>System Error</title>
    <link href="<c:url value="/assets/styles.css" />" rel="stylesheet">
</head>
<body id="back">

    <h1>${exception.errCode} : System Errors</h1>

    <h2>${exception.errMsg}</h2>

<a href="/ClientPreAjax/interface.html">Go back to index</a>

</body>

</html>
