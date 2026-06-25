<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ReservierungsAnzeige" %>

<html>
<body>

<h2>Alle Reservierungen</h2>

<table border="1">

<tr>
    <th>Nr</th>
    <th>Teilnehmer</th>
    <th>Kurs</th>
    <th>Datum</th>
    <th>Uhrzeit</th>
</tr>

<%
List<ReservierungsAnzeige> liste =
    (List<ReservierungsAnzeige>)
        request.getAttribute(
            "reservierungen");

for(ReservierungsAnzeige r : liste){
%>

<tr>

<td>
<%=r.getNummer()%>
</td>

<td>
<%=r.getVorname()%>
<%=r.getNachname()%>
</td>

<td>
<%=r.getKurs()%>
</td>

<td>
<%=r.getDatum()%>
</td>

<td>
<%=r.getUhrzeit()%>
</td>

</tr>

<%
}
%>

</table>
<br>

<a href="index.jsp">
Zurück zur Startseite
</a>

</body>
</html>