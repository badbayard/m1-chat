<%@page import="java.lang.String"%>
<%@page import="java.util.ArrayList"%>
<%@page import="tp_mif03.Message"%>
<%@page import="tp_mif03.bean.GestionMessages"%>


<%  String nomSalon=(String)session.getAttribute("nom_salon");
    GestionMessages.addMsg(nomSalon, (String)session.getAttribute("pseudo"), (String)request.getParameter("message"));
%>
