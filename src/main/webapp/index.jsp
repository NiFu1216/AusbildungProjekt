<%@ page contentType="text/html;charset=UTF-8" %>

<html>

<head>
    <title>Ausbildungszentrum</title>
</head>

<body>

<h1>Ausbildungszentrum</h1>

<h3>

Angemeldet als

<%

Boolean admin =
(Boolean)
session.getAttribute("admin");

if(admin != null && admin){

%>

Administrator

<%

}else{

%>

<%=session.getAttribute("vorname")%>
<%=session.getAttribute("nachname")%>

<%
}
%>

</h3>

<br>

<a href="logout">

Logout

</a>

<hr>

<ul>

    <li>
        <a href="seminare">
            Seminar reservieren
        </a>
    </li>

    <li>
        <a href="reservierungen">
            Reservierungen anzeigen
        </a>
    </li>

</ul>

</body>

</html>