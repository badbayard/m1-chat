<%@page import="java.lang.String"%>
<%@page import="java.util.ArrayList"%>
<%@page import="tp_mif03.Message"%>
<%@page import="tp_mif03.SalonList"%>


<%! SalonList sList = new SalonList(); %>

<%  String nomSalon=(String)session.getAttribute("nom_salon");
    sList.addSalon(nomSalon);%>


<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="refresh" content="5"/>
</head>
<body>
<% out.println("<h1>"+ session.getAttribute("nom_salon") +"</h1>");


    if (request.getMethod().equals("POST"))
    {
        sList.addMsg(nomSalon, (String)session.getAttribute("pseudo"), (String)request.getParameter("message"));
    }
    for (Message m : sList.getSalon(nomSalon))
    {
        out.println("<p>" + m.toString() + "</p>");
    }
%>
</body>
</html>