<form action="/KozmoMusic_war_exploded/category/update" method="post" name="categoryupdateform">
    <fieldset class="grid-y cell product-form">
        <legend>Aggiorna Categoria</legend>
        <c:if test="${not empty alert}">
            <%@include file="../../partials/alert.jsp" %>
        </c:if>

        <input type="hidden" value="${categoria1.idcategoria}" name="idcategoria">
        <label for="tipologia" class="field cell w50">
            <b>Tipologia:</b><input id="tipologia" name="tipologia" placeholder="Tipologia" type="text" required
                                    minlength="3" maxlength="30">
        </label>
        <small class="cell errMsg"></small>
        <button class="btn" type="submit" onclick="validateForm(categoryupdateform)">Aggiorna</button>
    </fieldset>
</form>
<form action="/KozmoMusic_war_exploded/category/delete" method="post">
    <input type="hidden" value="${categoria1.idcategoria}" name="idcategoria">
    <button class="btn" type="submit">Elimina</button>
</form>

