<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, model.Person" %>
<%
    List<Person> kundenListe = (List<Person>) request.getAttribute("kundenListe");
    Person bearbeiterKunde = (Person) request.getAttribute("bearbeiterKunde");
    String suchbegriff = (String) request.getAttribute("suchbegriff");
    boolean isEdit = (bearbeiterKunde != null);
%>
<!DOCTYPE html>
<html>
<head>
    <title>Kundenverwaltung (Admin)</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style/style.css">
    <style>
        .form-grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 10px 15px;
            text-align: left;
        }
        .form-group-full {
            grid-column: span 2;
        }
        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 2px;
            font-size: 13px;
        }
        .form-group input[type="text"] {
            width: 100%;
            padding: 6px 8px;
            box-sizing: border-box;
        }
    </style>
</head>
<body>

<div class="card" style="max-width: 650px; margin: 20px auto;">
    <h2>Kundenverwaltung (Admin)</h2>
    <hr>

    <h3><%= isEdit ? "Kundendaten ändern" : "Neuen Kunden eintragen" %></h3>

    <form action="<%= request.getContextPath() %>/kunden" method="post">
        <input type="hidden" name="action" value="<%= isEdit ? "update" : "create" %>">

        <div class="form-grid">
            <div class="form-group form-group-full">
                <label for="svnr">SVNr:</label>
                <input type="text" id="svnr" name="svnr" value="<%= isEdit ? bearbeiterKunde.getSvnr() : "" %>" <%= isEdit ? "readonly" : "required" %>>
            </div>

            <div class="form-group">
                <label for="vorname">Vorname:</label>
                <input type="text" id="vorname" name="vorname" value="<%= isEdit ? bearbeiterKunde.getVorname() : "" %>" required>
            </div>

            <div class="form-group">
                <label for="nachname">Nachname:</label>
                <input type="text" id="nachname" name="nachname" value="<%= isEdit ? bearbeiterKunde.getNachname() : "" %>" required>
            </div>

            <div class="form-group">
                <label for="postleitzahl">PLZ:</label>
                <input type="text" id="postleitzahl" name="postleitzahl" value="<%= isEdit ? bearbeiterKunde.getPlz() : "" %>">
            </div>

            <div class="form-group">
                <label for="ort">Ort:</label>
                <input type="text" id="ort" name="ort" value="<%= isEdit ? bearbeiterKunde.getOrt() : "" %>">
            </div>

            <div class="form-group">
                <label for="strasse">Straße:</label>
                <input type="text" id="strasse" name="strasse" value="<%= isEdit ? bearbeiterKunde.getStrasse() : "" %>">
            </div>

            <div class="form-group">
                <label for="hausnummer">Hausnummer:</label>
                <input type="text" id="hausnummer" name="hausnummer" value="<%= isEdit ? bearbeiterKunde.getHausnummer() : "" %>">
            </div>
        </div>

        <br>
        <button type="submit" class="btn"><%= isEdit ? "Speichern" : "Anlegen" %></button>
        <% if(isEdit) { %>
        <a href="<%= request.getContextPath() %>/kunden" class="btn" style="background:#6c757d;">Abbrechen</a>
        <% } %>
    </form>

    <hr>

    <h3>Kunden suchen</h3>
    <form action="<%= request.getContextPath() %>/kunden" method="get">
        <input type="text" name="q" placeholder="Name oder SVNr suchen..." value="<%= suchbegriff != null ? suchbegriff : "" %>" style="padding: 8px; width: 65%;">
        <button type="submit" class="btn">Suchen</button>
    </form>

    <br>
    <table border="1" cellpadding="6" style="width:100%; border-collapse: collapse;">
        <tr>
            <th>SVNr</th>
            <th>Name</th>
            <th>Adresse</th>
            <th>Aktion</th>
        </tr>
        <% if (kundenListe != null && !kundenListe.isEmpty()) {
            for (Person k : kundenListe) { %>
        <tr>
            <td><%= k.getSvnr() %></td>
            <td><%= k.getNachname() %>, <%= k.getVorname() %></td>
            <td><%= k.getStrasse() %> <%= k.getHausnummer() %>, <%= k.getPlz() %> <%= k.getOrt() %></td>
            <td>
                <a href="<%= request.getContextPath() %>/kunden?action=edit&svnr=<%= k.getSvnr() %>" class="btn">Bearbeiten</a>
            </td>
        </tr>
        <%  }
        } else { %>
        <tr><td colspan="4">Keine Kunden gefunden.</td></tr>
        <% } %>
    </table>

    <br>
    <a href="<%= request.getContextPath() %>/startseite.jsp">Zurück zur Startseite</a>
</div>

</body>
</html>