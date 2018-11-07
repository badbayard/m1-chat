<%@page import="java.lang.String"%>
<%@page import="java.util.ArrayList"%>
<%@page import="tp_mif03.Message"%>
<%@page import="tp_mif03.bean.GestionMessages"%>

<%--<jsp:useBean id="GestionMessages" scope="application" class="tp_mif03.bean.GestionMessages"></jsp:useBean>--%>

<%  String nomSalon=(String)session.getAttribute("nom_salon");%>


<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="refresh" content="5"/>
</head>
<body>
<% out.println("<h1>"+ session.getAttribute("nom_salon") +"</h1>");

    for (Message m : GestionMessages.getSalon(nomSalon))
    {
        out.println("<p>" + m.toString() + "</p>");
    }
%>
</body>
</html>