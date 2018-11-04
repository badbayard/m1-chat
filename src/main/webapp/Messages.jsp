<%@page import="java.lang.String"%>
<%@page import="java.io.IOException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="tp_mif03.Message"%>


<%!  HashMap<String, ArrayList<Message>> salonList = new HashMap<String, ArrayList<Message>>(); %>

<%  String nomSalon=(String)session.getAttribute("nom_salon");
    if(!salonList.containsKey(nomSalon)) {
        ArrayList<Message> listeMessage = new ArrayList<Message>();
        salonList.put(nomSalon, listeMessage);
    }
%>


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
        salonList.get(nomSalon).add(new Message((String) session.getAttribute("pseudo"), (String) request.getParameter("message")));
   }
    for (Message m : salonList.get(nomSalon))
    {
        out.println("<p>" + m.toString() + "</p>");
    }
%>
</body>
</html>