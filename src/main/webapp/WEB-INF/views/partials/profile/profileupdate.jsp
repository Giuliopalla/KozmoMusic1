<%@ page import="model.utente.UtenteSession" %>
<form action="/KozmoMusic_war_exploded/accounts/update" method="post" name="profileupdateform">
    <fieldset class="grid-y cell product-form">

        <legend>Aggiorna Dati</legend>
        <c:if test="${not empty alert}">
            <%@include file="../../partials/alert.jsp" %>
        </c:if>
        <label for="nomeUtente" class="field cell w50">
            <b>Nome:</b><input id="nomeUtente" name="nomeUtente" placeholder="Nome" type="text" required minlength="5"
                               maxlength="30">
        </label>
        <small class="errMsg cell"></small>
        <label for="cognomeUtente" class="field cell w50">
            <b>Cognome:</b><input id="cognomeUtente" name="cognomeUtente" placeholder="Cognome" type="text" required
                                  minlength="5" maxlength="30">
        </label>
        <small class="errMsg cell"></small>
        <label for="emailUtente" class="field cell w50">
            <b>Email:</b><input id="emailUtente" name="emailUtente" placeholder="Email" type="text" required>
        </label>
        <small class="errMsg cell"></small>
            <% UtenteSession utenteSessione=(UtenteSession) request.getSession().getAttribute("utenteSession");
            if(utenteSessione.isAdmin()){%>
        <input type="hidden" value="${utente1.id}" name="idUtente">
        <label class="field cell w50">
            <a><b>Admin:</b><input name="adminUtente" type="radio" value="true">True
                <input name="adminUtente" type="radio" value="false" checked>False</a>
        </label>
        <small class="errMsg cell"></small>
            <%}%>
        <button class="btn" type="submit" onclick="validateForm(profileupdateform)">Aggiorna</button>
</form>
</fieldset>