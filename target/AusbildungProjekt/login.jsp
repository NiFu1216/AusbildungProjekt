<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Person" %>

<html>
<head>
    <title>Anmeldung</title>
    <link rel="stylesheet" href="style/style.css">
</head>

<body>

<div class="container">
<h2>Anmeldung</h2>
<br>

<form action="login" method="post" class="input-select">

<select name="svnr" class="input-select">

<%
List<Person> personen = (List<Person>) request.getAttribute("personen");

for (Person p : personen) {
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

<button class="btn">Anmelden</button>

</form>

</div>

</body>
</html>