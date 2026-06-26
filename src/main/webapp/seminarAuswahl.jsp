<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Seminar" %>

<html>
<head>
    <title>Seminar</title>
    <link rel="stylesheet" href="style/style.css">
</head>

<body>

<div class="container-big">

<h2>Seminar auswählen</h2>

<form action="seminarAuswahl" method="post" class="input-select">

<%
List<Seminar> seminare =
    (List<Seminar>) request.getAttribute(
        "seminare");

for(Seminar s : seminare){
%>

<label class="radio-option">
<input type="radio"
       name="seminar"
       value="<%=s.getDatum()%>|<%=s.getUhrzeit()%>"
       required>

<%=s.getKursName()%>
|
<%=s.getDatum()%>
|
<%=s.getUhrzeit()%>
</label>

<input type="hidden"
       name="uhrzeit"
       value="<%=s.getUhrzeit()%>">

<br>

<%
}
%>

<br>

<button type="submit" class="btn">
Weiter
</button>

<br><br>

<a href="startseite.jsp">Zur Startseite</a>

</form>

</div>

</body>
</html>