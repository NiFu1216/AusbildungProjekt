<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Person" %>

<html>

<head>
    <title>Teilnehmer auswählen</title>
    <link rel="stylesheet" href="style/style.css">
</head>

<body>

<div class="container">

<h2>Teilnehmer auswählen</h2>

<form action="teilnehmer" method="post" class="input-select">

<select name="svnr">

<%

List<Person> personen =
(List<Person>)
request.getAttribute("teilnehmerListe");

for(Person p : personen){

%>

<option value="<%=p.getSvnr()%>">

<%=p.getNachname()%>,
<%=p.getVorname()%>

(<%=p.getSvnr()%>)

</option>

<%

}

%>

</select>

<br>

<button type="submit" class="btn">

Reservierung fortsetzen

</button>

</form>

</div>

</body>

</html>