<form method="post" action="/KozmoMusic_war_exploded/category/create" name="categoryaddform">
<fieldset class="grid-y product-form cell">
<legend>Crea Categoria</legend>
<c:if test="${not empty alert}">
    <%@include file="../../partials/alert.jsp" %>
</c:if>
    <label for="tipologia" class="field cell w50">
        <input id="tipologia" name="tipologia" placeholder="Tipologia" type="text" required minlength="3" maxlength="30">
    </label>
    <small class="errMsg cell"></small>
    <button type="submit" class="btn" onclick="validateForm(categoryaddform)">Crea Categoria</button>
</fieldset>
</form>