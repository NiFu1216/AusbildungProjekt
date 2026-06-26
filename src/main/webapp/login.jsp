<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Person" %>

<html>
<head>
    <title>Anmeldung</title>
</head>

<body>

<h2>Anmeldung</h2>

<form action="login" method="post">

<select name="svnr">

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

<br><br>

<button>Anmelden</button>

</form>

</body>
</html>