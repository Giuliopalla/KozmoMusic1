<form action="/KozmoMusic_war_exploded/products/update" method="post" enctype="multipart/form-data" name="productupdateform">
    <fieldset class="grid-y cell product-form">

        <legend>Aggiorna Prodotto</legend>
        <c:if test="${not empty alert}">
            <%@include file="../../partials/alert.jsp" %>
        </c:if>

        <input type="hidden" value="${prodotto1.idprodotto}" name="idProdotto">
        <label for="NomeProd" class="field cell w50">
            <b>Nome:</b><input id="NomeProd" name="NomeProd" placeholder="Nome" type="text" required minlength="5" maxlength="50">
        </label>
        <small class="cell errMsg"></small>
        <label for="DescProd" class="field cell w50">
            <b>Descrizione:</b><input id="DescProd" name="DescProd" placeholder="Descrizione" type="text" required minlength="5"
                                      maxlength="100">
        </label>
        <small class="cell errMsg"></small>
        <label for="PrezzoProd" class="field cell w50">
            <b>Prezzo:</b><input id="PrezzoProd" name="PrezzoProd" placeholder="Prezzo" type="text" required>
        </label>
        <small class="cell errMsg"></small>
        <label for="FotoProd" class="field cell w50">
            <input id="FotoProd" name="FotoProd" type="file">
        </label>
        <label class="field cell w50">
            <a>
                <input type="radio" id="CD" name="IsCDProd" value="true" checked>CD
                <input type="radio" id="Vinile" name="IsCDProd" value="false">Vinile</a>
        </label>
        <label for="QuantitaProdotto" class="field cell w50">
            <input type="number" id="QuantitaProdotto" name="QuantitaProdotto" placeholder="Quantita'" min="1" max="99" required>
        </label>
        <small class="cell errMsg"></small>
        <label for="Categoria1Prodotto" class="field cell w50">
            <select name="Categoria1Prodotto" id="Categoria1Prodotto">
                <c:forEach items="${categorie}" var="categoria">
                    <option value="${categoria.idcategoria}">${categoria.tipologia}</option>
                </c:forEach>
            </select>
        </label>
        <label for="Categoria2Prodotto" class="field cell w50">
            <select name="Categoria2Prodotto" id="Categoria2Prodotto">
                <c:forEach items="${categorie}" var="categoria">
                    <option value="${categoria.idcategoria}">${categoria.tipologia}</option>
                </c:forEach>
            </select>
        </label>
        <input type="hidden" value="${prodotto1.idprodotto}" name="idProdotto">
        <button class="btn" type="submit" onclick="validateForm(productupdateform)">Aggiorna</button>
    </fieldset>
</form>
<form action="/KozmoMusic_war_exploded/products/delete" method="post">
    <button class="btn" type="submit">Elimina</button>

</form>


