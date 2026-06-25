<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<body>

<h2>Seminar suchen</h2>

<form action="seminare"
      method="post">

    Kurs:

    <select name="kurs">

        <%
        for(String k :
            (java.util.List<String>)
            request.getAttribute(
                "kurse")) {
        %>

        <option value="<%=k%>">
            <%=k%>
        </option>

        <%
        }
        %>

    </select>

    <br><br>

    Sprache:

    <select name="sprache">

        <%
        for(String s :
            (java.util.List<String>)
            request.getAttribute(
                "sprachen")) {
        %>

        <option value="<%=s%>">
            <%=s%>
        </option>

        <%
        }
        %>

    </select>

    <br><br>

    <button type="submit">
        Weiter
    </button>

</form>

</body>
</html>