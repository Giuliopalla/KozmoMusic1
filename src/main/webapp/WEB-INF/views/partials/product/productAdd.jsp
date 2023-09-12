<form method="post" action="/KozmoMusic_war_exploded/products/create" enctype="multipart/form-data"
      name="productaddform">
    <fieldset class="grid-y cell product-form">
        <legend>Crea Prodotto</legend>
        <c:if test="${not empty alert}">
            <%@include file="../../partials/alert.jsp" %>
        </c:if>
        <label for="NomeProd" class="field cell w50">
            <input id="NomeProd" name="NomeProd" placeholder="Nome" type="text" required minlength="5" maxlength="50">
        </label>
        <small class="cell errMsg"></small>
        <label for="DescProd" class="field cell w50">
            <input id="DescProd" name="DescProd" placeholder="Descirizione" type="text" required minlength="5"
                   maxlength="100">
        </label>
        <small class="cell errMsg"></small>
        <label for="PrezzoProd" class="field cell w50">
            <input id="PrezzoProd" name="PrezzoProd" placeholder="Prezzo" type="text" required>
        </label>
        <small class="cell errMsg"></small>
        <label for="FotoProd" class="field cell w50">
            <input id="FotoProd" name="FotoProd" type="file" required>
        </label>
        <small class="cell errMsg"></small>
        <label class=" field cell w50">
            <a> <input type="radio" id="CD" name="IsCDProd" value="true">CD
                <input type="radio" id="Vinile" name="IsCDProd" value="false">Vinile</a>
        </label>
        <label for="QuantitaProdotto" class="field cell w50">
            <input type="number" id="QuantitaProdotto" name="QuantitaProdotto" placeholder="Quantita' (max 99)" min="1"
                   max="99" required>
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
        <button type="submit" class="btn" onclick="validateForm(productaddform)">Crea Prodotto</button>
    </fieldset>
</form>