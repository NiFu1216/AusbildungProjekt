<%@ page contentType="text/html;charset=UTF-8" %>

<html>

<head>
    <title>Ausbildungszentrum</title>
    <link rel="stylesheet" href="style/style.css">
</head>

<body>

<div class="container">

    <h1>Ausbildungszentrum</h1>

    <h3>

        Angemeldet als

        <%
            Boolean admin = (Boolean) session.getAttribute("admin");

            if(admin != null && admin){
        %>

        Administrator

        <%
        }else if(session.getAttribute("vorname") != null){
        %>

        <%=session.getAttribute("vorname")%>
        <%=session.getAttribute("nachname")%>

        <%
        }else{
        %>

        Gast

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

        <% if(admin != null && admin) { %>
        <li>
            <a href="kunden">
                Kundenverwaltung
            </a>
        </li>
        <% } %>

    </ul>

</div>

</body>

</html>