<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Teilnehmer" %>

<html>
<body>

<h2>Teilnehmer auswählen</h2>

<form action="teilnehmer"
      method="post">

<%
List<Teilnehmer> liste =
    (List<Teilnehmer>)
        request.getAttribute(
                "teilnehmerListe");

for(Teilnehmer t : liste){
%>

<input type="radio"
       name="svnr"
       value="<%=t.getSvnr()%>"
       required>

SVNr:
<%=t.getSvnr()%>

(Kunde:
<%=t.getKundennummer()%>)

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