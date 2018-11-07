<% if (request.getMethod().equals("POST")) { %>
    <jsp:include page="Stockage.jsp"></jsp:include>
    <jsp:forward page="Affichage.jsp"></jsp:forward>
<%} else if (request.getMethod().equals("GET")) { %>
    <jsp:forward page="Affichage.jsp"></jsp:forward>
<%} %>