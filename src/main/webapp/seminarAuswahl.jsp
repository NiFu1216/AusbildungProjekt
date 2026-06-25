<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Seminar" %>

<html>
<body>

<h2>Seminar auswählen</h2>

<form action="seminarAuswahl" method="post">

<%
List<Seminar> seminare =
    (List<Seminar>) request.getAttribute(
        "seminare");

for(Seminar s : seminare){
%>

<input type="radio"
       name="seminar"
       value="<%=s.getDatum()%>|<%=s.getUhrzeit()%>"
       required>

<%=s.getKursName()%>
|
<%=s.getDatum()%>
|
<%=s.getUhrzeit()%>

<input type="hidden"
       name="uhrzeit"
       value="<%=s.getUhrzeit()%>">

<br>

<%
}
%>

<br>

<button type="submit">
Weiter
</button>

</form>

</body>
</html>