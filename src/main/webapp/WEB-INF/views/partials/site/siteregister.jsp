<form class="grid-x justify-center" action="/KozmoMusic_war_exploded/accounts/create" method="post" name="registerform">
    <fieldset class="grid-y product-form cell">
        <legend>Pannello registrazione</legend>
        <c:if test="${not empty alert}">
            <%@include file="../../partials/alert.jsp" %>
        </c:if>
        <span>Nome</span>
        <label for="nome" class="field cell w50">
            <input id="nome" name="nome" placeholder="Nome" type="text" required minlength="6" maxlength="30">
        </label>
        <small class="errMsg cell"></small>
        <span>Cognome</span>
        <label for="cognome" class="field cell w50">
            <input id="cognome" name="cognome" placeholder="Cognome" type="text" required minlength="6" maxlength="30">
        </label>
        <small class="errMsg cell"></small>
        <span>Email</span>
        <label for="email" class="field cell w50">
            <input id="email" name="email" placeholder="Email" type="text" required>
        </label>
        <small class="errMsg cell"></small>
        <span>Password</span>
        <label for="password" class="field cell w50">
            <input id="password" name="password" placeholder="Password" type="password" required minlength="6">
        </label>
        <small class="errMsg cell"></small>
        <button type="submit" class="btn" onclick="validateForm(registerform)">Registrati</button>
        <a href="/KozmoMusic_war_exploded/accounts/home">Torna alla home</a>
    </fieldset>
</form>