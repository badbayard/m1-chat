<%@page import="java.util.List"%>
<%@page import="java.io.IOException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="tp_mif03.Message"%>


<%!  private List<Message> list = new ArrayList<Message>();%>


<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="refresh" content="5"/>
</head>
<body>
<% out.println("<h1>"+ session.getAttribute("nom_salon")+"</h1>"); %>
<%
    if (request.getMethod().equals("POST"))
    {
        list.add(new Message((String) session.getAttribute("pseudo"), (String) request.getParameter("message")));
    }
    for (Message m : list)
    {
        out.println("<p>" + m.toString() + "</p>");
    }
%>
</body>
</html>