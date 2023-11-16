<form   action="/KozmoMusic_war_exploded/accounts/login" method="post" name="loginform">
    <fieldset class="grid-y  cell product-form">
        <legend>Pannello Login</legend>
        <c:if test="${not empty alert}">
            <%@include file="../../partials/alert.jsp" %>
        </c:if>
        <span>Email</span>
        <label for="email" class="field">
            <input type="text" name="email" id="email" placeholder="Email" required>
        </label>
        <small class="errMsg cell"></small>
        <span>Password</span>
        <label for="password" class="field">
            <input type="password" name="password" id="password" placeholder="Password" required minlength="6">
        </label>
        <small class="errMsg cell"></small>
        <button type="submit" class="btn" onclick="validateForm(loginform)">Accedi</button>
        <h2>Non sei registrato?<a href="/KozmoMusic_war_exploded/accounts/register">Registrati</a></h2>
        <a href="/KozmoMusic_war_exploded/accounts/home">Torna alla home</a>
    </fieldset>
</form>